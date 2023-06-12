package com.kbd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentSignIn {
  private int id;
  private int classId;
  private int sid;
  private int userId;
  private String username;
  private String finish;
}
