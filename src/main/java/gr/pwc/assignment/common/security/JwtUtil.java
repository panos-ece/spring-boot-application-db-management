package gr.pwc.assignment.common.security;

import gr.pwc.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    Logger log = LoggerFactory.getLogger(JwtUtil.class);


    private static final String SECRET = "a-very-long-secret-key-change-in-production";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_TIME = 1000 * 60 * 60L; //1h


    public String generateToken(AuthorizedPersonnel authPersonnel) {
//        log.info("The HMAC key for generation is: " + Base64.getEncoder().encodeToString(key.getEncoded()));
        return Jwts.builder()
                .subject(authPersonnel.getUsername())
                .claim("roles", authPersonnel.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

    private Date extractTokenExpiration(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractTokenExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, AuthorizedPersonnel user) {
//        log.info("The HMAC key for validation is: " + Base64.getEncoder().encodeToString(key.getEncoded()));
//        log.info("user is " + user.getUsername());
//        log.info("token user is " + extractUsername(token));
        return (extractUsername(token).equals(user.getUsername()) && !isTokenExpired(token));
    }
}
