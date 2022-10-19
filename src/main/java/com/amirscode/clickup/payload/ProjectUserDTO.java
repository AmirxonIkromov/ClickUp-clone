package com.amirscode.clickup.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ProjectUserDTO {

    @NotNull
    private Long project;

    @NotNull
    private UUID user;

}
