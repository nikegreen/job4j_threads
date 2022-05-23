package ru.job4j.synch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>(new ArrayList<>());
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        Set<Integer> exp = Set.of(Integer.valueOf(1),Integer.valueOf(2));
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(exp));
    }
}
