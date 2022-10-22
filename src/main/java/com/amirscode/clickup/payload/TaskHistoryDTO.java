package com.amirscode.clickup.payload;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class TaskHistoryDTO {

    private String field;

    private String before;

    private String after;

    private Date date;

    private String data;

    private UUID taskId;
}
