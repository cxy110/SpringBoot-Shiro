package com.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@ConfigurationProperties(prefix = "token")

public class TokenAuto {
  //432_000_000

 private int  time=60;     // 5天


//jwt加密
  public String getToken(String username, String password) {
    String token = JWT.create().withAudience(username)
          .withExpiresAt(new Date(System.currentTimeMillis()+time*1000)) //毫秒
        .sign(Algorithm.HMAC256(password));
    return token;
  }

}
