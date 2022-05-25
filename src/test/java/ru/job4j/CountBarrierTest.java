package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountBarrierTest {
    @Test
    public void test1() throws InterruptedException {
        boolean[] res = {false};
        CountBarrier cb = new CountBarrier(2);
        Thread threadCount1 = new Thread(cb::count);
        Thread threadCount2 = new Thread(cb::count);
        Thread threadWait = new Thread(()-> {
            cb.await();
            res[0] = true;
        });
        threadWait.start();
        assertFalse(res[0]);
        threadCount2.start();
        threadCount2.join();
        assertFalse(res[0]);
        threadCount1.start();
        threadCount1.join();
        threadWait.join();
        assertTrue(res[0]);
    }
}