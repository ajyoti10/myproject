package com.myproject.demo.exception;

import java.util.List;

import com.myproject.demo.dto.ErrorDTO;

import lombok.Getter;

@Getter
public class DemoException extends RuntimeException{

   private static final long serialVersionUID = 7932731402935556075L;

   protected final Object dto;
   protected final List<ErrorDTO> errors;

   public DemoException(Object dto, ErrorDTO... errors) {
      this.dto = dto;
      this.errors = List.of(errors);
   }
}
