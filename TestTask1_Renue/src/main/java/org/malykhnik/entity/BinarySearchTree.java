package org.malykhnik.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BinarySearchTree {
    private Node root;

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

    public List<Integer> findRowNum(String value) {
        List<Integer> rows = new ArrayList<>();
        findRowNumRec(root, value, rows);
        return rows;
    }

    private void findRowNumRec(Node root, String value, List<Integer> rows) {
        if (root == null) return;
        if (root.value.startsWith(value)) rows.add(root.rowNum);
        if (value.compareTo(root.value) < 0) findRowNumRec(root.left, value, rows);
        findRowNumRec(root.right, value, rows);
    }
}
