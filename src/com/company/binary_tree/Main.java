package com.company.binary_tree;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60};
        Node root = constructBT(arr, 0);
//        display(root);
//        displayBfs(root);
//        Node n = constructBT(arr, 0);
//        System.out.println(n);
//        Arrays.toString(arr);
//        Arrays.toString();
//        display(n);
//        ArrayList<String> paths = new ArrayList<>();
//        getAllPaths(root, "", paths);
//        System.out.println(paths);
        ArrayList<Integer> res = new ArrayList<>();
//        allNodesKAway(root, 2, 50, res);
//        System.out.println(res);
    }

    public static Node constructBT(int[] arr, int i) {
        Node n = new Node(arr[i]);
        int li = 2 * i + 1;
        if (li < arr.length && arr[li] != -1) {
            n.left = constructBT(arr, li);
        }
        int ri = 2 * i + 2;
        if (ri < arr.length && arr[ri] != -1) {
            n.right = constructBT(arr, ri);
        }
        return n;
    }

    public static void display(Node root) {
        if (root == null) return;
        System.out.print(root.left);
        System.out.print(" <- " + root + " -> ");
        System.out.print(root.right);
        System.out.println();
        display(root.left);
        display(root.right);
    }

    public static void displayBfs(Node root) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int s = q.size();
            while (s > 0) {
                Node curr = q.remove();
                System.out.println(curr);
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
                s--;
            }
        }
    }

    public static void getAllPaths(Node n, String path, ArrayList<String> paths) {
        if (n.left == null && n.right == null) {
            paths.add(path + n);
            return;
        }
        if (n.left != null)
            getAllPaths(n.left, path + n + "-", paths);
        if (n.right != null)
            getAllPaths(n.right, path + n + "-", paths);
    }

    public static List<Integer> distanceK(Node root, Node target, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<Node> path = nodeToRootPath(root, target);
        Node block = null;
        for (int i = 0; i < path.size(); i++) {
            if (k - i < 0) break;
            nodesDistanceK(path.get(i), block, k - i, res);
            block = path.get(i);
        }
        return res;
    }

    public static void nodesDistanceK(Node curr, Node block, int k, ArrayList<Integer> res) {
        if (curr == block) return;
        if (k == 0) {
            res.add(curr.data);
            return;
        }
        if (curr.left != null) nodesDistanceK(curr.left, block, k - 1, res);
        if (curr.right != null) nodesDistanceK(curr.right, block, k - 1, res);
    }

    public static ArrayList<Node> nodeToRootPath(Node root, Node tar) {
        if (root == tar) {
            return new ArrayList<Node>(Collections.singletonList(root));
        }
        ArrayList<Node> left = root.left == null ? null : nodeToRootPath(root.left, tar);
        if (left != null) {
            left.add(root);
            return left;
        }
        ArrayList<Node> right = root.right == null ? null : nodeToRootPath(root.right, tar);
        if (right != null) right.add(root);
        return right;
    }

    public static int minCameras = 0;

    public static int minCameraCover(Node root) {
        minCameras = 0;
        int camStatus = minCamStatus(root);
        if (camStatus == -1) minCameras++;
        return minCameras;
    }

    public static int minCamStatus(Node root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return -1;
        int l = minCamStatus(root.left);
        int r = minCamStatus(root.right);
        if (l == -1 || r == -1) {
            minCameras++;
            return 1;
        }
        if (l == 1 || r == 1) return 0;
        return -1;
    }

    public static boolean isSubtree(Node root, Node sub) {
        if (root == null) return false;
        if (root == sub) return check(root, sub);
        boolean left = isSubtree(root.left, sub);
        boolean right = isSubtree(root.right, sub);
        return (left || right);
    }

    private static boolean check(Node root, Node sub) {
        if (root != sub) return false;
        boolean left = check(root.left, sub.left);
        if (!left) return false;
        return check(root.right, sub.right);
    }

    public static DiameterPair diameterPair(Node node) {
        if (node == null) return new DiameterPair(-1, 0);
        DiameterPair l = diameterPair(node.left);
        DiameterPair r = diameterPair(node.right);
        int currDia = l.h + r.h + 2;
        int h = Math.max(l.h, r.h) + 1;
        return new DiameterPair(h, Math.max(currDia, Math.max(l.d, r.d)));
    }

    static class DiameterPair {
        int h;
        int d;

        public DiameterPair(int h, int d) {
            this.h = h;
            this.d = d;
        }
    }

    public static RobberyPair maxRobbery(Node root) {
        if (root == null) return new RobberyPair(0, 0);
        RobberyPair l = maxRobbery(root.left);
        RobberyPair r = maxRobbery(root.right);
        int currRob = root.data + l.noRob + r.noRob;
        int currNoRob = Math.max(l.noRob, l.rob) + Math.max(r.rob, r.noRob);
        return new RobberyPair(currRob, currNoRob);
    }

    static class RobberyPair {
        int rob;
        int noRob;

        public RobberyPair(int rob, int noRob) {
            this.rob = rob;
            this.noRob = noRob;
        }
    }

    public static List<List<Integer>> verticalTraversal(Node root) {

        PriorityQueue<VerticalTravPair> primary = new PriorityQueue<>();
        PriorityQueue<VerticalTravPair> secondary = new PriorityQueue<>();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int start = 0;
        primary.add(new VerticalTravPair(0, root));
        while (!primary.isEmpty()) {
            int size = primary.size();
            while (size > 0) {
                VerticalTravPair pair = primary.remove();
                if (map.containsKey(pair.level)) {
                    map.get(pair.level).add(pair.node.data);
                } else {
                    map.put(pair.level,
                            new ArrayList<>(Collections.singletonList(pair.node.data)));
                }
                if (pair.level < start) start = pair.level;
                if (pair.node.left != null) secondary.add(new VerticalTravPair(pair.level - 1, pair.node.left));
                if (pair.node.right != null) secondary.add(new VerticalTravPair(pair.level + 1, pair.node.right));
                size--;
            }
            PriorityQueue<VerticalTravPair> temp = secondary;
            secondary = primary;
            primary = temp;
        }
        List<List<Integer>> ans = new ArrayList<>();
        while (map.containsKey(start)) {
            ans.add(new ArrayList<>(map.get(start)));
            start++;
        }
        return ans;
    }

    static class VerticalTravPair implements Comparable<VerticalTravPair> {
        int level;
        Node node;

        public VerticalTravPair(int level, Node node) {
            this.level = level;
            this.node = node;
        }

        @Override
        public int compareTo(VerticalTravPair o) {
            return this.node.data - o.node.data;
        }
    }
}
