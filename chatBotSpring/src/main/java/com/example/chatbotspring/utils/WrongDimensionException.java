package com.example.chatbotspring.utils;

import java.util.Arrays;

public class WrongDimensionException extends RuntimeException {

    public WrongDimensionException(int actual, int expected, String layer){
        super("Expected " + expected + " value(s) for " + layer + "-layer but got " + actual + ".");
    }

    public WrongDimensionException(int[] actual, int[] expected){
        super("The dimensions of these two neural networks don't match: " + Arrays.toString(actual) + ", " + Arrays.toString(expected));
    }

    public WrongDimensionException(){
        super("Dimensions don't match.");
    }
}
