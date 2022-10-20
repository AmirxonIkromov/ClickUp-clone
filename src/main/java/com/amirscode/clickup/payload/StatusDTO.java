package com.amirscode.clickup.payload;

import com.amirscode.clickup.enums.StatusType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatusDTO {

    @NotNull
    private String name;

    private String color;

    private StatusType statusType;

    private Long spaceId;

    private Long projectId;

    private Long categoryId;
}
