package com.example.demo01.ojt01.Model.repository;

import com.example.demo01.ojt01.Model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(nativeQuery = true, value = "select s.studentId,s.studentName,s.age,s.status\n" +
            "from  student s\n" +
            "group by studentId\n" +
            "order by studentName desc limit :limit offset :page")
    List<Student> softByNameDesc(@Param("page") int page,@Param("limit")int limit);

    @Query(nativeQuery = true, value = "select s.studentId,s.studentName,s.age,s.status\n" +
            "from  student s\n" +
            "group by studentId\n" +
            "order by studentName asc limit :limit offset :page")
    List<Student> softByNameAsc(@Param("page") int page,@Param("limit")int limit);


    @Query(nativeQuery = true, value = "select s.studentId,s.studentName,s.age,s.status\n" +
            "from  student s\n" +
            "group by studentId\n" +
            "order by age desc limit :limit offset :page")
    List<Student> softByAgeDesc(@Param("page") int page,@Param("limit")int limit);
    @Query(nativeQuery = true, value = "select s.studentId,s.studentName,s.age,s.status\n" +
            "from  student s\n" +
            "group by studentId\n" +
            "order by age asc limit :limit offset :page")
    List<Student> softByAgeAsc(@Param("page") int page,@Param("limit")int limit);

    @Query(value = "select s.studentId,s.studentName,s.age,s.status\n" +
            "from  student s\n" +
            "group by studentId\n" +
            "order by studentName  desc , age desc limit :limit offset :page",nativeQuery = true)
    List<Student> softByNameDescAndAgeDesc(@Param("page") int page,@Param("limit")int limit);

    @Query(value = "select s.studentId,s.studentName,s.age,s.status\n" +
            "from  student s\n" +
            "group by studentId\n" +
            "order by studentName  asc , age asc limit :limit offset :page",nativeQuery = true)
    List<Student> softByNameAscAndAgeAsc(@Param("page") int page,@Param("limit")int limit);

    @Query(value = "select s.studentId,s.studentName,s.age,s.status\n" +
            "from  student s\n" +
            "group by studentId\n" +
            "order by studentName  desc , age asc limit :limit offset :page",nativeQuery = true)
    List<Student> softByNameDescAndAgeAsc(@Param("page") int page,@Param("limit")int limit);

    @Query(value = "select s.studentId,s.studentName,s.age,s.status\n" +
            "from  student s\n" +
            "group by studentId\n" +
            "order by studentName  asc , age desc limit :limit offset :page",nativeQuery = true)
    List<Student> softByNameAscAndAgeDesc(@Param("page") int page,@Param("limit")int limit);

    List<Student> findByStudentNameContainingIgnoreCase(String name);

    @Query(value = "select ceil(count(studentId)/:size1) from student p ",nativeQuery = true)
    int totalPage( @Param("size1")int size);

}
