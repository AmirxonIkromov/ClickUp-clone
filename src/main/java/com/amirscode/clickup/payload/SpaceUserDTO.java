package com.amirscode.clickup.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class SpaceUserDTO {

    @NotNull
    private Long spaceId;

    @NotNull
    private UUID memberId;
}
