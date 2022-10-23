package com.amirscode.clickup.entity;

import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class SpaceView extends AbsLongEntity {

    @OneToOne
    private Space space;

    @OneToMany
    private List<View> view;
}
