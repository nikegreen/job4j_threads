package ru.job4j.pools;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums() {
        }

        public Sums(int row, int col) {
            this.rowSum = row;
            this.colSum = col;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Sums)) {
                return false;
            }
            Sums sums = (Sums) o;
            return getRowSum() == sums.getRowSum() && getColSum() == sums.getColSum();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRowSum(), getColSum());
        }

        @Override
        public String toString() {
            return "Sums{rowSum=" + rowSum + ", colSum=" + colSum + '}';
        }
    }

    public static Sums[] sum(int[][] matrix) {
        final int size = matrix.length;
        final Sums[] sums = new Sums[size];
        for (int n = 0; n < size; n++) {
            sums[n] = new Sums();
            for (int i = 0; i < size; i++) {
                sums[n].colSum += matrix[i][n];
                sums[n].rowSum += matrix[n][i];
            }
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        final int size = matrix.length;
        final Sums[] sums = new Sums[size];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int n = 0; n < size; n++) {
            futures.put(n, getTask(matrix, n, size));
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTask(int[][] matrix, int current, int size) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sums = new Sums();
            for (int i = 0; i < size; i++) {
                sums.colSum += matrix[i][current];
                sums.rowSum += matrix[current][i];
            }
            return sums;
        });
    }
}
