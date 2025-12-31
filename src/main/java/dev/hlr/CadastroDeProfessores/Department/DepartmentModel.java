package dev.hlr.CadastroDeProfessores.Department;

import dev.hlr.CadastroDeProfessores.Teachers.TeacherModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_departments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
public class DepartmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    // @OneToMany, um department pode ter varios teachers.
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @ToString.Exclude // Evita o loop infinito do @Data com os relacionamentos JPA.
    private List<TeacherModel> teachers =  new ArrayList<>();
}
