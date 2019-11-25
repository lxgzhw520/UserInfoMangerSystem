package com.lxgzhw.domain;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    private int totalCount; // 总记录数
    private int totalPage; // 总页码
    private List<T> list; // 每页的数据
    private int currentPage; //当前页码
    private int rows;//每页显示的记录数
    private ArrayList<Integer> pageRange;//页码列表

    public Page() {
    }

    public Page(int totalCount, int totalPage, List<T> list, int currentPage, int rows, ArrayList<Integer> pageRange) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
        this.currentPage = currentPage;
        this.rows = rows;
        this.pageRange = pageRange;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public ArrayList<Integer> getPageRange() {
        /**
         * 页码生成方案:
         * 如果总页数<=7 则生成1到总页数
         * 如果总页数>7
         *     如果当前页小于3: 则生成1~7
         *     如果当前页大于等于3,且小于总页数-3:  则生成 当前页-3  当前页+3
         *     如果当前页大于总页数-3:  则生成 总页数-7 总页数
         */
        this.pageRange = new ArrayList<Integer>();
        if (this.totalPage < 8) {
            //1:  1
            //2:  1 2
            //5:  1 2 3 4 5
            for (int i = 0; i < this.totalPage; i++) {
                this.pageRange.add(i + 1);
            }
        } else {
            //判断当前页
            if (this.currentPage < 4) {
                for (int i = 0; i < 7; i++) {
                    this.pageRange.add(i + 1);
                }
            } else if (this.currentPage < this.totalPage - 3) {
                for (int i = this.currentPage - 3; i < this.currentPage + 4; i++) {
                    this.pageRange.add(i);
                }
            } else {
                for (int i = this.currentPage - 7; i <= this.totalPage; i++) {
                    this.pageRange.add(i);
                }
            }
        }
        return pageRange;
    }

    public void setPageRange(ArrayList<Integer> pageRange) {
        this.pageRange = pageRange;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                ", pageRange=" + pageRange +
                '}';
    }
}
