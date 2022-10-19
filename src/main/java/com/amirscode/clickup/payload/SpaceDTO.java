package com.amirscode.clickup.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class SpaceDTO {

    @NotNull
    private String name;

    private String  color;

    private String accessType;

    private Long iconId;

    private UUID avatarId;

    private boolean archived;

    private Long workspaceId;

    private UUID assignedUser;

    private List<Long> clickAppsId;

    private List<Long> viewId;
}
