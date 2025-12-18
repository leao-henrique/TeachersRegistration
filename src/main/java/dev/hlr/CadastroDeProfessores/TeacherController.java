package dev.hlr.CadastroDeProfessores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Cadastro de Professores!";
    }

    @PostMapping
    public ResponseEntity<TeacherModel> registerTeacher(@RequestBody TeacherModel teacher) {
        return new ResponseEntity<>(teacherService.registerTeacher(teacher), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TeacherModel>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.findAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherModel> getTeacherById(@PathVariable Long id) {
        return teacherService.findTeacherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherModel> updateTeacher(@PathVariable Long id, @RequestBody TeacherModel teacherDetails) {
        try {
            return ResponseEntity.ok(teacherService.updateTeacher(id, teacherDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchTeachers(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String department) {

        if (email != null) {
            return teacherService.findTeacherByEmail(email)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        if (department != null) {
            return ResponseEntity.ok(teacherService.findTeachersByDepartment(department));
        }

        return ResponseEntity.badRequest().body("Either 'email' or 'department' parameter is required.");
    }
}
