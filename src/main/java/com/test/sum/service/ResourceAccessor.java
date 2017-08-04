package com.test.sum.service;

import java.util.Iterator;
import java.util.function.BiConsumer;

public interface SecuredIterator {
    
    void withResources(final String fileName, final BiConsumer<Long, Iterator<Long>> it);
}
