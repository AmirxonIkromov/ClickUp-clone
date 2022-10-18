package com.amirscode.clickup.entity;

import com.amirscode.clickup.enums.TaskPermissionName;
import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class CategoryUser extends AbsLongEntity {

    private String name;

    @ManyToOne
    private Category category;

    @OneToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private TaskPermissionName permissionName;
}
