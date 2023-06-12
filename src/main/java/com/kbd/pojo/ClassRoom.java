package com.kbd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClassRoom {
  private int id;
  private int teacherId;
  private String teacherName;
  private String className;
  private int classNum;
  private String classCode;
}
