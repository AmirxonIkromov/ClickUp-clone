package com.amirscode.clickup.payload;

import com.amirscode.clickup.enums.TaskPermissionName;
import lombok.Data;

import java.util.UUID;

@Data
public class CategoryUserDTO {

    private Long categoryId;

    private UUID userId;

    private TaskPermissionName permissionName;
}
