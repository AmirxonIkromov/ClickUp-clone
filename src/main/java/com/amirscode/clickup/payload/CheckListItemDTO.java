package com.amirscode.clickup.payload;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class CheckListItemDTO {

    @NotNull
    private String name;

    private boolean completed;

    private Long checkListId;

    private UUID assignedUserId;
}
