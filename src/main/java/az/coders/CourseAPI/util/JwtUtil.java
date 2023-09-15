package az.coders.CourseAPI.util;

import az.coders.CourseAPI.config.SecurityConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    @Autowired
    SecurityConfig securityConfig;

    @Value("${jwt.secret}")
    private static String SECRET_KEY;

    @Value("${jwt.expirationdate}L")
    private static Long EXPIRATION_DATE;
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuer("Nicat")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .signWith(encoder(SECRET_KEY),SignatureAlgorithm.HS256)
                .compact();
    }

  public String validateToken(String token){

  }

public String getUsernameToken(String token){
    Claims claims = Jwts.parser().setSigningKey(encoder(SECRET_KEY)).parseClaimsJws(token).getBody();
return claims.getSubject();
}

private Key encoder(String secretKey){
   return Keys.hmacShaKeyFor(secretKey.getBytes());
}






}
