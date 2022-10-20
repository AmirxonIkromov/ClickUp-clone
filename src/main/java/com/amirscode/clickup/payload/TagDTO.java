package com.amirscode.clickup.payload;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class TagDTO {

    @NotNull
    private String name;

    private String color;

    @NotNull
    private Long workspaceId;
}
