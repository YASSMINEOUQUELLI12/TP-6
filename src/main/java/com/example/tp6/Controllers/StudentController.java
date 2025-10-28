package com.example.tp6.Controllers;
import com.example.tp6.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import com.example.tp6.Service.StudentService;

@RestController
@RequestMapping("/students") // le slash au début, c’est mieux
public class StudentController {

    private final StudentService studentService;

    // ✅ Injection par constructeur (pas besoin de @Autowired)
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save") // ✅ pas "PostMappina"
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student saved = studentService.save(student);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean ok = studentService.delete(id);
        return ok ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(studentService.countStudents());
    }

    @GetMapping("/byYear")
    public ResponseEntity<Collection<?>> byYear() {
        return ResponseEntity.ok(studentService.findNbrStudentByYear());
    }
}
