package com.solvd.bin.user.passwordValidation;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {
  private List<IValidate> validadores = new ArrayList<>();

  public void validateAtributes(String contrasenia, String nombreUsuario) {
    getValidations().forEach(validador -> validador.validate(contrasenia));
  }

  public void agregarValidador(IValidate validador) {
    validadores.add(validador);
  }

  private List<IValidate> getValidations() {
    return validadores;
  }
}
