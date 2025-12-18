package dev.hlr.CadastroDeProfessores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel, Long> {

    /**
     * Custom query to find a teacher by their email.
     * 
     * @param email the email to search for
     * @return an Optional containing the teacher if found, or empty otherwise
     */
    Optional<TeacherModel> findByEmail(String email);

    /**
     * Custom query to find all teachers in a specific department.
     * 
     * @param department the department to search for
     * @return a list of teachers in the specified department
     */
    List<TeacherModel> findByDepartment(String department);
}
