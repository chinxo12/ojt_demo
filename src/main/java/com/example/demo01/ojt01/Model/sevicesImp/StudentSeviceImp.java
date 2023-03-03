package com.example.demo01.ojt01.Model.sevicesImp;

import com.example.demo01.ojt01.Model.entity.Student;
import com.example.demo01.ojt01.Model.repository.StudentRepository;
import com.example.demo01.ojt01.Model.sevices.StudentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSeviceImp implements StudentSevice {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public boolean save(Student student) {
        try {
            studentRepository.save(student);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
         try {
             studentRepository.deleteById(id);
             return true;
         }catch (Exception e){
             return false;
         }
    }

    @Override
    public List<Student> findALl() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Page<Student> pagging(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }


    @Override
    public List<Student> softByName(String soft, int page, int limit) {
        if (soft.equals("asc")){
            return studentRepository.softByNameAsc(page,limit);
        }else {
            return studentRepository.softByNameDesc(page,limit);
        }
    }

    @Override
    public List<Student> softByAge(String directionAge, int page, int limit) {
        if (directionAge.equals("asc")){
           return studentRepository.softByAgeAsc(page,limit);
        }else {
            return studentRepository.softByAgeDesc(page,limit);
        }
    }

    @Override
    public List<Student> softByNameAndAge(String directionName, String directionAge, int page, int limit) {
        if (directionName.equals("asc")){
            if (directionAge.equals("asc")){
                return studentRepository.softByNameAscAndAgeAsc(page,limit);
            }else {
                return studentRepository.softByNameAscAndAgeDesc(page,limit);
            }
        }else {
            if (directionAge.equals("asc")){
                return studentRepository.softByNameDescAndAgeAsc(page,limit);
            }else {
                return studentRepository.softByNameDescAndAgeDesc(page,limit);
            }
        }
    }

    @Override
    public List<Student> searchByName(String name) {
        return studentRepository.findByStudentNameContainingIgnoreCase(name);
    }

    @Override
    public int totalPage(int size) {
        return studentRepository.totalPage(size);
    }


}
