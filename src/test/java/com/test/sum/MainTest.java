package com.test.sum;

import com.test.sum.service.ResourceAccessor;
import com.test.sum.service.SecuredResourceAccessorImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.IMMUTABLE;
import static java.util.Spliterator.NONNULL;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainFunctionalWayTest {

    private String filePath;

    @Before
    public void setUp() throws Exception {
//        filePath = Paths.get(this.getClass().getResource("/simple.txt").toURI()).toFile().getAbsolutePath();
        filePath = "d:\\Videos\\torrent\\123\\Prey [MULTi2]\\data1.dat";
    }

    @Test
    public void fpWay() throws Exception {
        final long l = System.nanoTime();

        ResourceAccessor it = new SecuredResourceAccessorImpl();
        it.withResources(filePath, (size, integerIterator) -> {
            final long sum = StreamSupport
                    .stream(Spliterators.spliterator(integerIterator, size, IMMUTABLE | NONNULL), false)
                    .mapToLong(value -> value).sum();

            System.out.println("FP: Sum is " + sum);
            System.out.println((System.nanoTime() - l) / 1000000000.0);

            assertThat(sum, is(15L));
        });
    }

    @Test
    public void procWay() throws Exception {
        final long l = System.nanoTime();

        RandomAccessFile f = new RandomAccessFile(filePath, "r");
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

        System.out.println("PR: Sum is " + sum);
        System.out.println((System.nanoTime() - l) / 1000000000.0);

        assertThat(sum, is(15L));
    }

}
