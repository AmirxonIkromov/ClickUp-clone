package com.amirscode.clickup.entity;

import com.amirscode.clickup.template.AbsLongEntity;
import com.amirscode.clickup.template.AbsUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Task extends AbsUUIDEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @JoinColumn(nullable = false)
    @OneToOne
    private Status status;

    @JoinColumn(nullable = false)
    @OneToOne
    private Category category;

    @OneToOne
    private Priority priority;

    @OneToOne
    private Task parentTask;

    private Date staredDate;

    private Date dueDate;

    private Long estimateTime;

    private Date activatedDate;

}
