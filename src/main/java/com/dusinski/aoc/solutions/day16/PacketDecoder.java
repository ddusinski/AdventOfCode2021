package com.dusinski.aoc.solutions.day16;

import com.dusinski.aoc.util.DataLoadUtil;


public class PacketDecoder {


    public PacketDecoder() {
//        String input = DataLoadUtil.loadStringLine("day16.txt");
        String input = DataLoadUtil.loadStringLine("test3a.txt");
        PackageDescriptor packageDescriptor = new PackageDescriptor(input);
        System.out.println(packageDescriptor.hexadecimalTransmissionString);
        System.out.println(packageDescriptor.binaryTransmissionString);
        System.out.println(packageDescriptor);
        System.out.println(packageDescriptor.packagesList.get(0).decimalVal);
        System.out.println("ver sum: "+packageDescriptor.getVerSum());

    }


}
