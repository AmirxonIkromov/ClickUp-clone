package com.amirscode.clickup.payload;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class TaskDependencyDTO {

    @NotNull
    private UUID dependencyTaskId;

    @NotNull
    private String dependencyType;

    @NotNull
    private UUID taskId;
}
