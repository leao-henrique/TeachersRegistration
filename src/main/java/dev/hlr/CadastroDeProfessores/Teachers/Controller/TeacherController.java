package dev.hlr.CadastroDeProfessores.Teachers.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TeacherController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Cadastro de Professores!";
    }

}
