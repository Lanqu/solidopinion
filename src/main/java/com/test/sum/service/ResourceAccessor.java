package com.test.sum.service;

import java.util.Iterator;
import java.util.function.BiConsumer;

public interface ResourceAccessor {
    void withResources(final String fileName, final CalculateAction action);

    @FunctionalInterface
    interface CalculateAction {
        void calculate(final Long integersCount, final Iterator<Integer> integersIterator);
    }
}
