package com.amirscode.clickup.payload;

import com.amirscode.clickup.enums.AddType;
import lombok.Data;

import java.util.UUID;

@Data
public class MemberDTO {
    private UUID id;

    private UUID roleId;

    private AddType addType;//ADD, EDIT, REMOVE
}
