package ir.ac.kntu;

import java.util.ArrayList;

public class ChangeArray {

    private String input;

    private String arrayName;

    private String nonUsableAddress;

    private String value;

    private ArrayList<Integer> address;

    ChangeArray(String input) {
        input = input.replaceAll(" ", "");
        this.input = input;
        this.arrayName = findName(input);
        this.nonUsableAddress = findNonUsableAddress(input);
        this.value = findValue(input);
        this.address = findAddress(nonUsableAddress);
    }

    private String findName(String input) {
        int end = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '[') {
                end = i - 1;
                break;
            }
        }
        return input.substring(0, end + 1);
    }

    private String findNonUsableAddress(String input) {
        int start = -1, end = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '[') {
                start = i;
                break;
            }
        }
        for (int j = start; j < input.length(); j++) {
            if (input.charAt(j) == '=') {
                end = j - 1;
                break;
            }
        }
        if (start == -1 || end == -1) {
            System.exit(0);
        }
        return input.substring(start, end + 1);
    }

    private ArrayList<Integer> findAddress(String input) {
        ArrayList<Integer> address = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '[') {
                int j = i + 1;
                StringBuilder tempAddress = new StringBuilder();
                while (input.charAt(j) != ']') {
                    tempAddress.append(input.charAt(j));
                    j++;
                }
                address.add(Integer.parseInt(tempAddress.toString()));
                i = j;
            }
        }
        return address;
    }

    private String findValue(String input) {
        int start = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '=') {
                start = i + 1;
            }
        }
        return input.substring(start, input.length() );
    }

    public ArrayList<Integer> getAddress() {
        return address;
    }

    public String getArrayName() {
        return arrayName;
    }

    public String getNonUsableAddress() {
        return nonUsableAddress;
    }

    public String getValue() {
        return value;
    }
}
