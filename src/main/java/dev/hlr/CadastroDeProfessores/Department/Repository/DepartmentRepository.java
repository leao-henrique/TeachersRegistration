package dev.hlr.CadastroDeProfessores.Department.Repository;

import dev.hlr.CadastroDeProfessores.Department.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long> {


}
