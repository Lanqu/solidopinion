package com.test.sum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.ReadableByteChannel;

public class InputResolverImpl implements InputResolver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String fileName;

    public InputResolverImpl(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ReadableByteChannel resolve() throws FileNotFoundException {
        logger.debug("Resolving file %s", fileName);
        return new RandomAccessFile(fileName, "r").getChannel();
    }
}
