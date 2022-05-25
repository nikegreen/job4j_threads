package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void test1() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final int[] prod = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] out = new int[prod.length];
        Thread producer = new Thread(() -> {
            try {
                for (Integer i: prod) {
                    queue.offer(i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < prod.length; i++) {
                    out[i] = queue.poll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertArrayEquals(out, prod);
    }

}