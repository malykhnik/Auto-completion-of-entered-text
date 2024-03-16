package org.malykhnik.service;

import org.malykhnik.entity.BinarySearchTree;

public interface BinaryTreeService {
    BinarySearchTree create(String filename, int columnId);
}
