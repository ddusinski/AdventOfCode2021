package com.dusinski.aoc.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoadUtil {
    public static List<Long> loadLongList(String resourceName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream(resourceName);
        if (input != null) {
            try {
                List<String> lines = IOUtils.readLines(input, "UTF-8");
                List<Long> numbers = new ArrayList<>();
                lines.forEach(s -> numbers.add(Long.parseLong(s)));
                return numbers;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Integer> loadDelimitedStringToList(String resourceName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream(resourceName);
        if (input != null) {
            try {
                String string = IOUtils.toString(input, StandardCharsets.UTF_8);
                String[] delimitedStringArray = string.split(",");
                List<String> strings = Arrays.asList(delimitedStringArray);
                List<Integer> numbers = new ArrayList<>();
                strings.forEach(s -> {
                        numbers.add(Integer.parseInt(s));
                }
                );
                return numbers;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
