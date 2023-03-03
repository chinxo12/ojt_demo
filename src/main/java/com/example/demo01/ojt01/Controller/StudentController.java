package com.example.demo01.ojt01.Controller;

import com.example.demo01.ojt01.Model.entity.Student;
import com.example.demo01.ojt01.Model.sevices.StudentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("OJT/v1/Student")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentSevice studentSevice;

    @GetMapping("findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(studentSevice.findALl());
    }

    @PostMapping("createStudent")
    public ResponseEntity<?> insertStudent(@RequestBody Student student) {
        boolean check = studentSevice.save(student);
        if (check) {
            return ResponseEntity.ok("Thêm mới thành công!");
        } else {
            return ResponseEntity.ok("Thêm mới thất bại!");
        }
    }

    @GetMapping("{studentId}")
    public ResponseEntity<?> findById(@PathVariable("studentId") int studentId) {
        return ResponseEntity.ok(studentSevice.findById(studentId));
    }
    @DeleteMapping("{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable("studentId") int studentId){
        boolean check = studentSevice.delete(studentId);
        if (check){
            return ResponseEntity.ok("Xóa thành công!");
        }else {
            return ResponseEntity.ok("Xóa thất bại!");
        }
    }
    @PutMapping("{studentId}")
    public ResponseEntity<?> updateStudent(@RequestBody Student student,@PathVariable("studentId") int studentId){
        student.setStudentId(studentId);
        boolean check = studentSevice.save(student);
        if (check) {
            return ResponseEntity.ok("Sửa thông tin sinh viên thành công!");
        } else {
            return ResponseEntity.ok("Sửa thông tin sinh viên thất bại!");
        }
    }
    @GetMapping("searchByName")
    public ResponseEntity<?> searchByName(@RequestParam("searchName") String name){
        return ResponseEntity.ok(studentSevice.searchByName(name));
    }
    @GetMapping("pagging")
    public ResponseEntity<?> pagging( @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "3") int limit){
        int realPage = page*limit;
        Pageable pageable = PageRequest.of(realPage,limit);
        Page<Student> pageStudent = studentSevice.pagging(pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("Student",pageStudent.getContent());
        data.put("total",pageStudent.getSize());
        data.put("totalItems",pageStudent.getTotalElements());
        data.put("totalPage",pageStudent.getTotalPages());
        return ResponseEntity.ok(data);
    }

    @GetMapping("softByName")
    public ResponseEntity<?> softByName(@RequestParam("direction")String direction,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "3") int size){
        int realPage = page*size;
        List<Student> listStudent = studentSevice.softByName(direction,realPage,size);
        int total = studentSevice.totalPage(size);
        Map<String,Object> data = new HashMap<>();
        data.put("totalPage",total);
        data.put("student",listStudent);
        return ResponseEntity.ok(data);
    }
    @GetMapping("softByAge")
    public ResponseEntity<?> softByAge(@RequestParam("direction")String direction,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "3") int size){
        int realPage = page*size;
        List<Student> listStudent = studentSevice.softByAge(direction,realPage,size);
        int total = studentSevice.totalPage(size);
        Map<String,Object> data = new HashMap<>();
        data.put("totalPage",total);
        data.put("student",listStudent);
        return ResponseEntity.ok(data);
    }

    @GetMapping("softByNameAndAge")
    public ResponseEntity<?> softByNameAndAge(@RequestParam("directionName")String directionName,
                                              @RequestParam("directionAge")String directionAge,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "3") int size){
        int realPage = page*size;
        List<Student> listStudent = studentSevice.softByNameAndAge(directionName,directionAge,realPage,size);
        int total = studentSevice.totalPage(size);
        Map<String,Object> data = new HashMap<>();
        data.put("totalPage",total);
        data.put("student",listStudent);
        return ResponseEntity.ok(data);
    }


}
