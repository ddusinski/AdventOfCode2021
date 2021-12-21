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
        List<String> lines = loadStringList(resourceName);
        List<Long> numbers = new ArrayList<>();
        lines.forEach(s -> numbers.add(Long.parseLong(s)));
        return numbers;
    }

    public static List<String> loadStringList(String resourceName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream(resourceName);
        if (input != null) {
            try {
                List<String> lines = IOUtils.readLines(input, "UTF-8");
                return lines;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String loadStringLine(String resourceName) {
        return loadLine(resourceName);
    }

    private static String loadLine(String resourceName) {
        String result;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream(resourceName);
        if (input != null) {
            try {
                String string = IOUtils.toString(input, StandardCharsets.UTF_8);

                return string;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Integer> loadDelimitedStringToList(String resourceName) {
        String string = loadLine(resourceName);
        String[] delimitedStringArray = string.split(",");
        List<String> strings = Arrays.asList(delimitedStringArray);
        List<Integer> numbers = new ArrayList<>();
        strings.forEach(s -> {
                    numbers.add(Integer.parseInt(s));
                }
        );

        return numbers;
    }


//    public static List<Integer> loadDelimitedStringToList(String resourceName) {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        InputStream input = classLoader.getResourceAsStream(resourceName);
//        if (input != null) {
//            try {
//                String string = IOUtils.toString(input, StandardCharsets.UTF_8);
//                String[] delimitedStringArray = string.split(",");
//                List<String> strings = Arrays.asList(delimitedStringArray);
//                List<Integer> numbers = new ArrayList<>();
//                strings.forEach(s -> {
//                            numbers.add(Integer.parseInt(s));
//                        }
//                );
//                return numbers;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

}
