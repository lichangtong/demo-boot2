package com.example.demo.demoboot2;

import com.google.inject.internal.util.Lists;

import java.util.List;

/**
 * @Auther: lichangtong
 * @Date: 2019-02-27 11:35
 * @Description:
 */
public class Test {
    private int id;
    private List<Test> items = Lists.newArrayList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Test> getItems() {
        return items;
    }

    public void setItems(List<Test> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }
}
