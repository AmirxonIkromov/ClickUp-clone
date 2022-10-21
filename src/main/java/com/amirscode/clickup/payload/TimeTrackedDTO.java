package com.amirscode.clickup.payload;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
public class TimeTrackedDTO {

    @NotNull
    private UUID taskId;

    private Timestamp startedAt;

    private Timestamp stoppedAt;
}
