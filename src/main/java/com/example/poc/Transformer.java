package com.example.poc;

@FunctionalInterface
public interface Transformer<T, R> {
    R apply(T input);

    default <V> Transformer<T, V> andThen(Transformer<? super R, ? extends V> after) {
        return (T t) -> after.apply(this.apply(t));
    }

    static <T> Transformer<T, T> identity() {
        return t -> t;
    }
}
