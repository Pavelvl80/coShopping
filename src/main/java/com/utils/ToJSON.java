package com.utils;

/**
 * Created by Edvard Piri on 09.02.2017.
 */
public class ToJSON {
    int counter = 0;
    private String[] keys = new String[counter+1];
    private Object[] values = new Object[counter+1];

    public String ToJSON() {
        String result = "";
        for (int i = 0; i < counter; i++) {
//            result = "" + keys[counter] + values[counter];
            result = result + "\"" + keys[i] + "\": \"" + values[i] + "\",";
        }
        return result;
    }

    public void setParams(String key, Object value) {
        keys[counter] = key;
        values[counter] = value;
        counter++;
    }
}
