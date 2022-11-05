package org.example.dto;

import java.util.List;

public class Questions {
   private List<Word> list;

    public List<Word> getList() {
        return list;
    }

    public void setList(List<Word> list) {
        this.list = list;
    }

    @Override
    public String
    toString() {
        return "Questions{" +
                "list=" + list +
                '}';
    }
}