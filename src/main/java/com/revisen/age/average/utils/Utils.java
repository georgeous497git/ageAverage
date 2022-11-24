package com.revisen.age.average.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class Utils {

    private Utils () {}

    public static void writeResultFiles(String fileName, List<SortedMap<Integer, String>> data) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int max = data.size();
        AtomicInteger count = new AtomicInteger(0);

        data.forEach(s -> {
                printWriter.printf("==> Execution %d of %d %n", count.addAndGet(1), max);
                if (s.isEmpty()) {
                    printWriter.println("The is not data to print.");
                }
                s.forEach((key, value) -> {
                    printWriter.printf("%s is %d years old %n", value, key);
                });
            }
        );
        printWriter.close();
    }
}
