package az.coders.CourseAPI.util;

import az.coders.CourseAPI.config.SecurityConfig;
import az.coders.CourseAPI.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
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

    @Value("${jwt.secret}")
    private static String SECRET_KEY;

    @Value("${jwt.expirationdate}")
    private static Long EXPIRATION_DATE;

    private static String TOKEN_HEADER = "Authorization";

    private static  String TOKEN_PREFIX ="Bearer ";


    public String generateToken(UserEntity userEntity) {
        return Jwts.builder()
                .setSubject(userEntity.getUsername())
                .setIssuer("Nicat")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .signWith(encoder(SECRET_KEY),SignatureAlgorithm.HS256)
                .compact();
    }
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

  public Boolean validateToken(String token){
      return token != null && !isExpired(token);
  }

public String getUsernameToken(String token){
    Claims claims = getClaims(SECRET_KEY,token);
return claims.getSubject();
}

private Boolean isExpired(String token){
    return getClaims(SECRET_KEY,token).getExpiration().after(new Date());
}
private Claims getClaims(String secretKey,String token){
   return Jwts.parser().setSigningKey(encoder(secretKey)).parseClaimsJws(token).getBody();
}



private Key encoder(String secretKey){
   return Keys.hmacShaKeyFor(secretKey.getBytes());
}






}
