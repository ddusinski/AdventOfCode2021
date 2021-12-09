package com.dusinski.aoc.solutions.day8;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.*;
import java.util.stream.Collectors;

public class SevenSegmentSearch {
    List<SignalEntry> signalList = new ArrayList<>();

    public SevenSegmentSearch() {
        List<String> input = DataLoadUtil.loadStringList("day8.txt");
        input.forEach(s -> this.signalList.add(new SignalEntry(s)));
    }

    public int getEasyDigitsPart1() {
        int dictCount = 0;
        for (SignalEntry se : this.signalList) {
            dictCount += se.getEasyDigitNumFromSignal();
        }
        return dictCount;
    }


    public int getEasyDigitsPart2SET() {
        int sum = 0;
        for (SignalEntry se : this.signalList) {
            sum += se.getSignalSum();
        }
        return sum;
    }

    private static class SignalEntry {
        public List<String> signalPattern = new ArrayList<>();
        public List<String> digitOutput = new ArrayList<>();
        public Map<Integer, Set<Character>> digitSignalSetMap = new LinkedHashMap<>();
        public List<Set<Character>> signalPatternListSet = new ArrayList<>();
        public List<Set<Character>> digitOutputListSet = new ArrayList<>();

        public SignalEntry(String input) {
            String sPattern = input.split(" \\| ")[0];
            String dOutput = input.split(" \\| ")[1];
            String[] sPatternArray = sPattern.split(" ");
            String[] dOutputArray = dOutput.split(" ");
            this.signalPattern.addAll(Arrays.asList(sPatternArray));
            this.digitOutput.addAll(Arrays.asList(dOutputArray));

            for (String s : sPatternArray) {
                Set<Character> characterSet = s.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
                this.signalPatternListSet.add(characterSet);
            }
            for (String s : dOutputArray) {
                Set<Character> characterSet = s.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
                this.digitOutputListSet.add(characterSet);
            }
        }

        public int getEasyDigitNumFromSignal() {
            int easyDigitCount = 0;
            for (String output : this.digitOutput) {
                int len = output.length();
                if (len == 2 || len == 3 || len == 4 || len == 7) {
                    easyDigitCount++;
                }
            }
            return easyDigitCount;
        }

        public void getSignalSetMap() {
            getBasicDigitsSET(1, 2);
            getBasicDigitsSET(7, 3);
            getBasicDigitsSET(8, 7);
            getBasicDigitsSET(4, 4);
            getDigitSET(3, 5, 1, 3); // find 3
            getDigitSET(2, 5, 4, 3); // find 2
            getDigitSET(5, 5, 4, 2); // find 5
            getDigitSET(6, 6, 1, 5); // find 6
            getDigitSET(9, 6, 4, 2); // find 9
            getDigitSET(0, 6, 4, 3); // find 0
        }

        public int getSignalSum() {
            int base = 0;
            getSignalSetMap();
            for (Set output : this.digitOutputListSet) {
                base = base * 10 + returnNumberFromSignalSet(output);
            }
            return base;
        }

        private int returnNumberFromSignalSet(Set digit) {
            int result = 0;
            for (Map.Entry<Integer, Set<Character>> mapEntry : this.digitSignalSetMap.entrySet()) {
                if (digit.equals(mapEntry.getValue())) {
                    result = mapEntry.getKey();
                }
            }
            return result;
        }

        private void getBasicDigitsSET(int digit, int signals) {
            for (Set<Character> set : this.signalPatternListSet) {
                if (set.size() == signals) {
                    this.digitSignalSetMap.put(digit, set);
                }
            }
            this.signalPatternListSet.remove(this.digitSignalSetMap.get(digit));
        }

        private void getDigitSET(int digit, int digitSize, int comparingNumber, int digitSizeAfterCutting) {
            for (Set<Character> d : this.signalPatternListSet) {
                if (d.size() == digitSize) {
                    LinkedHashSet<Character> testedDigit = new LinkedHashSet<>(d);
                    testedDigit.removeAll(this.digitSignalSetMap.get(comparingNumber));
                    if (testedDigit.size() == digitSizeAfterCutting) {
                        this.digitSignalSetMap.put(digit, d);
                    }
                }
            }
            this.signalPatternListSet.remove(this.digitSignalSetMap.get(digit));
        }

        @Override
        public String toString() {
            return "SignalEntry{" +
                    "digitSignalSetMap=" + digitSignalSetMap +
                    ", signalPatternListSet=" + signalPatternListSet +
                    ", digitOutputListSet=" + digitOutputListSet +
                    '}';
        }
    }
}