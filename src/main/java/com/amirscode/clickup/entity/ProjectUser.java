package com.amirscode.clickup.entity;

import com.amirscode.clickup.enums.TaskPermissionName;
import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class ProjectUser extends AbsLongEntity {

    @OneToOne
    private Project project;

    @OneToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private TaskPermissionName Permission;
}
