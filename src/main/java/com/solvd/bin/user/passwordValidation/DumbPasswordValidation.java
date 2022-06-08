package com.solvd.bin.user.passwordValidation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DumbPasswordValidation implements IValidate {
  private List<String> listaContrasenas = leerArchivo();

  public List<String> leerArchivo() {
    Path pathArchivo = Paths.get("src/main/resources/passwordlist.txt");

    try {
      return Files.readAllLines(pathArchivo, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException("The file could not be opened" + e);
    }
  }

  @Override
  public void validate(String pass) {
    if (this.listaContrasenas.contains(pass)) {
      throw new RuntimeException("The password is not safe, it is one of the 10.000 dumb passwords.");
    }
  }
}