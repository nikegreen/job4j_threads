package ru.job4j.cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {
    @Test
    public void test1add() {
        Cache cache = new Cache();
        assertTrue(cache.add(new Base(1, 1)));
        assertTrue(cache.add(new Base(2, 1)));
        assertTrue(cache.add(new Base(3, 1)));
        assertFalse(cache.add(new Base(1, 1)));
        assertFalse(cache.add(new Base(2, 1)));
        assertFalse(cache.add(new Base(3, 1)));
    }

    @Test
    public void test1addAndUpdate() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        assertTrue(cache.add(base1));
        assertTrue(cache.update(base1));
    }

    @Test
    public void test1addAndDelete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        assertTrue(cache.add(base1));
        assertFalse(cache.add(base1));
        cache.delete(base1);
        assertTrue(cache.add(base1));
    }
}