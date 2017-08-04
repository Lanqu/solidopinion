package com.test.sum;

import com.test.sum.service.IncorrectInputException;
import com.test.sum.service.InputResolver;
import com.test.sum.service.InputResolverImpl;
import com.test.sum.service.SecuredIterator;
import com.test.sum.service.SecuredIteratorImpl;
import com.test.sum.service.SumCalculator;
import com.test.sum.service.SumCalculatorImpl;

import java.io.InputStream;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.IMMUTABLE;
import static java.util.Spliterator.NONNULL;
import static java.util.function.Function.identity;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please pass correct file");
        }

        SecuredIterator it = new SecuredIteratorImpl();
        it.withResources(args[0], (size, integerIterator) -> {
            final LongStream stream = StreamSupport
                    .stream(Spliterators.spliterator(integerIterator, size, IMMUTABLE | NONNULL), false)
                    .mapToLong(identity());

            final Optional<Long> reduce = stream.reduce((l1, l2) -> {
                System.out.println(l1);
                return l1 + l2;});

            System.out.println(reduce.get());
        });
    }
}
