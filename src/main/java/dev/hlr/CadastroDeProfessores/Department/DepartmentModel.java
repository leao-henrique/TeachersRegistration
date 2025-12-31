package dev.hlr.CadastroDeProfessores.Department;

import dev.hlr.CadastroDeProfessores.Teachers.TeacherModel;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_departments")
public class DepartmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // @OneToMany, um department pode ter varios teachers.
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<TeacherModel> teachers =  new ArrayList<>();

    public DepartmentModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
