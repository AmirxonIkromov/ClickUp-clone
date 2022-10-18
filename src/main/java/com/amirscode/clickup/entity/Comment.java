package com.amirscode.clickup.entity;


import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Comment extends AbsLongEntity {

    private String text;

    @OneToOne
    private Task task;
}
