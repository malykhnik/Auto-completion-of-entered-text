package org.malykhnik.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResultDto {
    private String search;
    private List<Integer> resultIds = new ArrayList<>();
    private long time;
}
