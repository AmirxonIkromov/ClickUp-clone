package com.amirscode.clickup.entity;

import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class TaskHistory extends AbsLongEntity {

    private String field;

    private String before;

    private String after;

    private Date date;

    private String data;

    @ManyToOne
    private Task task;
}
