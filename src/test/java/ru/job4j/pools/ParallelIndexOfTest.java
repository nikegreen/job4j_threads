package ru.job4j.pools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParallelIndexOfTest {
    @Test
    public void test1left() {
        final Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        assertEquals(ParallelIndexOf.indexOf(array, array[7]), 7);
    }

    @Test
    public void test2right() {
        final Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        assertEquals(ParallelIndexOf.indexOf(array, array[12]), 12);
    }

    @Test
    public void test3single() {
        final Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        assertEquals(ParallelIndexOf.indexOf(array, array[7]), 7);
    }
}