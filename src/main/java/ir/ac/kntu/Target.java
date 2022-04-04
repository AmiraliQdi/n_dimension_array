package ir.ac.kntu;

import java.util.ArrayList;

public class Target {

    private ArrayList<Integer> address;

    private String block;

    private Array array;

    private int startOfBlock;

    private int endOfBlock;

    Target(ArrayList<Integer> address, Array array) {
        this.address = address;
        this.array = array;
        this.endOfBlock = 0;
        this.startOfBlock = 0;
        this.block = findTarget();
    }

    private String findTarget() {
        String tempBlock = array.getArrayInput();
        for (int i = 0; i < address.size() - 1; i++) {
            int numberOfAddress = address.get(i);
            tempBlock = findBlock(tempBlock, numberOfAddress);
        }
        return findInsideBlock(tempBlock, address.get(address.size() - 1));
    }

    private String findInsideBlock(String input, int address) {
        int nextCamma, startCamma = -1;
        if (address == 0) {
            nextCamma = findNextCamma(input, 0);
            startOfBlock += 1;
            endOfBlock += nextCamma - 1;
            return input.substring(1, nextCamma);
        }
        int counter = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ',') {
                counter++;
            }
            if (counter == address) {
                startCamma = i;
                startOfBlock += i + 1;
                break;
            }
        }
        nextCamma = findNextCamma(input, startCamma + 1);
        endOfBlock += nextCamma - 1;
        return input.substring(startCamma + 1, nextCamma);
    }

    private int findNextCamma(String input, int start) {
        for (int i = start; i < input.length(); i++) {
            if (input.charAt(i) == ',' || input.charAt(i) == ']') {
                return i;
            }
        }
        return -1;
    }

    private String findBlock(String input, int address) {
        int counter = 0, start = -1, index = 1;
        if (address == 0) {
            start = 1;
        }
        for (int j = 0; j < address; j++) {
            boolean first = true;
            for (int i = index; i < input.length(); i++) {
                if (i > index) {
                    first = false;
                }
                if (input.charAt(i) == '[') {
                    counter++;
                } else if (input.charAt(i) == ']') {
                    counter--;
                }
                if (counter == 0 && !first) {
                    index = i + 2;
                    break;
                }
            }
            start = index;
        }
        startOfBlock += start;
        return cutBlock(input, start);
    }

    private String cutBlock(String input, int start) {
        boolean isFirst = true;
        int counter = 0;
        int end = -1;
        endOfBlock = start;
        for (int i = start; i < input.length(); i++) {
            if (i > start) {
                isFirst = false;
            }
            if (input.charAt(i) == '[') {
                counter++;
            } else if (input.charAt(i) == ']') {
                counter--;
            }
            if (counter == 0 && !isFirst) {
                end = i + 1;
                break;
            }
        }
        return input.substring(start, end);
    }

    public String getBlock() {
        return this.block;
    }

    public int getStartOfBlock() {
        return startOfBlock;
    }

    public int getEndOfBlock() {
        return endOfBlock;
    }

    //[ [ [2,1,3],[1,3,4] ],[ [1,3,4],[3,4,5] ],[ [1,3,4],[1,3,4] ] ]

}
