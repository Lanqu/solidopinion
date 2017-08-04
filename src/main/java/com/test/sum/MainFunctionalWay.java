package com.test.sum;

import com.test.sum.service.ResourceAccessor;
import com.test.sum.service.SecuredResourceAccessorImpl;

import java.util.OptionalLong;
import java.util.Spliterators;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.IMMUTABLE;
import static java.util.Spliterator.NONNULL;

public class MainFunctionalWay {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please enter correct file");
            return;
        }

        final long l = System.nanoTime();

        ResourceAccessor it = new SecuredResourceAccessorImpl();
        it.withResources(args[0], (size, integerIterator) -> {
            final long sum = StreamSupport
                    .stream(Spliterators.spliterator(integerIterator, size, IMMUTABLE | NONNULL), false)
                    .mapToLong(value -> value).sum();

            System.out.println("FP: Sum is " + sum);
            System.out.println((System.nanoTime() - l) / 1000000000.0);
        });
    }
}
