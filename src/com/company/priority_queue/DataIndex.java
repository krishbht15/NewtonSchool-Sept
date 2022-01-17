package com.company.priority_queue;

public class DataIndex implements Comparable<DataIndex> {
    int idx;
    int lidx;
    int val;

    public DataIndex(int idx, int lidx, int val) {
        this.idx = idx;
        this.lidx = lidx;
        this.val = val;
    }

    @Override
    public int compareTo(DataIndex dataIndex) {
        return this.val - dataIndex.val;
    }
}
