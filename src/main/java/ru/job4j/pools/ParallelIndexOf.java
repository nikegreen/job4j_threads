package ru.job4j.pools;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ParallelIndexOf extends RecursiveTask<Integer> {
    private final int limit = 10;
    private final Object[] array;
    private final int from;
    private final int to;

    private final Object elem;

    public  ParallelIndexOf(Object[] array, int from, int to, Object elem) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.elem = elem;
    }

    @Override
    protected Integer compute() {
        int len = to - from;
        if (len < limit) {
            return Arrays.asList(array).indexOf(elem);
        }
        int mid = len / 2;
        ParallelIndexOf left = new ParallelIndexOf(array, from, mid, elem);
        ParallelIndexOf right = new ParallelIndexOf(array, mid + 1, to, elem);
        left.fork();
        right.fork();
        int l = left.join();
        int r = right.join();
        return minIndex(l, r);
    }

    private Integer minIndex(int l, int r) {
        return l == -1 || r == -1 ? max(l, r) : min(l, r);
    }

    public static int indexOf(Object[] array, Object element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexOf(array, 0, array.length - 1, element));
    }

    public static void main(String[] args) {
        final Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        System.out.println("index=" + ParallelIndexOf.indexOf(array, array[7]));
    }
}
