package com.hussam.carsAuction.security.jwt;

import com.hussam.carsAuction.security.userService.UserDetailsImp;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class JWTUtilities {
    @Value("${hussam.app.secretKey}")
    private String secretKey;
    @Value("${hussam.app.jwtExpirationMs}")
    private int ExpirationMs;

    /**
     *
     * @param userPrincipal
     * @return
     */
    public String generateJwtToken(UserDetailsImp userPrincipal) {
        return generateTokenFromEmail(userPrincipal.getEmail());
    }

    /**
     * Create the token from the given email using secretKey key and expiration time in Ms
     * @param email
     * @return generated token
     */
    public String generateTokenFromEmail(String email) {

        return Jwts.builder().setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + ExpirationMs ))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();

    }

    /**
     * extract the email from the given token
     * @param token
     * @return email of the user
     */
    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * validate the token that comes from the header of the request
     * @param jwtToken
     * @return true if the token is valid
     */
    public boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
                System.out.println("Invalid JWT Token: {}" + e.getMessage());
        }
        return false;
    }
}
