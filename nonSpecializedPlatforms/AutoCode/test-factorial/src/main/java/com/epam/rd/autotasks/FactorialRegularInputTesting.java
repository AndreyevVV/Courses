package com.epam.rd.autotasks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FactorialRegularInputTesting {

    Factorial factorial = new Factorial();

    static Stream<Arguments> testCases() {
        return Stream.of(Arguments.of("1", "1"), Arguments.of("5", "120"),
                         Arguments.of("9", "362880"), Arguments.of("12", "479001600"),
                         Arguments.of("13", "6227020800"));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testFactorial(String in, String expected) {
        assertEquals(expected, factorial.factorial(in));
    }
}
