package zscore;
/*
 * Copyright (C) 2012 amacaulay
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math.stat.descriptive.SummaryStatistics;
import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Calculates the z-score and sum of z-scores for regions of interest and a list
 * of attributes
 * 
 * @author amacaulay
 */
public final class ZScore {

	static final Logger LOGGER = LoggerFactory.getLogger(ZScore.class);

	private ZScore() {
	}

	/**
	 * Calculates z-score and sum of z-scores
	 * 
	 * @param features
	 *            Regions of interest
	 * @param attributes
	 *            Attributes to calculate z-scores for
	 * @return
	 */
	public static Collection sumZScores(
			FeatureIterator<SimpleFeature> features, List<String> attributes) {
		List<SimpleFeature> results = new ArrayList();
		// SummaryStatistics stats = new SummaryStatistics();

		Map<String, SummaryStatistics> stats = new HashMap();
		for (String attr : attributes) {
			SummaryStatistics s = new SummaryStatistics();
			stats.put(attr, s);
		}
		try {
			// Build up summary statistics for each attribute across all
			// features
			while (features.hasNext()) {
				SimpleFeature region = features.next();
				for (String attr : attributes) {
					stats.get(attr).addValue((Double) region.getAttribute(attr));
				}
				results.add(buildFeature(region, attributes));
			}
		} finally {
			features.close();
		}

		// Calculate Z-Score for each attribute in each feature and also sum the
		// z-scores for the set of attributes
		for (SimpleFeature region : results) {
			Double totalZ = 0.0;
			for (String attr : attributes) {
				Double rawScore = (Double) region.getAttribute(attr);
				Double zScore = (rawScore - stats.get(attr).getMean())
						/ stats.get(attr).getStandardDeviation();
				if (zScore != Double.NaN) {
					region.setAttribute(attr + "_ZScore", zScore);
					totalZ += zScore;
				} else {
					region.setAttribute(attr + "_ZScore", null);
				}
				if (attributes.size() > 1) {
					region.setAttribute("SumZScore", totalZ);
				}
			}
			LOGGER.debug("Z-score: {}", totalZ);
		}

		return DataUtilities.collection(results);
	}

	private static SimpleFeature buildFeature(SimpleFeature region,
			List<String> attributes) {

		SimpleFeatureType sft = (SimpleFeatureType) region.getType();
		SimpleFeatureTypeBuilder stb = new zscore.SimpleFeature();
		stb.init(sft);
		stb.setName("statisticsFeatureType");
		for (String attr : attributes) {
			stb.add(attr + "_ZScore", Double.class);
		}
		if (attributes.size() > 1) {
			stb.add("SumZScore", Double.class);
		}
		SimpleFeatureType statsFT = stb.buildFeatureType();
		SimpleFeatureBuilder sfb = new SimpleFeatureBuilder(statsFT);
		sfb.addAll(region.getAttributes());

		return sfb.buildFeature(region.getID());

	}
}