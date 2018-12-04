package com.j.dbl.pojo;

import java.io.Serializable;
import java.util.List;

public class EchartsData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String[] categories;
    private Object[] data;

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }
}
