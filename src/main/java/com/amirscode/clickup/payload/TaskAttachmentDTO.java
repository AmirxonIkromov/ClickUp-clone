package com.amirscode.clickup.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class TaskAttachmentDTO{

    @NotNull
    private UUID taskId;

    @NotNull
    private UUID attachmentId;

    private boolean pinCoverImage;
}
