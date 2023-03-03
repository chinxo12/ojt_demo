package com.example.demo01.ojt01.Model.sevices;

import com.example.demo01.ojt01.Model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface StudentSevice {
    boolean save(Student student);
    boolean delete(int id);
    List<Student> findALl();
    Student findById(int id);
    Page<Student> pagging(Pageable pageable);
    List<Student> softByName(String soft,int page, int limit);
    List<Student> softByAge(String directionAge,int page,int limit);
    List<Student> softByNameAndAge(String directionName,String directionAge,int page,int limit);
    List<Student> searchByName(String name);
    int totalPage(int size);
}
