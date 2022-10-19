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

public class SpaceClickApps extends AbsLongEntity {

    @OneToOne
    private Space space;

<<<<<<< HEAD
    @OneToOne
    private ClickApps clickApps;
=======
    @OneToMany
    private List<ClickApps> clickApps;
>>>>>>> 5de2541 (space and project CRUD)
}
