package ru.job4j.pool;

import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification implements AutoCloseable {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    private final PrintStream out;

    public EmailNotification() {
        this.out = System.out;
    }

    public EmailNotification(PrintStream printStream) {
        this.out = printStream;
    }

    /**
     * subject = Notification {username} to email {email}.
     *
     * body = Add a new event to {username}
     *
     * @param user - (type User) not == null. include username & email.
     *             automatic computed subject & body.
     *             run thread for send message.
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            StringBuilder subject = new StringBuilder();
            subject.append("Notification ")
                   .append(user.getUsername())
                   .append(" to email ")
                   .append(user.getEmail());
            StringBuilder body = new StringBuilder();
            body.append("Add a new event to ")
                .append(user.getUsername());
            send(subject.toString(),
                    body.toString(),
                    user.getEmail());
        });
    }

    public synchronized void send(String subject, String body, String email) {
        this.out.println("email:" + email);
        this.out.println("subject:" + subject);
        this.out.println("body:" + body);
    }

    @Override
    public void close() {
        pool.shutdown();
        while (pool.shutdownNow().size() != 0 && !Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
