package ru.job4j;

final public class User {
    private final int id;
    private final int amount;

    public User(final int id, final int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", amount=" + amount
                + '}';
    }
}
