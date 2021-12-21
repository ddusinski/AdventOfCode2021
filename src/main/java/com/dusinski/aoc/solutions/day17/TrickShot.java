package com.dusinski.aoc.solutions.day17;

import com.dusinski.aoc.util.DataLoadUtil;

import java.awt.*;
import java.util.List;
import java.util.*;

public class TrickShot {
    int targetMinX;
    int targetMaxX;
    int targetMinY;
    int targetMaxY;

    public TrickShot() {
        String input = DataLoadUtil.loadStringLine("day17.txt");
        input = input.replaceAll("target area: ", "");
        String xPart = input.split(",")[0];
        xPart = xPart.replaceAll("x=", "");
        targetMinX = Integer.parseInt(xPart.split("\\.\\.")[0]);
        targetMaxX = Integer.parseInt(xPart.split("\\.\\.")[1]);
        String yPart = input.split(",")[1];
        yPart = yPart.replaceAll(" y=", "");
        targetMinY = Integer.parseInt(yPart.split("\\.\\.")[0]);
        targetMaxY = Integer.parseInt(yPart.split("\\.\\.")[1]);
    }

    public int getMaxHigh() {
        ShootPosition shootPosition = new ShootPosition(this.targetMinX, this.targetMaxX, this.targetMinY, this.targetMaxY);
        int maxVy = Math.abs(shootPosition.tMinY) - 1;
        return shootPosition.getVyDistanceBelowZero(maxVy, maxVy + 1);
    }

    public int getPossibleVelCount() {
        ShootPosition shootPosition = new ShootPosition(this.targetMinX, this.targetMaxX, this.targetMinY, this.targetMaxY);
        List<ShootPosition.VxInitialValues> vxList = shootPosition.genVxInitialValuesList();
        Set<Point> getVxVyListForLastOne = shootPosition.getVxVyListForLastOne(vxList);
        Set<Point> getVxVyListNOTLastOneDOWN = shootPosition.getVxVyListNOTLastOneDOWN(vxList);
        Set<Point> getVxVyListNOTLastOneUP = shootPosition.getVxVyListNOTLastOneUP(vxList);
        Set<Point> getVxVyListForLastOneValBelowZero = shootPosition.getVxVyListForLastOneValBelowZero(vxList);
        Set<Point> vXvYlist = new HashSet<>();
        vXvYlist.addAll(getVxVyListForLastOne);
        vXvYlist.addAll(getVxVyListNOTLastOneDOWN);
        vXvYlist.addAll(getVxVyListNOTLastOneUP);
        vXvYlist.addAll(getVxVyListForLastOneValBelowZero);

        return vXvYlist.size();
    }

    @Override
    public String toString() {
        return "TrickShot{" +
                "targetMinX=" + targetMinX +
                ", targetMaxX=" + targetMaxX +
                ", targetMinY=" + targetMinY +
                ", targetMaxY=" + targetMaxY +
                '}';
    }


    public class ShootPosition {
        public Point currentPosition;
        public int xVelMin;
        public int xVelMax;
        int tMinX;
        int tMaxX;
        int tMinY;
        int tMaxY;

        public ShootPosition(int tMinX, int tMaxX, int tMinY, int tMaxY) {
            this.currentPosition = new Point(0, 0);
            this.tMinX = tMinX;
            this.tMaxX = tMaxX;
            this.tMinY = tMinY;
            this.tMaxY = tMaxY;
            this.xVelMax = tMaxX;
            getXVelMinMax();
        }

        private void getXVelMinMax() {
            this.xVelMax = this.tMaxX;
            double c = this.tMinX * 2;

            double deltaSqrt = Math.sqrt(1 + 4 * c);
            this.xVelMin = (int) Math.round((-1 + deltaSqrt) / 2);
        }

        private List<VxInitialValues> genVxInitialValuesList() {
            List<VxInitialValues> list = new ArrayList<>();
            for (int i = this.xVelMin; i <= this.xVelMax; i++) {
                int currentVx = i;
                int n = 1;  // step
                int currentX = getVxDistance(currentVx, n);

                while (currentX > 0) {
                    if (currentX <= this.tMaxX && currentX >= this.tMinX) {
                        list.add(new VxInitialValues(currentVx, n));
                    }
                    n++;
                    currentX = getVxDistance(currentVx, n);
                }
            }
            return list;
        }

        private int getVxDistance(int vX, int n) {
            int dis;
            if (vX + 1 == n) {
                dis = 0;
            } else
                dis = (2 * vX - n + 1) * n / 2;
            return dis;
        }

        private int getVyDistance(int vY, int n) {
            return -(2 * vY + n - 1) * n / 2;
        }

        private int getVyDistanceFromZero(int vY, int n) {
            return (-2 * vY - n + 1) * n / 2;
        }

        private int getVyDistanceBelowZero(int vY, int n) {
            return (2 * vY - n + 1) * n / 2;
        }

        public Set<Point> getVxVyListForLastOneValBelowZero(List<VxInitialValues> vxInitialValues) {
            Set<Point> vList = new HashSet<>();
            for (VxInitialValues vxIV : vxInitialValues) {
                if (vxIV.isLastOne) {                   // here calc all possible vy when end of vx reached
                    double yVelMin = (vxIV.stepNum - 1); // have to find yVel min which reach x below y=0;
                    yVelMin = yVelMin / 2;
                    for (int i = 0; i < (int) Math.ceil(yVelMin); i++) {
                        int n = vxIV.stepNum;
                        int currentY = getVyDistanceBelowZero(i, n);
                        while (currentY >= this.tMinY) {
                            if (currentY <= this.tMaxY) {
                                vList.add(new Point(vxIV.vX, i));
                            }
                            n++;
                            currentY = getVyDistanceBelowZero(i, n);
                        }
                    }
                }
            }
            return vList;
        }

        public Set<Point> getVxVyListForLastOne(List<VxInitialValues> vxInitialValues) {
            Set<Point> vList = new HashSet<>();
            for (VxInitialValues vxIV : vxInitialValues) {
                if (vxIV.isLastOne) {                   // here calc all possible vy when end of vx reached
                    int yVelMax = -this.tMinY;
                    double yVelMin = (vxIV.stepNum - 1); // have to find yVel min which reach x above y=0;
                    yVelMin = yVelMin / 2;
                    for (int i = (int) Math.ceil(yVelMin); i < yVelMax; i++) {
                        int n = 0;
                        int currentY = getVyDistanceFromZero(i, n);
                        while (currentY >= this.tMinY) {
                            if (currentY <= this.tMaxY) {
                                vList.add(new Point(vxIV.vX, i));
                            }
                            n++;
                            currentY = getVyDistanceFromZero(i, n);
                        }
                    }
                }
            }
            return vList;
        }

        public Set<Point> getVxVyListNOTLastOneDOWN(List<VxInitialValues> vxInitialValues) {
            Set<Point> vList = new HashSet<>();
            for (VxInitialValues vxIV : vxInitialValues) {
                if (!vxIV.isLastOne) {
                    int currentY = vxIV.yPos;
                    int currentVy = 0;
                    while (currentY >= this.tMinY) {
                        if (currentY <= this.tMaxY) {
                            vList.add(new Point(vxIV.vX, currentVy));
                        }
                        currentVy--;
                        currentY = vxIV.yPos + currentVy * vxIV.stepNum;
                    }
                }
            }
            return vList;
        }

        public Set<Point> getVxVyListNOTLastOneUP(List<VxInitialValues> vxInitialValues) {
            Set<Point> vList = new HashSet<>();
            for (VxInitialValues vxIV : vxInitialValues) {
                if (!vxIV.isLastOne) {
                    int currentY = vxIV.yPos;
                    int currentVy = 0;
                    while (currentY <= this.tMaxY) {
                        if (currentY >= this.tMinY) {
                            vList.add(new Point(vxIV.vX, currentVy));
                        }
                        currentVy++;
                        currentY = vxIV.yPos + currentVy * vxIV.stepNum;
                    }
                }
            }
            return vList;
        }

        @Override
        public String toString() {
            return "ShootPosition{" +
                    "currentPosition=" + currentPosition +
                    ", xVelMin=" + xVelMin +
                    ", xVelMax=" + xVelMax +
                    ", tMinX=" + tMinX +
                    ", tMaxX=" + tMaxX +
                    ", tMinY=" + tMinY +
                    ", tMaxY=" + tMaxY +
                    '}';
        }

        private class VxInitialValues {
            public final int stepNum;
            public final int vX;
            public final int xPos;
            public final int yPos;
            public boolean isLastOne;

            public VxInitialValues(int vX, int step) {
                this.stepNum = step;
                this.vX = vX;
                this.xPos = getVxDistance(vX, step);
                this.yPos = getVyDistance(0, step);
                if (vX == step) {
                    this.isLastOne = true;
                }
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof VxInitialValues)) return false;
                VxInitialValues that = (VxInitialValues) o;
                return stepNum == that.stepNum && vX == that.vX;
            }

            @Override
            public int hashCode() {
                return Objects.hash(stepNum, vX);
            }

            @Override
            public String toString() {
                return "VxInitialValues{" +
                        "vX=" + vX +
                        ", stepNum=" + stepNum +
                        ", xPos=" + xPos +
                        ", yPos=" + yPos +
                        ", isLastOne=" + isLastOne +
                        '}';
            }
        }
    }
}