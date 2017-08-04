package com.test.sum.service;

import sun.nio.ch.FileChannelImpl;

import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.stream.Stream;

public class SumCalculatorImpl implements SumCalculator {

    private final InputResolver inputResolver;

    public SumCalculatorImpl(InputResolver inputResolver) {
        this.inputResolver = inputResolver;
    }

    @Override
    public Long calculate() {
        return 0l;
    }
}
