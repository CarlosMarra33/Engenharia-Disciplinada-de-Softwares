//package com.venturaHR.ServiceImpl.security;
//
//import com.venturaHR.entity.Usuario;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//@Service
//public class SegurancaServiceImpl {
//
////    private static final long tempoExpiracao = 18000000;
//    private static final long tempoExpiracao = 1800000000;
//
//    private String key = "batata";
//
//    private Claims decodeToken(String Token){
//        return Jwts.parser()
//                .setSigningKey(key)
//                .parseClaimsJws(Token)
//                .getBody();
//    }
//
//    public String criacaoToken(Usuario usuario){
//
//        return Jwts.builder().setIssuedAt(new Date(System.currentTimeMillis()))
//                .setSubject(usuario.getEmail())
////                .setExpiration(new Date(System.currentTimeMillis() + tempoExpiracao))
//                .signWith(SignatureAlgorithm.HS256, key)
//                .compact();
//    }
//
//    public boolean validacao(String token) {
//        String tokenTratado = token.replace("Bearer ", "");
//        Claims claims = decodeToken(tokenTratado);
//        if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
//            return false;
//        }
//        return true;
//    }
//}
