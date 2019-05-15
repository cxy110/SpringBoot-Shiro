package com.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

//@ConfigurationProperties(prefix = "bmd")
public class Bmd {
  //@Value("${Bmd.userid}")
  private Integer userid;
  private List<Integer> roles=new ArrayList<Integer>();

  public Integer getUserid() {
    return userid;
  }

  public void setUserid(Integer userid) {
    this.userid = userid;
  }

  public List<Integer> getRoles() {
    return roles;
  }

  public void setRoles(List<Integer> roles) {
    this.roles = roles;
  }
}
