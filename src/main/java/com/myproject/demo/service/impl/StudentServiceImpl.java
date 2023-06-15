package com.myproject.demo.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.myproject.demo.dto.BaseResponseDTO;
import com.myproject.demo.dto.CreateStudentRequestDTO;
import com.myproject.demo.dto.ErrorDTO;
import com.myproject.demo.entity.StudentEntity;
import com.myproject.demo.enums.ErrorCode;
import com.myproject.demo.exception.InvalidParameterException;
import com.myproject.demo.repository.StudentRepository;
import com.myproject.demo.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{
   
   private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
   
   @Autowired
   private StudentRepository studentRepository;
   
   @Override
   public BaseResponseDTO createStudent(final CreateStudentRequestDTO createStudentRequestDTO) {
      
      logger.info("createStudent API logic start   : ");
      if (!CollectionUtils.isEmpty(studentValidation(createStudentRequestDTO))) {
         throw new InvalidParameterException(createStudentRequestDTO, studentValidation(createStudentRequestDTO));         
       }
      
      final int total = createStudentRequestDTO.getMarks1() + createStudentRequestDTO.getMarks2() + createStudentRequestDTO.getMarks3();
      final Double average = (double) (total/3);
      String result = "Pass";
      if(createStudentRequestDTO.getMarks1() < 35 
         || createStudentRequestDTO.getMarks2() < 35
         || createStudentRequestDTO.getMarks3() <35) {
         result = "Fail";
      }
      StudentEntity studentData = StudentEntity.builder()
                                               .firstName(createStudentRequestDTO.getFirstName())
                                               .lastName(createStudentRequestDTO.getLastName())
                                               .dob(createStudentRequestDTO.getDob())
                                               .gender(createStudentRequestDTO.getGender())
                                               .section(createStudentRequestDTO.getSection())
                                               .marks1(createStudentRequestDTO.getMarks1())
                                               .marks2(createStudentRequestDTO.getMarks2())
                                               .marks3(createStudentRequestDTO.getMarks3())
                                               .total(total)
                                               .average(average)
                                               .result(result)
                                               .build();
      studentRepository.save(studentData);
      final BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
      baseResponseDTO.setStatus("Success");
      baseResponseDTO.setResponseMessage("Student created");
      return baseResponseDTO;
      
   }

   private List<ErrorDTO> studentValidation(final CreateStudentRequestDTO createStudentRequestDTO) {
      final List<ErrorDTO> errors = new ArrayList<>();
      if(  StringUtils.isNotEmpty(createStudentRequestDTO.getFirstName()) && createStudentRequestDTO.getFirstName().length() < 3
            ){
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1000));
      }
      
      if(  StringUtils.isNotEmpty(createStudentRequestDTO.getLastName()) && createStudentRequestDTO.getLastName().length() < 3
         ){
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1000));
      }
      
      if( createStudentRequestDTO.getDob()  == null){
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1001));
      }
      if( createStudentRequestDTO.getDob()  != null){
         final LocalDate currDate = LocalDate.now();
         final Period ageDiff = Period.between(createStudentRequestDTO.getDob(), currDate);          
         if(ageDiff.getYears() > 20 && ageDiff.getYears() < 15) {
            errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1006));
         }
      }
      
      if( createStudentRequestDTO.getMarks1() !=null && createStudentRequestDTO.getMarks1() < 0 && createStudentRequestDTO.getMarks1() > 100){
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1002));
      }
      
      if( createStudentRequestDTO.getMarks2() !=null && createStudentRequestDTO.getMarks2() < 0 && createStudentRequestDTO.getMarks2() > 100){
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1002));
      }
      
      if( createStudentRequestDTO.getMarks3() !=null && createStudentRequestDTO.getMarks3() < 0 && createStudentRequestDTO.getMarks3() > 100){
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1002));
      }
      
      return errors;
   }
   
   @Override
   public BaseResponseDTO updateStudent(final Integer userId, final CreateStudentRequestDTO createStudentRequestDTO) {
      
      final List<ErrorDTO> errors = new ArrayList<>();
      
      if(userId == null) {
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1007));
         throw new InvalidParameterException(userId, errors);  
         
      }
      
      updateStudentValidation(createStudentRequestDTO, errors);
      if(!CollectionUtils.isEmpty(errors)) {
         throw new InvalidParameterException(createStudentRequestDTO, errors); 
      }
      
      final Optional<StudentEntity> studentOp = studentRepository.findById(userId);
      
      if(!studentOp.isPresent()) {
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1007));
         throw new InvalidParameterException(userId, errors);  
         
      }
      final StudentEntity student = studentOp.get();
      final int total = createStudentRequestDTO.getMarks1() + createStudentRequestDTO.getMarks2() + createStudentRequestDTO.getMarks3();
      final Double average = (double) (total/3);
      String result = "Pass";
      if(createStudentRequestDTO.getMarks1() < 35 
         || createStudentRequestDTO.getMarks2() < 35
         || createStudentRequestDTO.getMarks3() <35) {
         result = "Fail";
      }
      
      student.setMarks1(createStudentRequestDTO.getMarks1());
      student.setMarks2(createStudentRequestDTO.getMarks2());
      student.setMarks3(createStudentRequestDTO.getMarks3());
      student.setTotal(total);
      student.setAverage(average);
      student.setResult(result);
      studentRepository.save(student);
      final BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
      baseResponseDTO.setStatus("Success");
      baseResponseDTO.setResponseMessage("Student updated");
      return baseResponseDTO;
   }
   
   private void updateStudentValidation(final CreateStudentRequestDTO createStudentRequestDTO, final List<ErrorDTO> errors) {
      if(createStudentRequestDTO.getMarks1() == null) {
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1003));
         
      }
      if(createStudentRequestDTO.getMarks2() == null) {
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1004));
         
      }
      if(createStudentRequestDTO.getMarks3() == null) {
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1004));
         
      }
      
      if( createStudentRequestDTO.getMarks1() !=null && createStudentRequestDTO.getMarks1() < 0 && createStudentRequestDTO.getMarks1() > 100){
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1002));
      }
      
      if( createStudentRequestDTO.getMarks2() !=null && createStudentRequestDTO.getMarks2() < 0 && createStudentRequestDTO.getMarks2() > 100){
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1002));
      }
      
      if( createStudentRequestDTO.getMarks3() !=null && createStudentRequestDTO.getMarks3() < 0 && createStudentRequestDTO.getMarks3() > 100){
         errors.add(ErrorDTO.build(ErrorCode.STUDEMO_1002));
      }
      
   }
}
