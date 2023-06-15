package com.myproject.demo.exception;

import java.util.List;

import com.myproject.demo.dto.ErrorDTO;

import lombok.Getter;

@Getter
public class InvalidParameterException extends DemoException{
   
   private Integer status;

   private static final long serialVersionUID = -6406289686808222972L;

   public InvalidParameterException(Object dto, List<ErrorDTO> errors) {
      super(dto, errors.toArray(new ErrorDTO[] {}));
   }

   public InvalidParameterException(final Integer status, Object dto, List<ErrorDTO> errors) {
      super(dto, errors.toArray(new ErrorDTO[] {}));
      this.status = status;
   }

}
