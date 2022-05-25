package ru.job4j.buffer;

import ru.job4j.SimpleBlockingQueue;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(4);
        final Thread consumer = new Thread(
                () -> {
                    try {
                        while (true) {
                            System.out.println(queue.poll());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("consumer finish");
                }
        );
        consumer.start();
        final Thread producer = new Thread(
                () -> {
                    try {
                        for (int index = 0; index != 3; index++) {
                            queue.offer(index);
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("producer finish");
                }
        );
        producer.start();
        try {
            producer.join();
            consumer.interrupt();
        } catch (InterruptedException e) {
        }
        System.out.println("finish");
    }
}
