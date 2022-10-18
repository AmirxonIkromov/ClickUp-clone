package com.amirscode.clickup.entity;

import com.amirscode.clickup.enums.StatusType;
import com.amirscode.clickup.template.AbsLongEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Status extends AbsLongEntity {

    private String name;

    private String color;

    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @OneToOne
    private Space space;

    @OneToOne
    private Project project;

    @OneToOne
    private Category category;

}
