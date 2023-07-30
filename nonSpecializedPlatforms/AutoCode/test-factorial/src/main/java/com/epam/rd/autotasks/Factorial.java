package com.epam.rd.autotasks;

import java.math.BigInteger;

public class Factorial {
    public String factorial(String n) {
        BigInteger fact;

        try {
            fact = BigInteger.valueOf(Integer.parseInt(n));
        } catch (Exception e) {throw new IllegalArgumentException();}
        if (fact.compareTo(BigInteger.ZERO) < 0) throw new IllegalArgumentException();
        BigInteger result = BigInteger.ONE;
        if (fact.equals(BigInteger.ONE) || fact.equals(BigInteger.ZERO)) {
            return result.toString();
        }
        BigInteger big1 = fact.subtract(BigInteger.ONE);
        String int1 = big1.toString();
        result = fact.multiply(BigInteger.valueOf(Integer.parseInt(factorial(int1))));
        return result.toString();
    }
}
