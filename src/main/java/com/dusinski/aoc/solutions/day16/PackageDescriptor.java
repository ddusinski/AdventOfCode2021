package com.dusinski.aoc.solutions.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PackageDescriptor {
    public String hexadecimalTransmissionString;
    public String binaryTransmissionString;
    public List<Package> packagesList = new ArrayList<>();

    public PackageDescriptor(String input) {
        this.hexadecimalTransmissionString = input;
        this.binaryTransmissionString = convertToBinary();
        this.packagesList.add(new Package(this.binaryTransmissionString));
    }

    public int getVerSum() {
        int result = 0;
        for (Package p : this.packagesList) {
            result = result + p.getVSum();
        }
        return result;
    }


    private String convertToBinary() {
        StringBuilder sb = new StringBuilder();
        List<Character> characterList;
        characterList = this.hexadecimalTransmissionString.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        for (Character transChar : characterList) {
            switch (transChar) {
                case '0':
                    sb.append("0000");
                    break;
                case '1':
                    sb.append("0001");
                    break;
                case '2':
                    sb.append("0010");
                    break;
                case '3':
                    sb.append("0011");
                    break;
                case '4':
                    sb.append("0100");
                    break;
                case '5':
                    sb.append("0101");
                    break;
                case '6':
                    sb.append("0110");
                    break;
                case '7':
                    sb.append("0111");
                    break;
                case '8':
                    sb.append("1000");
                    break;
                case '9':
                    sb.append("1001");
                    break;
                case 'A':
                    sb.append("1010");
                    break;
                case 'B':
                    sb.append("1011");
                    break;
                case 'C':
                    sb.append("1100");
                    break;
                case 'D':
                    sb.append("1101");
                    break;
                case 'E':
                    sb.append("1110");
                    break;
                case 'F':
                    sb.append("1111");
                    break;
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "PackageDescriptor{" +
                "hexadecimalTransmissionString='" + hexadecimalTransmissionString + '\'' +
                ", binaryTransmissionString='" + binaryTransmissionString + '\'' +
                ", packagesList=" + packagesList +
                '}';
    }

//    The three bits labeled V (110) are the packet version, 6.
//    The three bits labeled T (100) are the packet type ID, 4, which means the packet is a literal value.
//    The five bits labeled A (10111) start with a 1 (not the last group, keep reading) and contain the first four bits of the number, 0111.
//    The five bits labeled B (11110) start with a 1 (not the last group, keep reading) and contain four more bits of the number, 1110.
//    The five bits labeled C (00101) start with a 0 (last group, end of packet) and contain the last four bits of the number, 0101.
//    The three unlabeled 0 bits at the end are extra due to the hexadecimal representation and should be ignored.


    public class Package {
        String initialString;
        String V; // package version
        int intV;
        String T; //package type
        int intT;
        List<String> stringGroupList = new ArrayList<>();
        String literalVal;
        int decimalVal;

        String I; // length type - when 0 next 15 bits are length of subpackets when 1 next 11 bits are nr of subpackets
        String L;
        int intL;
        List<Package> subpackageList = new ArrayList<>();

        public Package(String decimal) {

            this.V = decimal.substring(0, 3);
            this.intV = Integer.parseInt(this.V, 2);
            this.T = decimal.substring(3, 6);
            this.intT = Integer.parseInt(this.T, 2);
            this.initialString = this.V + this.T;

            if (this.intT == 4) {
                readGroups(decimal.substring(6));
                getValues();
            }
            if (this.intT != 4) {
                this.I = decimal.substring(6, 7);
                if (this.I.equals("0")) {
                    this.L = decimal.substring(7, 22);
                    this.intL = Integer.parseInt(this.L, 2);
                    String subpackageString = decimal.substring(22, 22 + this.intL);
                    while (subpackageString.length() > 0) {
                        Package newPackage = new Package(subpackageString);
                        this.subpackageList.add(newPackage);
                        subpackageString = subpackageString.substring(newPackage.initialString.length());
                    }

                }
                if (this.I.equals("1")) {
                    this.L = decimal.substring(7, 18);
                    this.intL = Integer.parseInt(this.L, 2);
                    String subpackageString = decimal.substring(18);
                    for (int i = 0; i < this.intL; i++) {
                        Package newPackage = new Package(subpackageString);
                        this.subpackageList.add(newPackage);
                        subpackageString = subpackageString.substring(newPackage.initialString.length());
                    }
                }
                int a = 1;
            }
        }

        public int getVSum() {
            int result = this.intV;
            for (Package p : this.subpackageList) {
                result = result + p.getVSum();
            }
            return result;
        }

        private void readGroups(String decInput) {
            String subString = decInput;
            while (subString.charAt(0) == '1') {
                this.initialString = this.initialString + subString.substring(0, 5);
                this.stringGroupList.add(subString.substring(1, 5));
                subString = subString.substring(5);
            }
            this.initialString = this.initialString + subString.substring(0, 5);
            this.stringGroupList.add(subString.substring(1, 5)); // adds zero group

        }

        public int getDecimalVal() {
            return decimalVal;
        }

        private void getValues() {
            this.literalVal = String.join("", this.stringGroupList);
            this.decimalVal = Integer.parseInt(this.literalVal, 2);
        }

        @Override
        public String toString() {
            return "Package{" +
                    "V='" + V + '\'' +
                    ", intV=" + intV +
                    ", T='" + T + '\'' +
                    ", intT=" + intT +
                    ", stringGroupList=" + stringGroupList +
                    ", literalVal='" + literalVal + '\'' +
                    ", decimalVal=" + decimalVal +
                    '}';
        }
    }


}
