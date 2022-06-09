package com.solvd.bin.user.passwordValidation;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {
  private List<IValidate> validators = new ArrayList<>();

  public void validateAtributes(String password, String userName) {
    getValidations().forEach(validator -> validator.validate(password));
  }

  public void addValidator(IValidate validator) {
    validators.add(validator);
  }

  private List<IValidate> getValidations() {
    return validators;
  }
}
