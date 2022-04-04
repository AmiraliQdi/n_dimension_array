package ir.ac.kntu;

import java.util.ArrayList;

public class Storage {

    private ArrayList<Array> arraysList;

    Storage() {
        arraysList = new ArrayList<Array>();
    }

    public boolean isFreeName(String name) {
        for (int i = 0; i < arraysList.size(); i++) {
            if (name.equals(arraysList.get(i).getArrayName())) {
                return false;
            }
        }
        return true;
    }

    public void addNewArray(Array newArray) {
        arraysList.add(newArray);
    }

    public Array getArrayFromIndex(int index) {
        return arraysList.get(index);
    }

    public Array getArrayFromName(String name) {
        for (int i = 0; i < arraysList.size(); i++) {
            if (arraysList.get(i).getArrayName().equals(name)) {
                return arraysList.get(i);
            }
        }
        System.out.println("there is no array with this name");
        return null;
    }

}
