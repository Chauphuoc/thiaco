package com.example.thiaco.service;

import com.example.thiaco.security.UserPrinciple;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    // phần chữ kí
    private static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    //khoảng thời gian sống của token theo ms
    private static final long JWT_TOKEN_VALIDITY = 1000L * 60 * 60;

    public String generateToken(Authentication authentication){
        // từ đối tượng authentication thì lấy ra UserPrincipal để lấy thông tin người dùng
        UserPrinciple principal = (UserPrinciple) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", principal.getUsername());
        claims.put("role", principal.getRole());

        return Jwts.builder()
                .setSubject((principal.getUsername())) // chủ đề (username)
                .addClaims(claims) // thông tin tùy chon dưới dạng map (fullName và role)

//                thời điểm token được tạo ra
                .setIssuedAt(new Date())
//               set time chết của token = hôm nay, tính từ thời điểm tạo + time set ở JWT_TOKEN_VALIDITY, tính theo ms
                .setExpiration(new Date(new Date().getTime() + JWT_TOKEN_VALIDITY))
//               chèn chư kí vào đoạn ma token được băm theo kiểu HS256
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }
    public boolean validateJwtToken(String token){
//        xác nhận đúng mã token hay chưa
        try{
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
//            String subject = claims.getSubject();
            return true;
        }catch (Exception e){
//            mã token không hợp lệ, trả về false
            e.printStackTrace();
            return false;
        }
    }
    public String getUserNameFromJwtToken(String jwtToken){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims.getSubject();
    }
}
