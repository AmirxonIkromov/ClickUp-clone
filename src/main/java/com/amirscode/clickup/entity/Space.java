package com.amirscode.clickup.entity;


import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

=======
import javax.persistence.*;

@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "workspace_id"})})
>>>>>>> 5de2541 (space and project CRUD)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Space extends AbsLongEntity {

    private String name;

    private String  color;

    private String initialLetter;

    private String accessType;

<<<<<<< HEAD
=======
    private boolean archived;

>>>>>>> 5de2541 (space and project CRUD)
    @OneToOne(fetch = FetchType.LAZY)
    private Icon icon;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment avatar;

    @OneToOne(fetch = FetchType.LAZY)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Workspace workspace;

<<<<<<< HEAD
=======
    @PrePersist
    @PreUpdate
    public void setInitialLetterMyMethod() {
        this.initialLetter = name.substring(0, 1);
    }

>>>>>>> 5de2541 (space and project CRUD)
}
