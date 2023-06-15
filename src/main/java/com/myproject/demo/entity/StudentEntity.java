package com.myproject.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.myproject.demo.enums.CalssSectionEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@Entity
@ToString
@Table(name = "student")
public class StudentEntity {
   
   @Id
   @Column(name = "id", nullable = false)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   
   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;
   
   @Column(name = "dob")
   private LocalDate dob;
   
   @Column(name = "section")
   private CalssSectionEnum section;
   
   @Column(name = "gender")
   private String gender;
   
   @Column(name = "marks1")
   private Integer marks1;
   
   @Column(name = "marks2")
   private Integer marks2;
   
   @Column(name = "marks3")
   private Integer marks3;
   
   @Column(name = "total")
   private Integer total;
   
   @Column(name = "average")
   private Double average;
   
   @Column(name = "result")
   private String result;

}
