package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool implements AutoCloseable {
    final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(4);

    private final Thread consumer = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            threads.removeIf(t -> t.getState().equals(Thread.State.TERMINATED));
            if (threads.size() < size) {
                try {
                    Thread th = new Thread(tasks.poll());
                    threads.add(th);
                    th.start();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    });

    public ThreadPool() {
        consumer.start();
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread:threads) {
            thread.interrupt();
        }
    }

    @Override
    public void close() {
        consumer.interrupt();
        shutdown();
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) throws InterruptedException {
        try (ThreadPool pool = new ThreadPool()) {
            pool.work(() -> {
                System.out.println("start 1");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finish 1");
            });
            pool.work(() -> {
                System.out.println("start 2");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finish 2");
            });
            pool.work(() -> {
                System.out.println("start 3");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finish 3");
            });
            pool.work(() -> {
                System.out.println("start 4");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finish 4");
            });
            pool.work(() -> {
                System.out.println("start 5");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finish 5");
            });
            pool.work(() -> {
                System.out.println("start 6");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finish 6");
            });
            pool.work(() -> {
                System.out.println("start 7");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finish 7");
            });
            pool.work(() -> {
                System.out.println("start 8");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finish 8");
            });
            pool.work(() -> {
                System.out.println("start 9");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finish 9");
            });
            pool.work(() -> {
                System.out.println("start 10");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finish 10");
            });
            pool.work(() -> {
                System.out.println("start 11");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finish 11");
            });
        }
    }
}
