package ir.ac.kntu;

import java.util.ArrayList;

public class Array {

    private String arrayName;

    private String arrayInput;

    private char arrayType;

    private int dimension;

    private int inputLength;

    private ArrayList<Integer> dimensions;

    private int membersCount;

    public Array(String input) {
        dimensions = new ArrayList<>();
        makeArray(input);
        this.inputLength = findInputLength();
        this.arrayType = findArrayType();
        this.dimension = countArrayDimension();
        findDimensions();
        membersCount = countCamma() + 1;
    }

    public Array(String arrayName,String arrayInput){
        dimensions = new ArrayList<>();
        this.arrayName = arrayName;
        this.arrayInput = arrayInput;
        this.inputLength = findInputLength();
        this.arrayType = findArrayType();
        this.dimension = countArrayDimension();
        findDimensions();
        membersCount = countCamma() + 1;
    }

    private void makeArray(String array) {
        array = removeSpaces(array);
        String name = returnName(array);
        array = finalArray(array);
        this.arrayInput = array;
        this.arrayName = name;
    }

    private String returnName(String input) {
        int end = 0;
        if ((int) input.charAt(0) >= 48 && (int) input.charAt(0) <= 57) {
            System.out.println("invalid name");
            System.exit(0);
        }
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '=') {
                end = i;
                break;
            }
        }
        return input.substring(0, end);
    }

    private String finalArray(String array) {
        int start = 0;
        for (int i = 0; i < array.length(); i++) {
            if (array.charAt(i) == '=') {
                start = i + 1;
                break;
            }
        }
        return array.substring(start, array.length());
    }

    public int getInputLength() {
        return inputLength;
    }

    public ArrayList<Integer> getDimensions() {
        return dimensions;
    }

    private void findDimensions() {

    }

    private int findInputLength() {
        return arrayInput.length();
    }

    public void setArrayName(String arrayName) {
        this.arrayName = arrayName;
    }

    public void setArrayInput(String arrayInput) {
        this.arrayInput = arrayInput;
    }

    public String getArrayName() {
        return arrayName;
    }

    public String getArrayInput() {
        return arrayInput;
    }

    public char getArrayType() {
        return arrayType;
    }

    public int getDimension() {
        return dimension;
    }

    private String removeSpaces(String arrayInput) {
        arrayInput = arrayInput.replaceAll(" ", "");
        return arrayInput;
    }

    private char findArrayType() {
        return 'e';
    }

    private boolean isString() {
        for (int i = 0; i < arrayInput.length(); i++) {
            if (arrayInput.charAt(i) == '\"') {
                return true;
            }
        }
        return false;
    }

    private boolean isChar() {
        for (int i = 0; i < arrayInput.length(); i++) {
            if (arrayInput.charAt(i) == '\'') {
                return true;
            }
        }
        return false;
    }

    private boolean isFloat() {
        for (int i = 0; i < arrayInput.length(); i++) {
            if (arrayInput.charAt(i) == '.') {
                return true;
            }
        }
        return false;
    }

    private int countArrayDimension() {
        int counter = 0;
        for (int i = 0; i < inputLength; i++) {
            if (arrayInput.charAt(i) == '[') {
                counter++;
            }
            if (arrayInput.charAt(i) == ']') {
                break;
            }
        }
        return counter;
    }

    private int countCamma(){
        int counter = 0;
        for (int i = 0;i<inputLength;i++){
            if (arrayInput.charAt(i)== ','){
                counter++;
            }
        }
        return counter;
    }

    public int getMembersCount(){
        return membersCount;
    }
}
