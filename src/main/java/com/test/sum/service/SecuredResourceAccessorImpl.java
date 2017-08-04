package com.test.sum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.ReadableByteChannel;
import java.util.Iterator;
import java.util.function.BiConsumer;

public class SecuredResourceAccessorImpl implements ResourceAccessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int BYTES_IN_INT = 4;

    @Override
    public void withResources(final String fileName, final CalculateAction it) {
        logger.debug("Resolving file %s", fileName);

        try (final RandomAccessFile r = new RandomAccessFile(fileName, "r");
             final ReadableByteChannel channel = r.getChannel()) {
            final long integersInFile = getCountOfIntegersInFile(r);
            it.calculate(integersInFile, new FileOfIntegersIterator(channel::read));
        } catch (IOException e) {
            logger.error("Error accessing file", e);
        }
    }

    private long getCountOfIntegersInFile(RandomAccessFile r) throws IOException {
        return r.length() / BYTES_IN_INT;
    }
}
