package com.myproject.demo.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myproject.demo.enums.CalssSectionEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateStudentRequestDTO {
   
   private String firstName;
   private String lastName;
   private LocalDate   dob;
   private CalssSectionEnum   section;
   private String   gender;
   private Integer   marks1;
   private Integer   marks2;
   private Integer   marks3;
   private Integer   total;
   private Double  Average;
   private String result;

}
