package com.service;

import com.entity.UserBack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
  UserBack login(String username, String password, HttpServletRequest request, HttpServletResponse response);
}
