package com.test.sum;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;

public class MainProceduralWay {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Please enter correct file");
            return;
        }
        final long l = System.nanoTime();

        RandomAccessFile f = new RandomAccessFile(args[0], "r");
        ReadableByteChannel readableByteChannel = f.getChannel();
        ByteBuffer allocated = ByteBuffer.allocate(1024 * 1024).order(ByteOrder.LITTLE_ENDIAN);
        long sum = 0;
        while (readableByteChannel.read(allocated) > 0) {
            allocated.flip();
            while (allocated.remaining() >= 4) {
                sum += allocated.getInt();
            }
            allocated.flip();
        }

        System.out.println("Sum is " + sum);
        System.out.println((System.nanoTime() - l) / 1000000000.0);
    }
}
