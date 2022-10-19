package com.amirscode.clickup.entity;

import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
<<<<<<< HEAD
import javax.persistence.OneToOne;
=======
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
>>>>>>> 5de2541 (space and project CRUD)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class SpaceView extends AbsLongEntity {

    @OneToOne
    private Space space;

<<<<<<< HEAD
    @OneToOne
    private View view;
=======
    @OneToMany
    private List<View> view;
>>>>>>> 5de2541 (space and project CRUD)
}
