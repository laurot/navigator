package com.solvd.bin.user.passwordValidation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DumbPasswordValidation implements IValidate {
  private List<String> passwordList = readFile();

  public List<String> readFile() {
    Path pathFile = Paths.get("src/main/resources/passwordlist.txt");

    try {
      return Files.readAllLines(pathFile, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException("The file could not be opened" + e);
    }
  }

  @Override
  public void validate(String pass) {
    if (this.passwordList.contains(pass)) {
      throw new RuntimeException("The password is not safe, it is one of the 10.000 dumb passwords.");
    }
  }
}