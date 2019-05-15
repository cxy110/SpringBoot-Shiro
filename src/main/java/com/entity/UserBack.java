package com.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserBack {
  User user;
  Integer roleId;
  String token;
 Date tokentime;
}
