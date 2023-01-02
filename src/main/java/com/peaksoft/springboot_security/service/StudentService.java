package com.peaksoft.springboot_security.service;


import com.peaksoft.springboot_security.dto.StudentRequest;
import com.peaksoft.springboot_security.dto.StudentResponse;
import com.peaksoft.springboot_security.entity.Student;
import com.peaksoft.springboot_security.repository.StudentRepository1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository1 studentRepository1;

    public StudentResponse create(StudentRequest request){
        Student student = mapToEntity(request);
        studentRepository1.save(student);
        return studentResponse(student);
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Optional<Student> student = studentRepository1.findById(id);
        if (student.isEmpty()){
            System.out.println("Student id not found");
    }
    mapToUpdateStudent(student.get(),request);
        return studentResponse(studentRepository1.save(student.get()));
    }

    public StudentResponse getById(Long id) {
        Student student = studentRepository1.findById(id).get();
        return studentResponse(student);
    }

    public void deleteById(Long id) {
        Student student = studentRepository1.findById(id).get();
        studentRepository1.delete(student);
    }

    public Student mapToEntity(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setCreated(LocalDate.now());
        student.setDeleted(student.getDeleted());
        student.setIsActive(student.getIsActive());
        return student;
    }

    public void mapToUpdateStudent(Student student, StudentRequest request) {
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
    }

    public StudentResponse studentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .email(student.getEmail())
                .created(student.getCreated())
                .isActive(student.getIsActive())
                .build();
    }

    public List<Student> getAllStudents() {
        return studentRepository1.findAll();
    }


    public List<StudentResponse> view(List<Student> students){
        List<StudentResponse> responses = new ArrayList<>();
        for(Student  s: students){
            responses.add(studentResponse(s));
        }
        return responses;
    }

    private List<Student> search(String name, Pageable pageable){
        String text = name == null ? "" : name;
        return studentRepository1.searchAndPagination(text.toUpperCase(), (PageRequest) pageable);
    }


}

