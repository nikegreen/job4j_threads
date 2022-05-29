package ru.job4j.pools;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RolColSumTest {

    @Test
    public void test1sum() {
        final int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        final RolColSum.Sums[] res = {
                new RolColSum.Sums(10, 28),
                new RolColSum.Sums(26, 32),
                new RolColSum.Sums(42, 36),
                new RolColSum.Sums(58, 40)
        };
        final List<RolColSum.Sums> list = List.of(res);
        assertThat(List.of(RolColSum.sum(matrix)), is(list));
    }

    @Test
    public void test1asyncSum() throws ExecutionException, InterruptedException {
        final int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        final RolColSum.Sums[] res = {
                new RolColSum.Sums(10, 28),
                new RolColSum.Sums(26, 32),
                new RolColSum.Sums(42, 36),
                new RolColSum.Sums(58, 40)
        };
        final List<RolColSum.Sums> list = List.of(res);
        assertThat(List.of(RolColSum.asyncSum(matrix)), is(list));
    }
}