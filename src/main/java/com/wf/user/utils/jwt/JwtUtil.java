package com.wf.user.utils.jwt;

import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author 玉米排骨汤
 * @Date 2023/12/30 19:44
 * @Package com.wf.user.utils.jwt
 * @Version 1.0
 * @Since 1.0
 */
public class JwtUtil {

    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    public static final String APP_SECRET = "weifengxueyuceng";

    public static String getJwtToken(long id,String userAccount){
        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")

                .setSubject("user-init")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))

                .claim("id", id)
                .claim("userAccount", userAccount)

                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    public static boolean checkToken(String jwtToken){
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean checkToken(HttpServletRequest request){
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Long getUserAccountId(HttpServletRequest request){
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        Integer id = (Integer) claims.get("id");
        long newId = id.longValue();
        return newId;

    }

}
