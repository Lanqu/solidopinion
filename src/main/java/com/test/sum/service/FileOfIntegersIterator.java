package com.test.sum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;

public class FileOfIntegersIterator implements Iterator<Integer> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ByteBuffer allocated =
            ByteBuffer.allocate(1024 * 1024).order(ByteOrder.LITTLE_ENDIAN);
    private final ChannelReader channelReader;

    FileOfIntegersIterator(final ChannelReader channelReader) throws IOException {
        this.channelReader = channelReader;

        channelReader.read(allocated);
        allocated.flip();
    }

    @Override
    public boolean hasNext() {
        if (canReadInteger()) {
            return true;
        } else {
            allocated.flip();

            int read;
            try {
                read = channelReader.read(allocated);
            } catch (IOException e) {
                logger.error("That shouldn't happen during exercise", e);
                throw new RuntimeException(e);
            }

            if (read >= 4) {
                allocated.flip();
                return true;
            } else {
                return false;
            }
        }
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
