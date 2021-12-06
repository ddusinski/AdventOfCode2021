package com.dusinski.aoc.solutions.day5;

public class Instruction {
    public CooPoint startPoint;
    public CooPoint endPoint;

    public Instruction(String inputString) {
        String[] splitString = inputString.split(" -> ");
        String x1 = splitString[0].split(",")[0];
        String y1 = splitString[0].split(",")[1];
        String x2 = splitString[1].split(",")[0];
        String y2 = splitString[1].split(",")[1];

        this.startPoint = new CooPoint(Integer.parseInt(x1), Integer.parseInt(y1));
        this.endPoint = new CooPoint(Integer.parseInt(x2), Integer.parseInt(y2));
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }

    public static class CooPoint {
        private int x;
        private int y;

        public CooPoint() {
        }

        public CooPoint(int a, int b) {
            this.x = a;
            this.y = b;
        }

        @Override
        public String toString() {
            return "CooPoint{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
