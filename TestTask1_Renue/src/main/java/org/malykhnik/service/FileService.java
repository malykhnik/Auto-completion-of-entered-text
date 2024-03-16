package org.malykhnik.service;

import org.malykhnik.entity.JsonAnswer;

import java.util.List;

public interface FileService {
    List<String> readQueries(String filename);
    void writeInfo(String filename, JsonAnswer jsonAnswer);
}
