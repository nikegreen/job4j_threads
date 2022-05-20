package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    private final char[] process = {'-', '\\', '|', '/'};

    @Override
    public void run() {
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r load: " + process[index & 3]);
            index++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        progress.interrupt();
        progress.join(600);
    }
}
