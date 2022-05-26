package ru.job4j.cache;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(final Base model) {
        memory.computeIfPresent(model.getId(), (id, y) -> {
            Base stored = memory.get(id);
            if (stored.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base newModel = new Base(id, model.getVersion() + 1);
            newModel.setName(model.getName());
            return newModel;
                }
        );
        return memory.containsKey(model.getId());
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }
}
