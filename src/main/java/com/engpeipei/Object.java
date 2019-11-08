package com.engpeipei;

import java.util.ArrayList;
import java.util.List;

public class Object {
    private List <String>arrayURL = new ArrayList<String>();

    public void setArrayURL (String url) {
        arrayURL.add(url);
    }

    public List<String> getArrayURL() {
        return arrayURL;
    }


}
