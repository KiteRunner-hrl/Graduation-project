package com.kbd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Users {
  private int id;

  private String fullName;

  private String username;

  private int classId;

  private String password;

  private String role;

  private String face_token;
}
