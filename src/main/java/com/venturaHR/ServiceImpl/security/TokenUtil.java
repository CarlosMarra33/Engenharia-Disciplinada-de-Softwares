package com.venturaHR.ServiceImpl.security;

import com.venturaHR.entity.Usuario;
import com.venturaHR.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";
    private static final long EXPIRATION = 12*60*60*1000;
    private static final String SECRET_KEY = "12345678910111211234567891011121";
    private static final String EMISSOR = "Ventura";

    public static String createToken(String email){
        Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return PREFIX + token;
    }

    private static boolean isExpirationValid(Date expiration){
        return expiration.after(new Date(System.currentTimeMillis()));
    }

    private static boolean isEmissorValid(String emissor){
        return emissor.equals(EMISSOR);
    }

    private static boolean isSubjectValid(String email){
        return email!= null && email.length() > 0;
    }

    public static Authentication validate(HttpServletRequest request){
        String token = request.getHeader(HEADER);
        token = token.replace(PREFIX, "");

        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .build().parseClaimsJws(token);
        String email = jws.getBody().getSubject();
        String issuer = jws.getBody().getIssuer();
        Date expiration = jws.getBody().getExpiration();

        if (isSubjectValid(email) && isEmissorValid(issuer) && isExpirationValid(expiration)){
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        }

        throw new UnauthorizedException("invalid Token");
    }
}
