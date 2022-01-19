package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    //post - create
    public Student saveStudent(Student student){
        return studentRepo.save(student);
    }

    public List<Student> saveStudents(List<Student> students){
        return studentRepo.saveAll(students);
    }

    // get - Read
    public List<Student> getAll(){
        return studentRepo.findAll();
    }

    public Student getById(int id){
        Student student1 = studentRepo.findById(id).get();
        return student1;
    }

    //delete
    public void delete(int id){
        studentRepo.deleteById(id);
    }

    // put - update
    public Student update(Student student, int id) {

        Student student1 = studentRepo.findById(id).orElseThrow(() -> new ApiRequestException("id not present"));
        student1.setPlace(student.getPlace());
        student1.setName(student.getName());
        return studentRepo.save(student1);

        }

        public List<Student> byName(String name){
           List<Student> student1 = studentRepo.findByName(name);
           return student1;
        }

        public Long countByName(String name){
            Long count = studentRepo.countByName(name);
            return count;
        }

        public List<Student> byPlace(String place){
            List<Student> list = studentRepo.findDistinctByPlace(place);
            return list;
        }

        public List<Student> byNameAndPlace(String name,String place){
            List<Student> list = studentRepo.findByNameAndPlace(name,place);
            return list;
         }
    }


