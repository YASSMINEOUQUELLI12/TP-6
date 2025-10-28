package com.example.tp6.Controllers;


import com.example.tp6.Service.StudentService;
import com.example.tp6.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void testFindAllStudents() {

        Student student1 = new Student();
        student1.setId(1);
        student1.setNom("Ali");
        student1.setPrenom("Karim");

        Student student2 = new Student();
        student2.setId(2);
        student2.setNom("Sofia");
        student2.setPrenom("Laila");


        when(studentService.findAll()).thenReturn(Arrays.asList(student1, student2));


        ResponseEntity<List<Student>> response = studentController.findAll();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Ali", response.getBody().get(0).getNom());
    }
}
