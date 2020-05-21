package com.example.bottomnavigation;

public class NumberConverter {

    public static int toDecimal(String binaryString){
        return Integer.parseInt(binaryString,2);
    }

    public static String toBinary(int integer){
        return Integer.toBinaryString(integer);
    }
}