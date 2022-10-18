package com.amirscode.clickup.entity;

import com.amirscode.clickup.template.AbsLongEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Icon extends AbsLongEntity {

    private String color;

    @OneToOne
    private Attachment attachment;

}
