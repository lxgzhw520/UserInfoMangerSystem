package com.lxgzhw.test;

import org.junit.Test;

import java.util.ArrayList;

public class PageTest {
    int currentPage = 5;
    ArrayList<Integer> pageRange =
            new ArrayList<>();

    @Test
    public void demo01() {

        for (int i = this.currentPage - 3; i < this.currentPage + 4; i++) {
            this.pageRange.add(i);
        }
        System.out.println(this.pageRange);
    }
}
