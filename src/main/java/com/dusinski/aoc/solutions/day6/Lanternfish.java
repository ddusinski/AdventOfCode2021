package com.dusinski.aoc.solutions.day6;


import com.dusinski.aoc.util.DataLoadUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Lanternfish {
    List<Fish> fishList = new ArrayList<>();
    int maxDay;

    public Lanternfish() {
        List<Integer> fishAgeInput = DataLoadUtil.loadDelimitedStringToList("day6.txt");
        fishAgeInput.forEach(s -> this.fishList.add(new Fish(s)));
    }

    public int getGenNumAfter80days() {
        for (int i = 0; i < 80; i++) {
            this.fishList = calcDay(this.fishList);
        }
        return this.fishList.size();
    }

    private List<Fish> calcDay(List<Fish> fList) {
        int newFishCount = 0;
        for (Fish f : fList) {
            if (f.age == 0) {
                newFishCount++;
                f.age = 7;
            }
            f.age--;
        }
        if (newFishCount > 0) {
            for (int i = 0; i < newFishCount; i++) {
                fList.add(new Fish(8));
            }
        }
        return fList;
    }

    // recursive approach
    public long getIterative(int n) {
        long fishSum = 0;
        this.maxDay = n;

        Map<Integer, Long> sumMap = new LinkedHashMap<>();

        for (Fish f : this.fishList) {

            if (sumMap.containsKey(f.age)) {
                fishSum += sumMap.get(f.age);
            } else {
                long result = getFishNumber(0, f.age);
                fishSum += result;
                sumMap.put(f.age, result);
//                System.out.println("added result for: "+f.age);
            }
        }
        return fishSum;
    }

    private long getFishNumber(int currentDay, int fishAge) {
        if (currentDay >= this.maxDay) {
            return 1;
        }
        if (fishAge == 0) {
            return getFishNumber(currentDay + 1, 6) + getFishNumber(currentDay + 1, 8);
        } else if (fishAge == 6) {
            return getFishNumber(currentDay + 5, fishAge - 5);
        } else {
            return getFishNumber(currentDay + 1, fishAge - 1);
        }
    }

    private class Fish {
        int age;

        public Fish(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "age=" + age +
                    '}';
        }
    }


}
