package com.test.sum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SecuredIteratorImpl implements SecuredIterator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int BYTES_IN_INT = 4;

    @Override
    public void withResources(final String fileName, final BiConsumer<Long, Iterator<Integer>> it) {
        logger.debug("Resolving file %s", fileName);

        try (final RandomAccessFile r = new RandomAccessFile(fileName, "r")) {
            try (final FileChannel channel = r.getChannel()) {
                final long integersInFile = r.length() / BYTES_IN_INT;
                it.accept(integersInFile, new MyIterator(channel));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
