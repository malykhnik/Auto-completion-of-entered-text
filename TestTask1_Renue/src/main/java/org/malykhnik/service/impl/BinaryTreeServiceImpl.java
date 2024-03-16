package org.malykhnik.service.impl;

import org.malykhnik.entity.BinarySearchTree;
import org.malykhnik.service.BinaryTreeService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class BinaryTreeServiceImpl implements BinaryTreeService {
    @Override
    public BinarySearchTree create(String filename, int columnId) {
        BinarySearchTree bst = new BinarySearchTree();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            String line;
            int rowNum = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\,");
                if (parts.length <= columnId || columnId < 0) {
                    throw new IllegalArgumentException("Incorrect columnId!!!");
                }
                String correctStr = parts[columnId-1].trim();
                correctStr = correctStr.substring(1, correctStr.length() - 1);
                bst.insert(correctStr, rowNum);
                rowNum += 1;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not open airports.csv");
        }
        return bst;
    }
}
