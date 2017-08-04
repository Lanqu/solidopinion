package com.test.sum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

public class MyIterator implements Iterator<Integer> {
    private ByteBuffer allocated = ByteBuffer.allocate(1024 * 1024).order(ByteOrder.LITTLE_ENDIAN);
    private final ChannelReader byteBufferConsumer;

    MyIterator(final ChannelReader byteBufferConsumer) throws IOException {
        this.byteBufferConsumer = byteBufferConsumer;

        byteBufferConsumer.read(allocated);
        allocated.flip();
    }

    @Override
    public boolean hasNext() {
        if (canReadInteger()) {
            return true;
        } else {
            allocated.flip();

            int read = 0;
            try {
                read = byteBufferConsumer.read(allocated);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (read > 0) {
                allocated.flip();
                return allocated.hasRemaining();
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return allocated.getInt();
    }

    private boolean canReadInteger() {
        return allocated.remaining() >= 4;
    }

    @FunctionalInterface
    public interface ChannelReader {
        int read(ByteBuffer buf) throws IOException;
    }
}
