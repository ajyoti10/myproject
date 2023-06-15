package com.myproject.demo.enums;

import org.apache.commons.lang3.StringUtils;


public enum CalssSectionEnum {
   
   A("A"),
   B("B"),
   C("C");
   
   private String type;
   
   CalssSectionEnum(String type) {
      this.type = type;
   }

   public String getType() {
      return type;
   }

   public static CalssSectionEnum getSectionTypeMethod(String type) {
      if (StringUtils.isEmpty(type))
         return null;

      for (CalssSectionEnum e : CalssSectionEnum.values()) {
         if (e.getType().equals(type))
            return e;
      }
      return null;
   }

}
