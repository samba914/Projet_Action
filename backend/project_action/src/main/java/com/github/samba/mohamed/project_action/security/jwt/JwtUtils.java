package com.github.samba.mohamed.project_action.security.jwt;

import com.github.samba.mohamed.project_action.service.UserDetailsImpl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Date;
import jakarta.xml.bind.DatatypeConverter;
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private String jwtCookie="projectaction";
   // @Value("${jwt.secret}")
    Key key = MacProvider.generateKey();
    String jwtSecret = DatatypeConverter.printBase64Binary(key.getEncoded());
    @Autowired
    Environment env;

   // @Value("${jwt.expirationMs}")
    private int jwtExpirationMs = 86400000;

    public String generateJwtToken(UserDetailsImpl userPrincipal) throws UnsupportedEncodingException {


        SecretKeySpec secret_key = new SecretKeySpec(jwtSecret.getBytes("UTF-8"), "HmacSHA256");

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public AbstractMap.SimpleEntry<String,ResponseCookie> generateJwtCookie(UserDetailsImpl userPrincipal) throws UnsupportedEncodingException {
        String jwt = generateJwtToken(userPrincipal);
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
        return new AbstractMap.SimpleEntry<>(jwt,cookie);
    }

    public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
        return cookie;
    }

}
