package com.yagnesh.retrofitdemo.model;

// This is used to map the JSON keys to the object by GSON
public class Question {

    String title;
    String link;

    @Override
    public String toString() {
        return (title);
    }
} 