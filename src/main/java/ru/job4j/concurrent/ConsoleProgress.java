package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    private final char[] process = {'-', '\\', '|', '/'};

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            for (char ch:process) {
                System.out.print("\r load: " + ch);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(8000);
        progress.interrupt();
        progress.join(600);
    }
}
