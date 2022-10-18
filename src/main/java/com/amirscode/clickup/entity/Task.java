package com.amirscode.clickup.entity;

import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Task extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToOne
    private Status status;

    @OneToOne
    private Category category;

    @OneToOne
    private Priority priority;

    @OneToOne
    private Task parentTask;

    @Column(nullable = false)
    private Timestamp staredDate;

    private Timestamp dueDate;

    private Long estimateTime;

    private Timestamp activatedDate;

}
