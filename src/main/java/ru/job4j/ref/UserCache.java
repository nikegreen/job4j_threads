package ru.job4j.ref;


import net.jcip.annotations.NotThreadSafe;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  в POM.XML нужно добавить
 *  <project...>
 *   <dependencies>
 *     <dependency>
 *        <groupId>net.jcip</groupId>
 *        <artifactId>jcip-annotations</artifactId>
 *        <version>1.0</version>
 *     </dependency>
 *     ...
 *   </dependencies>
 *
 * </project>
 */

@NotThreadSafe
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        List<User> res = new ArrayList<>();
        users.values().forEach(user -> res.add(User.of(user.getName())));
        return res;
    }
}
