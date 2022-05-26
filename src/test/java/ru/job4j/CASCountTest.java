package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class CASCountTest {
    @Test
    public void test1() throws InterruptedException {
        final int MAX = 60;
        CASCount count = new CASCount();
        assertEquals(count.get(), 0);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < MAX; i++) {
                count.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < MAX; i++) {
                count.increment();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(count.get(), MAX * 2);
    }
}