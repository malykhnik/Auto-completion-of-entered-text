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
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

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

        FileService fileService = new FileServiceImpl();
        List<String> queries = fileService.readQueries(inputFile);

        BinaryTreeService btsService = new BinaryTreeServiceImpl();
        BinarySearchTree bst = btsService.create(fileData, columnId);

        long endTime = System.currentTimeMillis();
        long finalTime = endTime - startTime;
        jsonAnswer.setInitTime(finalTime);
        for (String query : queries) {
            ResultDto resultDto = new ResultDto();

            startTime = System.nanoTime();
            List<Integer> rows = bst.findRowNum(query);
            resultDto.setResultIds(rows);
            endTime = System.nanoTime();

            finalTime = endTime - startTime;
            resultDto.setTime(Math.round((float) finalTime /1_000_000));
            resultDto.setSearch(query);

            jsonAnswer.getResult().add(resultDto);
        }

        fileService.writeInfo(outputFile, jsonAnswer);
    }
}
