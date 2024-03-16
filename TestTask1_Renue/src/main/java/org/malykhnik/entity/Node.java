package org.malykhnik.entity;

public class Node {
    String value;
    int rowNum;
    Node left, right;

    public Node(String value, int rowNum) {
        this.value = value;
        this.rowNum = rowNum;
        left = right = null;
    }
}
