package com.example.bottomnavigation;

public class MathExt {

    public static String sumBinaryStrings(String first, String second)
    {
        int b1 = Integer.parseInt(first, 2);
        int b2 = Integer.parseInt(second, 2);
        int sum = b1 + b2;
        return Integer.toBinaryString(sum); }

}
