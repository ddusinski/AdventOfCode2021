package com.dusinski.aoc.solutions.day14;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PolymerTemplate {
    public String polymerTemplateString;
    public List<InsertionRule> insertionRuleList;

    public PolymerTemplate(List<String> input) {
        this.polymerTemplateString = input.get(0);
        this.insertionRuleList = new ArrayList<>();
        for (int i = 2; i < input.size(); i++) {
            insertionRuleList.add(new InsertionRule(input.get(i)));
        }
    }

    public String calculateTemplateString(String s
    ) {
//        String s = this.polymerTemplateString;
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        List<Character> charLinkedList = s.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        for (int i = 0; i < charLinkedList.size() - 1; i++) {
//        for (int i = 0; i < s.length() - 1; i++) {
            char insertion = checkRules(charLinkedList.get(i), charLinkedList.get(i + 1));
//            char insertion = checkRules(s.charAt(i), s.charAt(i + 1));

            if (insertion != '-') {
                sb.append(insertion);
//                s = s.substring(0,i)+insertion+s.substring(i,s.length());

            }
            sb.append(charLinkedList.get(i + 1));

//            sb.append(s.charAt(i+1));
        }
        return sb.toString();
    }


    public List<String> calculateTemplateStringVER2(List<String> s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.get(0).charAt(0));
        List<String> helpStringList = new ArrayList<>();


        for (int j = 0; j < s.size(); j++) {
            for (int i = 0; i < s.get(j).length() - 1; i++) {
                char insertion = checkRules(s.get(j).charAt(i), s.get(j).charAt(i + 1));
                if (insertion != '-') {
                    sb.append(insertion);
                }
                if (sb.length() > 71755102) {
                    helpStringList.add(sb.toString());
                    sb.setLength(0);
                }
                sb.append(s.get(j).charAt(i + 1));
            }
            if (j < s.size() - 1) {
                char insertion = checkRules(s.get(j).charAt(s.get(j).length()-1), s.get(j+1).charAt(0));
                if (insertion != '-') {
                    sb.append(insertion);
                }
            }

        }


        helpStringList.add(sb.toString());
        sb.setLength(0);
//        s = sb.toString();
//        System.out.println("SB length: " + sb.length());
//        System.out.println("helpStringList: " + helpStringList);
        return helpStringList;
    }


    private char checkRules(char s, char e) {
        for (InsertionRule iRule : this.insertionRuleList) {
            if (iRule.s == s && iRule.e == e) {
                return iRule.insertion;
            }
        }
        return '-';
    }

    public long getMinMaxQuantity(String input) {
        Map<Character, Long> occurrenceMap =
                input.chars().mapToObj(e -> (char) e).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(occurrenceMap);
        Long min = Collections.min(occurrenceMap.values());
        Long max = Collections.max(occurrenceMap.values());
        return max - min;
    }

    public long getMinMaxQuantityFromList(List<String> input) {
        Map<Character, Long> occurrenceResultMap = new LinkedHashMap<>();
        for (String s : input) {
            Map<Character, Long> tempOccurrenceMap =
                    s.chars().mapToObj(e -> (char) e).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            tempOccurrenceMap.forEach((k, v) -> occurrenceResultMap.merge(k, v, Long::sum));
        }
        System.out.println(occurrenceResultMap);
        Long min = Collections.min(occurrenceResultMap.values());
        Long max = Collections.max(occurrenceResultMap.values());
        return max - min;
    }


    @Override
    public String toString() {
        return "PolymerTemplate{" +
                "polymerTemplateString='" + polymerTemplateString + '\'' +
                ", insertionRuleList=" + insertionRuleList +
                '}';
    }


    private class InsertionRule {
        public char s;
        public char e;
        public char insertion;

        public InsertionRule(String i) {
//             CH -> B
            String[] a = i.split(" -> ");
            this.s = a[0].charAt(0);
            this.e = a[0].charAt(1);
            this.insertion = a[1].charAt(0);
        }

        @Override
        public String toString() {
            return "InsertionRule{" +
                    "s=" + s +
                    ", e=" + e +
                    ", insertion=" + insertion +
                    '}';
        }
    }


}
