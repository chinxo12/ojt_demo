package com.example.demo01.ojt01.Model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "studentId")
    private int studentId;
    @JoinColumn(name = "studentName")
    private String studentName;
    @JoinColumn(name = "age")
    private int age;
    @JoinColumn(name = "status")
    private boolean status;
}
