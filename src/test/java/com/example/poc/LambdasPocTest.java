package com.example.poc;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LambdasPocTest {

    @Test
    void predicateAndFunctionWork() {
        Predicate<String> nonBlank = s -> s != null && !s.isBlank();
        Function<String, Integer> length = String::length;

        assertTrue(nonBlank.test("x"));
        assertFalse(nonBlank.test(""));
        assertEquals(3, length.apply("abc"));
    }

    @Test
    void callableLambdaWorks() throws Exception {
        Callable<Integer> c = () -> 21 * 2;
        assertEquals(42, c.call());
    }

    @Test
    void streamsTransformAsExpected() {
        List<String> input = List.of(" Ada ", "", "ALAN");
        List<String> output = input.stream()
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        assertEquals(List.of("ada", "alan"), output);
    }

    @Test
    void customTransformerComposes() {
        Transformer<String, String> trim = String::trim;
        Transformer<String, String> upper = String::toUpperCase;
        Transformer<String, Integer> len = String::length;

        Transformer<String, Integer> pipeline = trim.andThen(upper).andThen(len);
        assertEquals(5, pipeline.apply("  hello  "));
    }
}
