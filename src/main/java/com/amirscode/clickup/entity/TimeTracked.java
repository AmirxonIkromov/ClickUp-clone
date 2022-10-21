package com.amirscode.clickup.entity;

import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class TimeTracked extends AbsLongEntity {

    @ManyToOne
    private Task task;

    private Timestamp startedAt;

    private Timestamp stoppedAt;
}
