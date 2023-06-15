package com.myproject.demo.service;

import com.myproject.demo.dto.BaseResponseDTO;
import com.myproject.demo.dto.CreateStudentRequestDTO;

public interface IStudentService {
   
   BaseResponseDTO createStudent(final CreateStudentRequestDTO createStudentRequestDTO);
   
   BaseResponseDTO updateStudent(final Integer userId, final CreateStudentRequestDTO createStudentRequestDTO);

}
