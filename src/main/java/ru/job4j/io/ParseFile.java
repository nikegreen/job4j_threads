package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

final public class ParseFile {
    private final File file;

    public ParseFile(final File f) {
        file = f;
    }

    public String getContent() throws IOException {
        return getContent((c) -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return getContent((data) -> data < 0x80);
    }

    public synchronized String getContent(final Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new FileInputStream(file)) {
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(i, 200)) {
                int data;
                while ((data = bufferedInputStream.read()) != -1) {
                    if (filter.test((char) data)) {
                        output.append((char) data);
                    }
                }
            }
        }
        return output.toString();
    }
}
