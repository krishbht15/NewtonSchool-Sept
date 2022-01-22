package com.company.priority_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueue  {

    ArrayList<Integer> data;

    public PriorityQueue() {
        this.data = new ArrayList<>();
    }

    public void add(int d) {
        this.data.add(d);
        upHeapify(this.data.size() - 1);
    }

    private void upHeapify(int i) {
        int pi = (i - 1) / 2;
        if (pi >= 0 && this.data.get(i) < this.data.get(pi)) {
            swap(i, pi);
            if (pi > 0) upHeapify(pi);
        }
    }

    private void swap(int i, int pi) {
        int iData = this.data.get(i);
        int piData = this.data.get(pi);
        this.data.set(pi, iData);
        this.data.set(i, piData);
    }

    public int remove() {
        if(this.size()==0) return -1;
        int res = this.data.get(0);
        swap(0, this.data.size() - 1);
        this.data.remove(this.data.size()-1);
        downHeapify(0);
         int[][] a={{1,2},{3,1}};
        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int v1=o1[0]*o1[1]*o1[2];
                int v2=o2[0]*o2[1]*o2[2];
                return v1-v2;
            }
        });
         return res;
    }

    private void downHeapify(int i) {
        int res = i;
        int li = 2 * i + 1;
        if (li < data.size() && this.data.get(res) > this.data.get(li)) res = li;
        int ri = 2 * i + 2;
        if (ri < data.size() && this.data.get(res) > this.data.get(ri)) res = ri;
        if (res == i) return;
        swap(i, res);
        downHeapify(res);
    }

    public int peek() {
        if(this.size()==0) return -1;
        return this.data.get(0);
    }

    public int size() {
        if(this.data.size()==0) return -1;
        return this.data.size();
    }
}
