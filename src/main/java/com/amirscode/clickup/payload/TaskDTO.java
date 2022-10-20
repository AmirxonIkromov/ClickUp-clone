package com.amirscode.clickup.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class TaskDTO {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Long statusId;

    @NotNull
    private Long categoryId;

    private Long priorityId;

    private Long parentTaskId;

    private Date staredDate;

    private Date dueDate;

    private Long estimateTime;

    private Date activatedDate;
}
