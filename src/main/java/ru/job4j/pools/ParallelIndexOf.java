package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ParallelIndexOf<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;

    private final T elem;

    public  ParallelIndexOf(T[] array, int from, int to, T elem) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.elem = elem;
    }

    @Override
    protected Integer compute() {
        final int len = to - from;
        final int limit = 10;
        if (len < limit) {
            return indexOf(elem, from, to);
        }
        int mid = len / 2;
        ParallelIndexOf<T> left = new ParallelIndexOf<>(array, from, mid, elem);
        ParallelIndexOf<T> right = new ParallelIndexOf<>(array, mid + 1, to, elem);
        left.fork();
        right.fork();
        int l = left.join();
        int r = right.join();
        return minIndex(l, r);
    }

    private Integer minIndex(int l, int r) {
        return l == -1 || r == -1 ? max(l, r) : min(l, r);
    }

    private int indexOf(T elem, int from, int to) {
        for (int i = from; i <= to; i++) {
            if (elem.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int indexOf(T[] array, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexOf<>(array, 0, array.length - 1, element));
    }

    public static void main(String[] args) {
        final Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        System.out.println("index=" + ParallelIndexOf.indexOf(array, array[7]));
    }
}
