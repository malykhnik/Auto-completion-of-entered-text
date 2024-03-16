package org.malykhnik.entity;

import java.util.Set;

public class BinarySearchTree {
    Node root;

    public void insert(String value, int rowNum) {
        root = insertRec(root, value, rowNum);
    }

    private Node insertRec(Node root, String value, int rowNum) {
        if (root == null) {
            root = new Node(value, rowNum);
            return root;
        }
        if (value.compareTo(root.value) < 0)
            root.left = insertRec(root.left, value, rowNum);
        else if (value.compareTo(root.value) > 0)
            root.right = insertRec(root.right, value, rowNum);
        return root;
    }

    public int findRowNum(String value, Set<Integer> visited) {
        return findRowNumRec(root, value, visited);
    }

    private int findRowNumRec(Node root, String value, Set<Integer> visited) {
        if (root == null || (root.value.startsWith(value) && !visited.contains(root.rowNum)))
            if (root != null) {
                visited.add(root.rowNum);
                return root.rowNum;
            }
            else return  -1;
        if (value.compareTo(root.value) < 0)
            return findRowNumRec(root.left, value, visited);
        return findRowNumRec(root.right, value, visited);
    }
}
