package com.amirscode.clickup.payload;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class CommentDTO {

    @NotNull
    private String text;

    @NotNull
    private UUID taskId;
}
