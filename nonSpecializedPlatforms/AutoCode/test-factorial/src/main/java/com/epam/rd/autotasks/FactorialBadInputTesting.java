package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FactorialBadInputTesting {

    Factorial factorial = new Factorial();

    @Test
    void testNullInput(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{factorial.factorial(null);});
    }
    @Test
    void testNegativeInput(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{factorial.factorial("-1");});
    }

    @Test
    void testFractionalInput(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{factorial.factorial("3.5");});
    }

    @Test
    void testNonDigitalInput(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{factorial.factorial("NonDigitalInput");});
    }
}