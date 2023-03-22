package com.webapp.InvoiceManagementApp.security;


import com.webapp.InvoiceManagementApp.model.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenUtil {

    private static final int EXPIRATION_SECONDS = 100 * 60;

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generate(Customer customer) {
        return Jwts.builder()
                .setClaims(Map.of("Customer_id", customer.getCustomerId()))
                .setSubject(customer.getUsername())
                .setIssuer("InvoiceManagementApp")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(EXPIRATION_SECONDS)))
                .signWith(key)
                .compact();
    }

    public boolean validate(String token) {
        return getUsername(token) != null && !isExpired(token);
    }

    public String getUsername(String token) {
        var claims = getClaims(token);
        return claims.getSubject();
    }

    public Long getCustomerId(String token) {
        var strippedToken = removeBearerFromToken(token);
        var claims = getClaims(strippedToken);
        var customerIdObj = claims.get("Customer_id");

        if (customerIdObj == null) {
            return null;
        }

        var customerIdDouble = (Double) customerIdObj;

        return customerIdDouble.longValue();
    }

    private boolean isExpired(String token) {
        var claims = getClaims(token);
        return claims.getExpiration().before(Date.from(Instant.now()));
    }


    private String removeBearerFromToken(String token) {
        return token.split(" ")[1];
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}