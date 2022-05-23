package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
final public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, Integer> users = new HashMap<>();

    public synchronized boolean add(User user) {
        boolean res = user != null && !users.containsKey(user.getId());
        if (res) {
            users.put(user.getId(), user.getAmount());
        }
        return res;
    }

    public synchronized boolean update(User user) {
        boolean res = user != null && users.containsKey(user.getId());
        if (res) {
            users.put(user.getId(), user.getAmount());
        }
        return res;
    }

    public synchronized boolean delete(User user) {
        boolean res = user != null && users.containsKey(user.getId());
        if (res) {
            users.remove(user.getId());
        }
        return res;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean res = users.containsKey(fromId)
                && users.containsKey(toId)
                && amount >= 0
                && users.get(fromId) >= amount;
        if (res) {
            users.put(fromId, users.get(fromId) - amount);
            users.put(toId, users.get(toId) + amount);
        }
        return res;
    }

    public synchronized int getAmount(int id) {
        if (!users.containsKey(id)) {
            throw new IllegalArgumentException("key " + id + " not found!");
        }
        return users.get(id);
    }

    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        System.out.println("add id1 = " + storage.add(new User(1, 100)));
        System.out.println("add id2 = " + storage.add(new User(2, 200)));
        System.out.println("transfer = " + storage.transfer(1, 2, 50));
        System.out.println("id=1, amount=" + storage.getAmount(1));
        System.out.println("id=2, amount=" + storage.getAmount(2));
    }
}
