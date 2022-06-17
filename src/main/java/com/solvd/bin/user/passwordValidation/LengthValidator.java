package com.solvd.bin.user.passwordValidation;

public class LengthValidator implements IValidate {
  @Override
  public void validate(String pass) {
    if (pass.length() > 8) {
      throw new RuntimeException("The password must contain at least 8 characters");
    }
  }
}
