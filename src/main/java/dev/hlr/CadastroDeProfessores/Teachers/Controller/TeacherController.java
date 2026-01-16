package dev.hlr.CadastroDeProfessores.Teachers.Controller;

import dev.hlr.CadastroDeProfessores.Teachers.Services.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> create(@RequestBody TeacherDTO data) {
        TeacherDTO created = service.register;
        return ResponseEntity.ok(created);
    }
}
