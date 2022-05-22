package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String fileName;

    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    /**
     *  speed [1 kByte / 1 sec]
     *
     *  speed_download = 1 kB / (delta [millisec]
     *  = 1 kB / (delta [millisec] / 10000) [sec]
     *  = 1 kB * 1000 / delta [kB / sec]
     */
    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long delta = System.currentTimeMillis() - start;
                Thread.sleep(1000 > speed * delta ? 1000 / speed - delta : 0);
                start = System.currentTimeMillis();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (!testArgs(args)) {
            return;
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args[2];
        System.out.println("Download: " + url);
        System.out.println("Download speed: " + speed + "kByte/sec");
        System.out.println("Download file: " + fileName);
        Thread wget = new Thread(new Wget(url, speed, fileName));
        long start = System.currentTimeMillis();
        wget.start();
        wget.join();
        System.out.println("Download: ok. time="
                + (System.currentTimeMillis() - start)
                + " milliSec");
    }

    private static boolean testArgs(String[] args) {
        boolean res = args.length >= 3;
        if (!res) {
            System.out.println("Usage:");
            System.out.println("  Wget <url> <speed> <file name>");
            System.out.println("  <url>   - internet url");
            System.out.println("  <speed> - download speed kBytes pes 1 second");
            System.out.println("  <file name> - destination path and file name");
        }
        return res;
    }
}
