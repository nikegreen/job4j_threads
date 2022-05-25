package ru.job4j;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(4);
        Thread producer = new Thread(
                () -> IntStream.range(0, 5).forEach(
                        value -> {
                            try {
                                queue.offer(value);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
    @Test
    public void test1() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
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