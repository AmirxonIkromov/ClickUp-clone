package com.amirscode.clickup.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class WorkspaceDTO {
    @NotNull
    private String name;

    @NotNull
    private String color;

    private Long workspaceId;

    private UUID avatarId;

    private String initial;

}
