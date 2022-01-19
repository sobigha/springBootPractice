package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping(path = "/addStudent", consumes = {"application/json"})
    public Student add(@RequestBody Student student){
        return service.saveStudent(student);
    }

    @PostMapping(path = "/addStudents", consumes = {"application/json"})
    public List<Student> addAll(@RequestBody List<Student> studentList){
        return service.saveStudents(studentList);
    }

    @GetMapping(path = "/getAll", produces = {"application/json"})
    public List<Student> getAll() {
      //  throw new ApiRequestException("Cannot get all students");
       return service.getAll();
     }

    @GetMapping(path = "/get/{id}", produces = {"application/json"})
    public ResponseEntity<?> get(@PathVariable int id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            Student student1 = service.getById(id);
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(student1);
        } catch (Exception exception) {
            httpHeaders.add("Message", "false");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body("Failed to get the user");
        }
    }
    @PutMapping("/update/{id}")
    public Student update(@RequestBody Student student, @PathVariable int id){
        return service.update(student, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body("Successfully Deleted");
        } catch (Exception exception) {
            httpHeaders.add("Message", "false");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body("Failed to delete the user");
        }
    }

    @GetMapping("/byName/{name}")
    public List<Student> byName(@PathVariable String name){
         return service.byName(name);
    }

    @GetMapping("/count/{name}")
    public Long count(@PathVariable String name){
        return service.countByName(name);
    }

    @GetMapping("/place/{place}")
    public List<Student> byPlace(@PathVariable String place){
        return service.byPlace(place);
    }

    @GetMapping("/name/place/{name}/{place}")
    public List<Student> byNameAndPlace(@PathVariable String name, @PathVariable String place){
        return service.byNameAndPlace(name,place);
    }

    @GetMapping("/name1/place1/{name}/{place}")
    public List<Student> byNameAndPlace(@PathVariable Map<String, String> map){
        String name = map.get("name");
        String place = map.get("place");
        return service.byNameAndPlace(name,place);
    }

}
