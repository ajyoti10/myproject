package com.myproject.demo.controller.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.demo.controller.StudentController;
import com.myproject.demo.dto.BaseResponseDTO;
import com.myproject.demo.dto.CreateStudentRequestDTO;
import com.myproject.demo.service.IStudentService;

@Service
public class StudentControllerImpl implements StudentController{
   
   private static final Logger logger = LoggerFactory.getLogger(StudentControllerImpl.class);
   
   @Autowired IStudentService iStudentService;
   
   @Override
   public ResponseEntity<BaseResponseDTO> createStudent(final CreateStudentRequestDTO createStudentRequestDTO, HttpServletRequest httpRequest){
      return ResponseEntity.ok(iStudentService.createStudent(createStudentRequestDTO));
   }
   
   public ResponseEntity<BaseResponseDTO> updateStudent(final Integer userId,
                                                        final CreateStudentRequestDTO createStudentRequestDTO, 
                                                        HttpServletRequest httpRequest){
      return ResponseEntity.ok(iStudentService.updateStudent(userId, createStudentRequestDTO));
   }

}
