package com.test.sum;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainTest {

    // i like using 4th Junit
    @Test
    public void name() throws Exception {
        URL resource = getClass().getResource("/simple.txt");
        InputStream resourceAsStream = getClass().getResourceAsStream("/simple.txt");

        Path path = Paths.get(resource.toURI());
//        BufferedReader bufferedReader = Files.newBufferedReader(path);
//        bufferedReader.lines().forEach(System.out::println);
//
        ReadableByteChannel readableByteChannel = Channels.newChannel(resourceAsStream);


        FileChannel inChannel = new RandomAccessFile(
                path.toFile().getAbsolutePath(), "r"
        ).getChannel();

        ByteBuffer allocated = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN);
        long sum = 0;
        while (readableByteChannel.read(allocated) > 0) {
//            System.out.println(allocated.remaining());
            allocated.flip();
//            System.out.println(allocated.remaining());
            try {
                while (allocated.hasRemaining()) {
                    int anInt = allocated.getInt();
//                    System.out.println(anInt);
//                    System.out.println(allocated.remaining());
                    sum += anInt;
                }
                allocated.flip();
            } catch (BufferUnderflowException e) {
                e.toString();
            }
        }

        System.out.println("Sum is " + sum);
        inChannel.close();
    }
}
