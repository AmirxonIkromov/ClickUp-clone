package com.amirscode.clickup.payload;

import com.amirscode.clickup.enums.TaskPermissionName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ProjectUserDTO {

    @NotNull
    private Long project;

    @NotNull
    private UUID user;

    private TaskPermissionName permissionName;

}
