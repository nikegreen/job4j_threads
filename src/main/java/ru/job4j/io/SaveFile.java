package ru.job4j.io;

import java.io.*;

final public class SaveFile {
    private final File file;

    public SaveFile(final File f) {
        file = f;
    }

    public void saveContent(final String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(o)) {
                for (int i = 0; i < content.length(); i += 1) {
                    bufferedOutputStream.write(content.charAt(i));
                }
            }
        }
    }
}
