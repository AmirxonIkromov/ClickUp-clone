package com.amirscode.clickup.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDTO {

    @NotNull
    private String name;

    private String accessType;

    private String color;

    private boolean archived;

    @NotNull
    private Long projectId;
}
