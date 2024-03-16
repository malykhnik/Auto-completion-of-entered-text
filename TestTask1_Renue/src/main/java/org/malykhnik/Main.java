package org.malykhnik;

import org.malykhnik.entity.BinarySearchTree;
import org.malykhnik.dto.ResultDto;
import org.malykhnik.entity.JsonAnswer;
import org.malykhnik.service.BinaryTreeService;
import org.malykhnik.service.FileService;
import org.malykhnik.service.impl.BinaryTreeServiceImpl;
import org.malykhnik.service.impl.FileServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        String fileData = "home/test/airports.csv";
//        String inputFile = "temp/input1.txt";
//        String outputFile = "temp/result1.json";
//        int columnId = 2;

        String fileData = "";
        String inputFile = "";
        String outputFile = "";
        int columnId = 0;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--data") && i + 1 < args.length) {
                fileData = args[i + 1];
            } else if (args[i].equals("--input-file") && i + 1 < args.length) {
                inputFile = args[i + 1];
            } else if (args[i].equals("--output-file") && i + 1 < args.length) {
                outputFile = args[i + 1];
            } else if (args[i].equals("--indexed-column-id") && i + 1 < args.length) {
                columnId = Integer.parseInt(args[i + 1]);
            }
        }

        if (fileData.isEmpty() || inputFile.isEmpty() || outputFile.isEmpty()) {
            throw new IllegalArgumentException("Incorrect parameters provided!");
        }

        fileData = fileData.substring(1);
        inputFile = inputFile.substring(1);
        outputFile = outputFile.substring(1);

        JsonAnswer jsonAnswer = new JsonAnswer();
        long startTime = System.nanoTime();

        FileService fileService = new FileServiceImpl();
        List<String> queries = fileService.readQueries(inputFile);

        long endTime = System.nanoTime();

        jsonAnswer.setInitTime((endTime - startTime));

        BinaryTreeService btsService = new BinaryTreeServiceImpl();
        BinarySearchTree bst = btsService.create(fileData, columnId);

        for (String query : queries) {
            ResultDto resultDto = new ResultDto();
            int numRow;

            Set<Integer> visitedRows = new HashSet<>();

            startTime = System.nanoTime();
            while ((numRow = bst.findRowNum(query, visitedRows)) != -1) {
                resultDto.getResultIds().add(numRow + 1);
            }
            endTime = System.nanoTime();

            resultDto.setTime(endTime - startTime);
            resultDto.setSearch(query);

            jsonAnswer.getResult().add(resultDto);
        }

        fileService.writeInfo(outputFile, jsonAnswer);
    }
}
