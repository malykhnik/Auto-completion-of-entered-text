package org.malykhnik.service.impl;

import com.google.gson.*;
import org.malykhnik.dto.ResultDto;
import org.malykhnik.entity.JsonAnswer;
import org.malykhnik.service.FileService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {
    @Override
    public List<String> readQueries(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not open input1.txt");
        }

        if (lines.isEmpty()) {
            throw new RuntimeException("input1.txt is empty!!");
        }
        return lines;
    }

    @Override
    public void writeInfo(String filename, JsonAnswer jsonAnswer) {
        try {
            Path filePath = Path.of(filename);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);

            Gson gson = new Gson();
            String json = gson.toJson(jsonAnswer);

            writer.write(json);
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can not open airports.csv");
        }
    }
}
