package com.dusinski.aoc.solutions.day3;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.ArrayList;
import java.util.List;

public class BinaryDiagnostic {

    public static int getPowerConsumption() {
        List<String> instrList = DataLoadUtil.loadStringList("day3.txt");
        List<Integer> integerList = new ArrayList<>();
        instrList.forEach(s -> integerList.add(Integer.parseInt(s, 2)));

        // find occurrences of 1
        int[] bitSumArray = getBitOccurrenceArray(integerList, instrList.get(0).length());
        int gammaParam = getGamma(bitSumArray, instrList.size());
        int epsilonParam = getEpsilon(bitSumArray, instrList.size());


        return gammaParam * epsilonParam;
    }

    public  static int  getLifeSupportRating(){
        return getOxygenRating()*geCO2Rating();
    }

    public static int getOxygenRating() {
        List<String> instrList = DataLoadUtil.loadStringList("day3.txt");
        List<Integer> integerList = new ArrayList<>();
        instrList.forEach(s -> integerList.add(Integer.parseInt(s, 2)));
        int[] bitSumArray = getBitOccurrenceArray(integerList, instrList.get(0).length());
//        System.out.println(instrList);

        int bitPosition = 0;
        while (integerList.size() > 1 && bitPosition < bitSumArray.length) {
            int currentBit = isMostCommonBit(bitSumArray, integerList.size(), bitPosition);
            int currentPosition = bitSumArray.length - bitPosition - 1;
            integerList.removeIf(s -> checkBitOnPos(s, currentPosition) != currentBit);
            bitPosition++;

            //clearing occurrence array to have ony existing nubers
            bitSumArray = getBitOccurrenceArray(integerList, instrList.get(0).length());


            List<String> tempBinary = new ArrayList<>();
            integerList.forEach(s -> tempBinary.add(Integer.toBinaryString(s)));
        }
        return integerList.get(0);
    }

    public static int geCO2Rating() {
        List<String> instrList = DataLoadUtil.loadStringList("day3.txt");
        List<Integer> integerList = new ArrayList<>();
        instrList.forEach(s -> integerList.add(Integer.parseInt(s, 2)));
        int[] bitSumArray = getBitOccurrenceArray(integerList, instrList.get(0).length());
        int bitPosition = 0;
        while (integerList.size() > 1 && bitPosition < bitSumArray.length) {
            int currentBit = isMostLeastBit(bitSumArray, integerList.size(), bitPosition);
            int currentPosition = bitSumArray.length - bitPosition - 1;
            integerList.removeIf(s -> checkBitOnPos(s, currentPosition) != currentBit);
            bitPosition++;

            //clearing occurrence array to have ony existing numbers
            bitSumArray = getBitOccurrenceArray(integerList, instrList.get(0).length());


            List<String> tempBinary = new ArrayList<>();
            integerList.forEach(s -> tempBinary.add(Integer.toBinaryString(s)));
        }
        return integerList.get(0);
    }



    private static int isMostCommonBit(int[] bitSumArray, int elementsCount, int position) {
        double middleVal = elementsCount;
        middleVal = middleVal / 2;
        if (bitSumArray[position] >= middleVal) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int isMostLeastBit(int[] bitSumArray, int elementsCount, int position) {
        double middleVal = elementsCount;
        middleVal = middleVal / 2;
        if (bitSumArray[position] < middleVal) {
            return 1;
        } else {
            return 0;
        }
    }

    private static int[] getBitOccurrenceArray(List<Integer> integerInputList, int arraySize) {
        int[] bitSumArray = new int[arraySize];
        for (int i = 0; i < bitSumArray.length; i++) {
            int finalI = bitSumArray.length - i - 1;
            bitSumArray[i] = integerInputList.stream().reduce(0, (subtotal, element) -> subtotal + checkBitOnPos(element, finalI));
        }
        return bitSumArray;
    }


    private static int getGamma(int[] occurrenceArray, int elementsCount) {
        int gammaParam = 0;
        for (int i = 0; i < occurrenceArray.length; i++) {
            gammaParam = gammaParam << 1;
            if (occurrenceArray[i] > elementsCount / 2) {
                gammaParam++;
            }
        }
        return gammaParam;
    }

    private static int getEpsilon(int[] occurrenceArray, int elementsCount) {
        int epsilonParam = 0;
        for (int i = 0; i < occurrenceArray.length; i++) {
            epsilonParam = epsilonParam << 1;
            if (occurrenceArray[i] < elementsCount / 2) {
                epsilonParam++;
            }
        }
        return epsilonParam;
    }

    private static int checkBitOnPos(int number, int bitPosition) {
        int op = (number >> bitPosition) & 1;
        return (number >> bitPosition) & 1;
    }
}
