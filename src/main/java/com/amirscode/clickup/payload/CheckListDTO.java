package com.amirscode.clickup.payload;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class CheckListDTO{

    @NotNull
    private String name;

    @NotNull
    private UUID taskId;
}
