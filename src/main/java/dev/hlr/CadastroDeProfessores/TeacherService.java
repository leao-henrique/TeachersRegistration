package dev.hlr.CadastroDeProfessores;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    /**
     * Registers a new teacher.
     * Validates email uniqueness.
     */
    @Transactional
    public TeacherModel registerTeacher(TeacherModel teacher) {
        if (teacherRepository.findByEmail(teacher.getEmail()).isPresent()) {
            throw new RuntimeException("Teacher with email " + teacher.getEmail() + " already exists.");
        }

        if (teacher.getHireDate() == null) {
            teacher.setHireDate(LocalDateTime.now());
        }

        return teacherRepository.save(teacher);
    }

    /**
     * Updates an existing teacher.
     * Only non-null fields from the request are updated.
     */
    @Transactional
    public TeacherModel updateTeacher(Long id, TeacherModel teacherDetails) {
        TeacherModel teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        if (teacherDetails.getFirstName() != null) {
            teacher.setFirstName(teacherDetails.getFirstName());
        }
        if (teacherDetails.getLastName() != null) {
            teacher.setLastName(teacherDetails.getLastName());
        }
        if (teacherDetails.getEmail() != null) {
            // If email is changing, check for uniqueness
            if (!teacherDetails.getEmail().equals(teacher.getEmail())) {
                if (teacherRepository.findByEmail(teacherDetails.getEmail()).isPresent()) {
                    throw new RuntimeException("Email " + teacherDetails.getEmail() + " is already in use.");
                }
            }
            teacher.setEmail(teacherDetails.getEmail());
        }
        if (teacherDetails.getDepartment() != null) {
            teacher.setDepartment(teacherDetails.getDepartment());
        }
        if (teacherDetails.getHireDate() != null) {
            teacher.setHireDate(teacherDetails.getHireDate());
        }

        return teacherRepository.save(teacher);
    }

    /**
     * Deletes a teacher by ID.
     */
    @Transactional
    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        teacherRepository.deleteById(id);
    }

    public List<TeacherModel> findAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<TeacherModel> findTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public Optional<TeacherModel> findTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    public List<TeacherModel> findTeachersByDepartment(String department) {
        return teacherRepository.findByDepartment(department);
    }
}
