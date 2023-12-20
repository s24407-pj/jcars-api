package edu.pjatk.jcarsapi.model;

import edu.pjatk.jcarsapi.model.Enums.ERoles;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ERoles name;

    public Role() {
    }

    public Role(ERoles name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERoles getName() {
        return name;
    }

    public void setName(ERoles name) {
        this.name = name;
    }
}
