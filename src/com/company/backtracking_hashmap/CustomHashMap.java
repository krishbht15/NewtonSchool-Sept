package com.company.backtracking_hashmap;

import java.util.LinkedList;

public class CustomHashMap<K, V> {
    LinkedList<Node<K, V>>[] buckets;
    int size;

    public CustomHashMap() {
        this.size = 0;
        initialiseBucket(4);
    }

    public void put(K key, V value) {
        int bi = findBucketIndex(key);
        int llIndex = findLLIndex(bi, key);
        if (llIndex == -1) {
            Node<K, V> node = new Node<>(key, value);
            buckets[bi].addLast(node);
            this.size++;
        } else {
            Node<K, V> node = buckets[bi].get(llIndex);
            node.value = value;
        }
        double lamda = this.size * (1.0 / buckets.length);
        if (lamda > 2.0) {
            rehashing();
        }
    }

    public V get(K key) {
        LinkedList<Node<K, V>> ll = buckets[findBucketIndex(key)];
        for (int i = 0; i < ll.size(); i++) {
            if (ll.get(i).key.equals(key)) return ll.get(i).value;
        }
        return null;
    }

    public void remove(K key) {
        LinkedList<Node<K, V>> ll = buckets[findBucketIndex(key)];
        for (int i = 0; i < ll.size(); i++) {
            if (ll.get(i).key.equals(key)) {
                ll.remove(i);
            }
        }
    }

    private void rehashing() {
        LinkedList<Node<K, V>>[] oldBucket = this.buckets;
        initialiseBucket(2 * this.buckets.length);
        for (int i = 0; i < oldBucket.length; i++) {
            for (int j = 0; j < oldBucket[i].size(); j++) {
                Node<K, V> curr = oldBucket[i].get(j);
                int bi = findBucketIndex(curr.key);
                buckets[bi].addLast(curr);
            }
        }
    }


    private int findLLIndex(int bucketIndex, K key) {
        LinkedList<Node<K, V>> ll = buckets[bucketIndex];
        for (int i = 0; i < ll.size(); i++) {
            if (ll.get(i).key.equals(key)) return i;
        }
        return -1;
    }

    private int findBucketIndex(K key) {
        int hashcode = key.hashCode();
        return Math.abs(hashcode) % buckets.length;
    }

    private void initialiseBucket(int size) {
        this.buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            this.buckets[i] = new LinkedList<>();
        }
    }
}
