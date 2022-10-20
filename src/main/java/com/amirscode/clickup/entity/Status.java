package com.amirscode.clickup.entity;

import com.amirscode.clickup.enums.StatusType;
import com.amirscode.clickup.template.AbsLongEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

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

    @ManyToOne
    private Space space;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Category category;

}
