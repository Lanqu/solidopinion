package com.test.sum;

import com.test.sum.service.ResourceAccessor;
import com.test.sum.service.SecuredResourceAccessorImpl;

import java.util.OptionalLong;
import java.util.Spliterators;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.IMMUTABLE;
import static java.util.Spliterator.NONNULL;

public class Main {

    public static final String file = "d:\\Videos\\torrent\\123\\Shameless (US) - Season 1 (AlexFilm)\\Shameless.US.s01e03.rus.eng.AlexFilm.avi";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please enter correct file");
        }

        final long l = System.nanoTime();

        ResourceAccessor it = new SecuredResourceAccessorImpl();
        it.withResources(args[0], (size, integerIterator) -> {
            final LongStream stream = StreamSupport
                    .stream(Spliterators.spliterator(integerIterator, size, IMMUTABLE | NONNULL), false)
                    .mapToLong(value -> value);

            final OptionalLong reduce = stream.reduce((l1, l2) -> l1 + l2);

            System.out.println(reduce.orElseThrow(() -> {throw new RuntimeException("Can't calculate ");}));
            System.out.println((System.nanoTime() - l) / 1000000000.0);
        });
    }
}
