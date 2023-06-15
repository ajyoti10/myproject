package com.myproject.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.demo.dto.CreateStudentRequestDTO;
import com.myproject.demo.dto.BaseResponseDTO;

@RestController
public interface StudentController {
   
   @PostMapping(value = "/api/v1/student/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<BaseResponseDTO> createStudent(@RequestBody final CreateStudentRequestDTO createStudentRequestDTO, HttpServletRequest httpRequest);
   
   @PostMapping(value = "/api/v1/student/{userId}/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<BaseResponseDTO> updateStudent(@PathVariable("userId") final Integer userId,
                                                        @RequestBody final CreateStudentRequestDTO createStudentRequestDTO, 
                                                        HttpServletRequest httpRequest);

}
