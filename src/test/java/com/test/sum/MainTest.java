package com.test.sum;

import com.test.sum.service.IncorrectInputException;
import com.test.sum.service.InputResolver;
import com.test.sum.service.MyIterator;
import org.junit.Test;

import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.*;

public class MainTest {

    // i like using 4th Junit
    @Test
    public void name() throws Exception {
//        InputStream resourceAsStream = getClass().getResourceAsStream("/simple.txt");
//        ReadableByteChannel readableByteChannel = Channels.newChannel(resourceAsStream);
//        ByteBuffer allocated = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN);
//        long sum = 0;
//        while (readableByteChannel.read(allocated) > 0) {
////            System.out.println(allocated.remaining());
//            allocated.flip();
////            System.out.println(allocated.remaining());
//            try {
//                while (allocated.hasRemaining()) {
//                    int anInt = allocated.getInt();
//                    Stream.generate(() -> {
//
//                    });
////                    System.out.println(anInt);
////                    System.out.println(allocated.remaining());
//                    sum += anInt;
//                }
//                allocated.flip();
//            } catch (BufferUnderflowException e) {
//                e.toString();
//            }
//        }

//        System.out.println("Sum is " + sum);

//        MyIterator it = new MyIterator();
//        final Stream<Integer> stream = StreamSupport
//                .stream(Spliterators.spliteratorUnknownSize(it, IMMUTABLE | NONNULL), true);
//
//        final Optional<Integer> reduce = stream.reduce((integer, integer2) -> integer + integer2);
//
//        System.out.println(reduce.get());
    }
}
