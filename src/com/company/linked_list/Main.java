package com.company.linked_list;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

    }

    public static Node deepCopyWithMap(Node head) {
        Node res = new Node(-1);
        Node n = res;
        Node i = head;
        HashMap<Node, Node> map = new HashMap<>();
        while (i != null) {
            n.next = new Node(i.val);
            map.put(i, n.next);
            n = n.next;
            i = i.next;
        }
        i = head;
        while (i != null) {
            if (map.get(i) != null)
                map.get(i).random = map.get(i.random);
            i = i.next;
        }
        return res.next;
    }


    public static Node deepCopyWithoutSpace(Node head) {
        Node n = head;
        while (n != null) {
            Node next = n.next;
            Node newNode = new Node(n.val);
            n.next = newNode;
            newNode.next = next;
            n = next;
        }
        n = head;
        while (n != null) {
            if (n.random != null) n.next.random = n.random.next;
            n = n.next.next;
        }
        n = head;
        Node res = n.next;
        while (n != null) {
            Node next = n.next;
            n.next = n.next.next;
            next.next = n.next != null ? n.next.next : null;
            n = n.next;
        }
        return res;
    }
}
