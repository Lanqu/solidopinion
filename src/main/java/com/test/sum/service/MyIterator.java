package com.test.sum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;
import java.util.Iterator;

public class MyIterator implements Iterator<Long> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ByteBuffer allocated = ByteBuffer.allocate(1024).order(ByteOrder.LITTLE_ENDIAN);
    private final ReadableByteChannel readableByteChannel;

    MyIterator(final ReadableByteChannel readableByteChannel) throws IOException {
        this.readableByteChannel = readableByteChannel;

        readableByteChannel.read(allocated);
        allocated.flip();
    }

    @Override
    public boolean hasNext() {
        if (allocated.hasRemaining()) {
            return true;
        } else {
            int read = 0;
            try {
                allocated.flip();
                read = readableByteChannel.read(allocated);
            } catch (IOException e) {
                logger.error("Can't read from channel", e);
            }

            if (read > 0) {
                allocated.flip();
                return allocated.hasRemaining();
            }
        }
        return false;
    }

    @Override
    public Long next() {
        if (allocated.remaining() >= 4) {
            return (long) allocated.getInt();
        } else return 0L;
    }
}
