package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTests {

  @Test
  public void contextLoads() {
  }

  @Test
  public static void main(String[] args) {
    int min=10;
    int max=11;
    int field1=10;
    int field2=11;
    int keyword1=12;
    int keyword2=13;
    System.out.println("+"+field1+":"+keyword1+" "+"-"+field2+":"+keyword2);
  }
}
