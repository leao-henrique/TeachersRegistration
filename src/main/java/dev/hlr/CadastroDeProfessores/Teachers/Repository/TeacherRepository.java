package dev.hlr.CadastroDeProfessores.Teachers.Repository;

import dev.hlr.CadastroDeProfessores.Teachers.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherModel, Long> {

}
