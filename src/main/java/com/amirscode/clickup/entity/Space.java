package com.amirscode.clickup.entity;


import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import javax.persistence.*;

@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "workspace_id"})})
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Space extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    private String  color;

    private String initialLetter;

    private String accessType;

    private boolean archived;

    @OneToOne(fetch = FetchType.LAZY)
    private Icon icon;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment avatar;

    @OneToOne(fetch = FetchType.LAZY)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Workspace workspace;

    @PrePersist
    @PreUpdate
    public void setInitialLetterMyMethod() {
        this.initialLetter = name.substring(0, 1);
    }

}
