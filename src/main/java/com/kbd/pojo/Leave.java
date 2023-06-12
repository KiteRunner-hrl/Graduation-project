package com.kbd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Leave {
  private int id;
  private int userId;
  private String userName;
  private String lessonName;
  private String leaveType;
  private String leaveReason;
  private String approveTeacher;
  private Integer status;
  private String reject;
}
