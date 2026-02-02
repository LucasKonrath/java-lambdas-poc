package com.example.poc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LambdasPoc {

    public static void main(String[] args) throws Exception {
        System.out.println("=== java-lambdas-poc ===");

        runnableAndCallable();
        standardFunctionalInterfaces();
        methodReferencesAndStreams();
        customFunctionalInterfaceComposition();
        capturingEffectivelyFinal();

        System.out.println("Done.");
    }

    static void runnableAndCallable() throws Exception {
        Runnable r = () -> System.out.println("Runnable ran at " + LocalDate.now());

        Callable<Integer> c = () -> 40 + 2;

        r.run();
        System.out.println("Callable returned: " + c.call());
    }

    static void standardFunctionalInterfaces() {
        Predicate<String> nonBlank = s -> s != null && !s.isBlank();
        Function<String, Integer> length = String::length;
        Consumer<String> printer = System.out::println;
        Supplier<String> supplier = () -> "supplied-value";

        String value = supplier.get();
        if (nonBlank.test(value)) {
            printer.accept("Length of '" + value + "' = " + length.apply(value));
        }
    }

    static void methodReferencesAndStreams() {
        List<String> names = Arrays.asList(" Ada ", "grace", "", " Linus ", "ALAN");

        List<String> cleaned = names.stream()
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(String::toLowerCase)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        System.out.println("Cleaned names: " + cleaned);

        Optional<Integer> totalChars = cleaned.stream()
                .map(String::length)
                .reduce(Integer::sum);

        System.out.println("Total chars: " + totalChars.orElse(0));
    }

    static void customFunctionalInterfaceComposition() {
        Transformer<String, String> trim = String::trim;
        Transformer<String, String> upper = String::toUpperCase;
        Transformer<String, Integer> len = String::length;

        Transformer<String, Integer> pipeline = trim.andThen(upper).andThen(len);

        System.out.println("Pipeline result for '  hello  ': " + pipeline.apply("  hello  "));
    }

    static void capturingEffectivelyFinal() {
        int multiplier = 3; // effectively final

        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4));
        List<Integer> scaled = nums.stream()
                .map(n -> n * multiplier)
                .collect(Collectors.toList());

        System.out.println("Scaled by " + multiplier + ": " + scaled);
    }
}
