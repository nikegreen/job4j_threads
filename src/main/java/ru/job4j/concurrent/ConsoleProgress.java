package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        final char[] PROCESS = {'-', '\\', '|', '/'};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (char ch : PROCESS) {
                    System.out.print("\r load: " + ch);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(7000);
        progress.interrupt();
        progress.join(600);
    }
}
