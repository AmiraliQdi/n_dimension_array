package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Storage storage = new Storage();

    private static ChangeArray newChange;

    public static void main(String[] args) {

        start();

    }

    public static void start() {
        System.out.println("please do not use ( '[' , ']' , ',' '=') this characters in your arrays name");
        while (true) {

            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();

            process(input);
        }
    }

    public static void process(String input) {
        int commandType = checkCommand(input);
        input = input.replaceAll(" ", "");
        switch (commandType) {
            case 0:
                init(input);
                break;
            case 1:
                changeArray(input);
                break;
            case 2:
                pair(input);
            default:
                break;
        }
    }

    //Commands
    //----------------------------------------------------//

    public static void init(String input) {
        Array newArray = new Array(input);
        if (storage.isFreeName(newArray.getArrayName())) {
            storage.addNewArray(newArray);
            System.out.println("Array made : " + newArray.getArrayInput());
        } else {
            System.out.println("this name is not available");
        }
    }

    public static void changeArray(String input) {
        ChangeArray newChange = new ChangeArray(input);
        ArrayList<Integer> address = newChange.getAddress();
        Array changingArray = storage.getArrayFromName(newChange.getArrayName());
        Target changingTarget = new Target(address, changingArray);
        String newValue = newChange.getValue();
        String newArrayInput = makeNewArrayInput(changingArray,changingTarget,newValue);
        if (storage.isFreeName(newChange.getArrayName())) {
            Array newArray = new Array(newChange.getArrayName(),newArrayInput);
            //result
            System.out.println("Array changed : " + newArray.getArrayInput());
            storage.addNewArray(newArray);
        } else {
            changingArray.setArrayInput(newArrayInput);
        }
    }

    public static void pair(String input){
        Pair newPair = new Pair(input);
        if (storage.isFreeName(newPair.getResultArray().getArrayName())){
            System.out.println(newPair.getResultArray().getArrayName() + " array is not a valid name");
        } else {
            storage.addNewArray(newPair.getResultArray());
            System.out.println( " Result array : " + newPair.getResultArray().getArrayInput());
        }
    }

    //----------------------------------------------------//

    //Check Commands
    public static int checkCommand(String commandLine) {
        if (isInit(commandLine)) {
            return 0;
        } else if (isChangeArray(commandLine)) {
            return 1;
        } else if (isPair(commandLine)){
            return 2;
        } else {
            return -1;
        }
    }

    public static boolean isInit(String commandLine) {
        int equalPlace = findEqual(commandLine);
        for (int i = equalPlace; i < commandLine.length(); i++) {
            if (commandLine.charAt(i) == ']' || commandLine.charAt(i) == '[') {
                return true;
            }
        }
        return false;
    }

    public static boolean isChangeArray(String commandLine) {
        int equalPlace = findEqual(commandLine);
        for (int i = 0; i < equalPlace; i++) {
            if (commandLine.charAt(i) == ']' || commandLine.charAt(i) == '[') {
                return true;
            }
        }
        return false;
    }

    public static boolean isPair(String commandLine) {
        if (noCroshe(commandLine)) {
            for (int i = 0; i < commandLine.length(); i++) {
                char temp = commandLine.charAt(i);
                if (temp == '+' || temp == '-' || temp == '/' || temp == '*' || temp == '#') {
                    return true;
                }
            }
        }
        return false;
    }

    //End Check Commands

    public static int findEqual(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '=') {
                return i;
            }
        }
        return -1;
    }

    public static boolean noCroshe(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '[' || input.charAt(i) == '[') {
                return false;
            }
        }
        return true;
    }

    public static String makeNewArrayInput(Array changingArray,Target changingTarget,String newValue){
        String newArrayInput = changingArray.getArrayInput().substring(0, changingTarget.getStartOfBlock());
        newArrayInput += newValue;
        newArrayInput += changingArray.getArrayInput().substring(changingTarget.getEndOfBlock() + 1, changingArray.getInputLength());
        return newArrayInput;
    }

}