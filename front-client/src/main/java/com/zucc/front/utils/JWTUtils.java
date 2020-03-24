package com.zucc.front.utils;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTUtils {

  // 有效时间 5分钟
  private static final long ttlMillis = 5 * 60 * 1000;
  
  static String SECRETKEY = "vWCWfijhoicgFOpdxxSqkpUFNAPTDSRRVcpvZEWjEXKqXNtJgkV";

  /**
   * 由字符串生成加密key
   * 
   * @return
   */
  public static SecretKey generalKey(String stringKey) {
    byte[] encodedKey = Base64.decodeBase64(stringKey);
    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    return key;
  }

  /**
   * 构建jwt
   * @param id
   * @param subject
   * @param ttlMillis
   * @return
   * @throws Exception
   */
  public static String createJWT(String subject) throws Exception {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    SecretKey key = generalKey(SECRETKEY);
    JwtBuilder builder = Jwts.builder().setIssuer("").setId(UUIDUtils.getUUID()).setIssuedAt(now).setSubject(subject)
        .signWith(signatureAlgorithm, key);
    long expMillis = nowMillis + ttlMillis;
    Date exp = new Date(expMillis);
    builder.setExpiration(exp);
    return builder.compact();
  }

  /**
   * 解析jwt
   * @param jwt
   * @return
   * @throws ExpiredJwtException
   * @throws UnsupportedJwtException
   * @throws MalformedJwtException
   * @throws SignatureException
   * @throws IllegalArgumentException
   */
  public static Claims parseJWT(String jwt) throws ExpiredJwtException, UnsupportedJwtException,
      MalformedJwtException, SignatureException, IllegalArgumentException {
    SecretKey key = generalKey(SECRETKEY);
    Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
    return claims;
  }
}
