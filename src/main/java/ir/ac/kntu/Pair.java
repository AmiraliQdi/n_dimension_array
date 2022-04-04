package ir.ac.kntu;

import java.lang.reflect.Parameter;

public class Pair {

    private Array firstArray;

    private Array secondArray;

    private Array resultArray;

    private String result;

    private String input;

    private char operator;

    Pair(Array firstArray, Array secondArray, char operator,String resultArrayName) {
        this.firstArray = firstArray;
        this.secondArray = secondArray;
        this.operator = operator;
        this.resultArray = this.firstArray;
        process();
        this.resultArray.setArrayName(resultArrayName);
    }

    Pair(String input) {
        input = input.replaceAll(" ", "");
        this.input = input;
        findArrays();
        findOperator();
        this.resultArray = this.firstArray;
        process();
        findResultArrayName();
    }

    private void findArrays() {
        int equalAddress = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '=') {
                equalAddress = i;
            }
        }
        int betweenArrays = -1;
        for (int j = equalAddress; j < input.length(); j++) {
            char currChar = input.charAt(j);
            if (currChar == '+' || currChar == '-' || currChar == '*' || currChar == '/' || currChar == '#') {
                betweenArrays = j;
            }
        }
        String firstArrayName = input.substring(equalAddress + 1, betweenArrays);
        String secondArrayName = input.substring(betweenArrays+1, input.length());
        if (Main.storage.isFreeName(firstArrayName) || Main.storage.isFreeName(secondArrayName)) {
            System.out.println("Arrays name are not valid");
        } else {
            firstArray = Main.storage.getArrayFromName(firstArrayName);
            secondArray = Main.storage.getArrayFromName(secondArrayName);
        }
    }

    private void findOperator() {
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            if (currChar == '+') {
                operator = '+';
                break;
            } else if (currChar == '-') {
                operator = '-';
                break;
            } else if (currChar == '*') {
                operator = '*';
                break;
            } else if (currChar == '/') {
                operator = '/';
                break;
            } else if (currChar == '#') {
                operator = '#';
                break;
            }
        }
    }

    private void process(){
        String firstArrayMembers = clean(firstArray.getArrayInput());
        String secondArraysMembers = clean(secondArray.getArrayInput());
        String[] firstMembers = firstArrayMembers.split(",");
        String[] secondMembers = secondArraysMembers.split(",");
        String[] result = makeResultArray(firstMembers,secondMembers);
        String[] resultBlocks = addStringMode(result);
        fillTheResultArray(resultBlocks);
    }

    private void fillTheResultArray(String[] resultBlocks){
        for (int i = 0;i<resultBlocks.length;i++){
            int address = findAddress(i);
            replaceResultBlock(resultBlocks[i],address);
        }
    }

    private int findAddress(int memberIndex){
        String input = resultArray.getArrayInput();
        int start = 0;
        for (int j = 0;j<input.length();j++){
            if (input.charAt(j) != '['){
                start = j;
                break;
            }
        }
        if (memberIndex == 0){
            return start;
        }
        int counter  = 0;
        for (int i = start;i<input.length();i++){
            if (counter == memberIndex){
                return i;
            }
            if ((input.charAt(i) == ',' && input.charAt(i+1) != '[' ) ||
                    (input.charAt(i) == '[' && input.charAt(i+1) != '[')) {
                counter++;
            }
        }
        return -1;
    }

    private String[] addStringMode(String[] strings){
        if (!Character.isDigit(strings[0].charAt(0))) {
            for (int i = 0;i<strings.length;i++){
                String newString = "\"";
                newString += strings[i];
                newString += "\"";
                strings[i] = newString;
            }
        }
        return strings;
    }

    private String[] makeResultArray(String[] firstMembers,String[] secondMembers){
        String[] result = new String[firstMembers.length];
        for (int i = 0;i<firstMembers.length;i++){
            result[i] = operate(firstMembers[i],secondMembers[i]);
        }
        return result;
    }

    private String clean(String input){
        input = input.replaceAll("\\[","");
        input = input.replaceAll("]","");
        input = input.replaceAll("'","");
        input = input.replaceAll("‘","");
        input = input.replaceAll("’","");
        input = input.replaceAll("\"","");
        input = input.replaceAll("“","");
        input = input.replaceAll("”","");
        return input;
    }

    private String operate(String firstBlock,String secondBlock){
        switch (operator){
            case '+':
                return plusEl(firstBlock,secondBlock);
            case '-':
                return minesEl(firstBlock,secondBlock);
            case '*':
                return multiEl(firstBlock,secondBlock);
            case '/':
                return divideEl(firstBlock,secondBlock);
            case '#':
                return plusStringEl(firstBlock,secondBlock);
            default:
                return null;
        }
    }

    private boolean isFloat(String string){
        for (int i = 0;i<string.length();i++){
            if (string.charAt(i) == '.'){
                return true;
            }
        }
        return false;
    }

    private String plusEl(String firstBlock,String secondBlock){
        if (isFloat(firstBlock)){
            float firstNumber = Float.parseFloat(firstBlock);
            float secondNumber = Float.parseFloat(secondBlock);
            return Float.toString(firstNumber + secondNumber);
        } else {
            int firstNumber = Integer.parseInt(firstBlock);
            int secondNumber = Integer.parseInt(secondBlock);
            return Integer.toString(firstNumber+secondNumber);
        }
    }

    private String minesEl(String firstBlock,String secondBlock){
        if (isFloat(firstBlock)){
            float firstNumber = Float.parseFloat(firstBlock);
            float secondNumber = Float.parseFloat(secondBlock);
            return Float.toString(firstNumber - secondNumber);
        } else {
            int firstNumber = Integer.parseInt(firstBlock);
            int secondNumber = Integer.parseInt(secondBlock);
            return Integer.toString(firstNumber-secondNumber);
        }
    }

    private String multiEl(String firstBlock,String secondBlock){
        if (isFloat(firstBlock)){
            float firstNumber = Float.parseFloat(firstBlock);
            float secondNumber = Float.parseFloat(secondBlock);
            return Float.toString(firstNumber * secondNumber);
        } else {
            int firstNumber = Integer.parseInt(firstBlock);
            int secondNumber = Integer.parseInt(secondBlock);
            return Integer.toString(firstNumber*secondNumber);
        }
    }

    private String divideEl(String firstBlock,String secondBlock){
        if (isFloat(firstBlock)){
            float firstNumber = Float.parseFloat(firstBlock);
            float secondNumber = Float.parseFloat(secondBlock);
            return Float.toString(firstNumber / secondNumber);
        } else {
            int firstNumber = Integer.parseInt(firstBlock);
            int secondNumber = Integer.parseInt(secondBlock);
            return Integer.toString(firstNumber/secondNumber);
        }
    }

    private String plusStringEl(String firstBlock,String secondBlock){
        return firstBlock+secondBlock;
    }

    private void replaceResultBlock(String result,int startOfMember){
        String baseString = resultArray.getArrayInput().substring(0,startOfMember);
        baseString += result;
        int start = -1;
        for (int i = startOfMember;i<resultArray.getArrayInput().length();i++) {
            if (resultArray.getArrayInput().charAt(i) == ']' || resultArray.getArrayInput().charAt(i) == ','){
                start = i;
                break;
            }
        }
        baseString += resultArray.getArrayInput().substring(start);
        resultArray.setArrayInput(baseString);
    }

    private void findResultArrayName(){
        int end = -1;
        for (int i = 0;i<input.length();i++){
            if (input.charAt(i) == '='){
                end = i-1;
            }
        }
        resultArray.setArrayName(input.substring(0,end+1));
    }

    public Array getResultArray() {
        return resultArray;
    }
}
