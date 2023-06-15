package com.myproject.demo.enums;

public enum ErrorCode {
  
   STUDEMO_1000("Minimum length should be 3!", ""),
   STUDEMO_1001("DOB is mandtory!", ""),
   STUDEMO_1002("Marks range is 0 to 100.!", ""),
   STUDEMO_1003("Marks1 is mandtory!!", ""),
   STUDEMO_1004("Marks2 is mandtory!!", ""),
   STUDEMO_1005("Marks3 is mandtory!!", ""),
   STUDEMO_1006("Age should be greater than 15 year and less than or equal to 20 years!", ""),
   STUDEMO_1007("Invalid UserId","");
   ;
   
   private final String message;
   private final String attributeCode;

   private ErrorCode(String message, String attributeCode) {
      this.message = message;
      this.attributeCode = attributeCode;
   }

   public String getMessage() {
      return message;
   }

   public String getAttributeCode() {
      return attributeCode;
   }
}
