package dev.hlr.CadastroDeProfessores.Teachers;
import dev.hlr.CadastroDeProfessores.Department.DepartmentModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_registration")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
public class TeacherModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    // @ManyToOne, muitos teacher tem apenas uma missao.
    @ManyToOne(fetch = FetchType.LAZY) // Para carregar apenas quando for chamado.
    @JoinColumn(name = "department_id") // FK
    @ToString.Exclude
    private DepartmentModel department;

    private LocalDateTime hireDate;

    private List<DepartmentModel> departments;


}
