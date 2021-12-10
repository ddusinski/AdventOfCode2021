package com.dusinski.aoc.solutions.day10;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

class NavSysLine {

    List<Chunk> chunkList;
    char illegalCharacter = '0';
    boolean isIllegal = false;
    long lineScore = 0;

    public NavSysLine(String input) {
        this.chunkList = new ArrayList<>();
        List<Character> charList = input.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        charList.forEach(s -> this.chunkList.add(new Chunk(s)));
    }

    public int getErrorScore() {
        this.illegalCharacter = getIllegalCharacter();
        switch (this.illegalCharacter) {
            case ')':
                return 3;
            case ']':
                return 57;
            case '}':
                return 1197;
            case '>':
                return 25137;
            default:
                return 0;
        }
    }

    //this function stops when first illegal is found
    private char getIllegalCharacter() {
        Stack<Chunk> chunkStack = new Stack<>();
        for (Chunk c : this.chunkList) {
            if (c.isOpen) {
                chunkStack.push(c);
            } else {
                if (Chunk.isChunkGroup(chunkStack.peek(), c)) {
                    chunkStack.pop();
                } else {
                    this.isIllegal = true;
                    return c.val;
                }
            }
        }
        return '0';
    }

    //this function scans entire stack
    public void removeOccurrences() {
        Stack<Chunk> chunkStack = new Stack<>();
        for (Chunk c : this.chunkList) {
            if (c.isOpen) {
                chunkStack.push(c);
            } else {
                if (Chunk.isChunkGroup(chunkStack.peek(), c)) {
                    chunkStack.pop();
                } else {
                    this.isIllegal = true;
                }
            }
        }
        this.lineScore = calcScore(chunkStack);
    }

    private long calcScore(Stack<Chunk> input) {
        long score = 0;
        while (!input.isEmpty()) {
            Chunk cChunk = input.pop();
            switch (cChunk.val) {
                case '(':
                    score = score * 5 + 1;
                    break;
                case '[':
                    score = score * 5 + 2;
                    break;
                case '{':
                    score = score * 5 + 3;
                    break;
                case '<':
                    score = score * 5 + 4;
                    break;
            }
        }
        return score;
    }

    @Override
    public String toString() {
        return "NavSysLine{" +
                "chunkList=|" + chunkList +
                '|';
    }

    private static class Chunk {
        Character val;
        boolean isOpen;

        public Chunk(Character val) {
            this.val = val;
            this.isOpen = isOpenCheck(val);
        }

        private static boolean isChunkGroup(Chunk start, Chunk end) {
            return (start.val == '(' && end.val == ')') || (start.val == '<' && end.val == '>')
                    || (start.val == '{' && end.val == '}') || (start.val == '[' && end.val == ']');
        }

        private boolean isOpenCheck(char val) {
            return val == '<' || val == '{' || val == '[' || val == '(';
        }

        @Override
        public String toString() {
            return val + "";
        }
    }
}


