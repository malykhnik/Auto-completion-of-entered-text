package org.malykhnik.entity;

import lombok.Data;
import org.malykhnik.dto.ResultDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class JsonAnswer {
    private long initTime;
    List<ResultDto> result = new ArrayList<>();
}
