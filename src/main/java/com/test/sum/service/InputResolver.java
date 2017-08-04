package com.test.sum.service;

import java.io.FileNotFoundException;
import java.nio.channels.ReadableByteChannel;

public interface InputResolver {
    ReadableByteChannel resolve() throws FileNotFoundException;
}
