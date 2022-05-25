package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void test1add() throws InterruptedException {
        boolean[] add1 = {false, false, false, true};
        UserStorage storage = new UserStorage();
        Thread thread1 = new Thread(() -> add1[0] = storage.add(new User(1, 100)));
        Thread thread2 = new Thread(() -> add1[1] = storage.add(new User(2, 100)));
        Thread thread3 = new Thread(() -> add1[2] = storage.add(new User(3, 100)));
        Thread thread4 = new Thread(() -> add1[3] = storage.add(new User(3, 200)));
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.start();
        thread4.join();
        assertTrue(add1[0]);
        assertTrue(add1[1]);
        assertTrue(add1[2]);
        assertFalse(add1[3]);
    }

    @Test
    public void test1addAndDelete() throws InterruptedException {
        boolean[] add1 = {false, false, false};
        boolean[] del1 = {false, false, false, true};
        UserStorage storage = new UserStorage();
        Thread thread1 = new Thread(() -> add1[0] = storage.add(new User(1, 100)));
        Thread thread2 = new Thread(() -> add1[1] = storage.add(new User(2, 100)));
        Thread thread3 = new Thread(() -> add1[2] = storage.add(new User(3, 100)));
        Thread thread4 = new Thread(() -> del1[0] = storage.delete(new User(1, 100)));
        Thread thread5 = new Thread(() -> del1[1] = storage.delete(new User(2, 100)));
        Thread thread6 = new Thread(() -> del1[2] = storage.delete(new User(3, 100)));
        Thread thread7 = new Thread(() -> del1[3] = storage.delete(new User(3, 100)));
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        assertTrue(add1[0]);
        assertTrue(add1[1]);
        assertTrue(add1[2]);
        thread4.start();
        thread5.start();
        thread6.start();
        thread4.join();
        thread5.join();
        thread6.join();
        assertTrue(del1[0]);
        assertTrue(del1[1]);
        assertTrue(del1[2]);
        thread7.start();
        thread7.join();
        assertFalse(del1[3]);
    }

    @Test
    public void test1addAndUpdate() throws InterruptedException {
        boolean[] add1 = {false, false, false, true};
        boolean[] upd1 = {false, false, false, true};
        UserStorage storage = new UserStorage();
        Thread thread1 = new Thread(() -> add1[0] = storage.add(new User(1, 100)));
        Thread thread2 = new Thread(() -> add1[1] = storage.add(new User(2, 100)));
        Thread thread3 = new Thread(() -> add1[2] = storage.add(new User(3, 100)));
        Thread thread4 = new Thread(() -> add1[3] = storage.add(new User(3, 200)));
        Thread thread5 = new Thread(() -> upd1[0] = storage.update(new User(1, 200)));
        Thread thread6 = new Thread(() -> upd1[1] = storage.update(new User(2, 200)));
        Thread thread7 = new Thread(() -> upd1[2] = storage.update(new User(3, 200)));
        Thread thread8 = new Thread(() -> upd1[3] = storage.update(new User(4, 200)));
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.start();
        thread4.join();
        assertTrue(add1[0]);
        assertTrue(add1[1]);
        assertTrue(add1[2]);
        assertFalse(add1[3]);
        thread5.start();
        thread6.start();
        thread7.start();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.start();
        thread8.join();
        assertTrue(upd1[0]);
        assertTrue(upd1[1]);
        assertTrue(upd1[2]);
        assertFalse(upd1[3]);
        assertEquals(storage.getAmount(1), 200);
        assertEquals(storage.getAmount(2), 200);
        assertEquals(storage.getAmount(3), 200);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test1GetAmountWhenFail() {
        UserStorage storage = new UserStorage();
        storage.getAmount(1);
    }

    @Test
    public void test2GetAmountWhen100() throws InterruptedException {
        UserStorage storage = new UserStorage();
        long[] amount = {0};
        storage.add(new User(1, 100));
        Thread thread = new Thread(() -> amount[0] = storage.getAmount(1));
        assertEquals(amount[0], 0);
        thread.start();
        thread.join();
        assertEquals(amount[0], 100);
    }
}