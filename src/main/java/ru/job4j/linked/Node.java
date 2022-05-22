package ru.job4j.linked;

/**
 *  Immutable class
 *  all field final
 *  all field is not change
 *  remove setValue() & setNext()
 *  class Node -> final.
 *
 *  https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html
 *
 * @param <T>
 */
final public class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(final T value, final Node<T> next) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}

