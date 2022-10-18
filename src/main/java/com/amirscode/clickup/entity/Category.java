package com.amirscode.clickup.entity;

import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Category extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    private String accessType;

    private String color;

    private boolean archived;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
}
