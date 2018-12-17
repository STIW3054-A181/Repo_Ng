/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.group;

import java.util.concurrent.Callable;

/**
 *
 * @author lenovo
 */
public class calculation implements Callable{
    int word;
    int character;
    double mean=0;
    double SD=0;
    public calculation(int word, int character){
        this.word=word;
        this.character=character;
    }
    @Override
    public Integer call() throws Exception {
        mean=character/word;
        SD=Math.sqrt(Math.pow((character-mean),2)/word);
        return (int)SD;
    }
    }
