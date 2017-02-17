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
        for (int i = 0; i < this.counter; i++) {
//            result = "" + keys[counter] + values[counter];
            result = result + "\"" + this.keys[i] + "\": \"" + this.values[i] + "\",";
        }
        return result;
    }

    public void setParams(String key, Object value) {
        this.keys[this.counter] = key;
        this.values[this.counter] = value;
        this.counter++;
    }
}
