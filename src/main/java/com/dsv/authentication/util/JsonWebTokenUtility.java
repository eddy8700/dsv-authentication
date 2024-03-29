package com.dsv.authentication.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JsonWebTokenUtility implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonWebTokenUtility.class);

    private SignatureAlgorithm signatureAlgorithm;

    private Key secretKey;


    public String createJsonWebToken(final JWTTokenBuilder jwtTokenDTO) {
        LOGGER.info("Building the token from jwtTokenDTO");
        String token = Jwts.builder().setSubject("" + jwtTokenDTO.getUserName())
                .claim("email", jwtTokenDTO.getUserEmail()).claim("roles", jwtTokenDTO.getRoles())
                .claim("permissions", jwtTokenDTO.getPermissions())
                .setExpiration(new Date(jwtTokenDTO.getExpirationTimeInMillis()))
                .signWith(getSignatureAlgorithm(), getSecretKey()).compact();
        LOGGER.info("Token build with the expiration time", jwtTokenDTO.getExpirationTimeInMillis());
        return token;
    }

    private Key deserializeKey(String encodedKey) {
        LOGGER.info("Deserialize the key with the encoded key", encodedKey);
        final byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        final Key key = new SecretKeySpec(decodedKey, getSignatureAlgorithm().getJcaName());
        return key;
    }

    private Key getSecretKey() {
        return this.secretKey;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    @SuppressWarnings("unchecked")
    public JWTTokenBuilder parseAndValidate(final String token) {
        try {
            LOGGER.info("Parsing the token", token);
            final Claims claims = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
            final String userName = claims.getSubject();
            final String email = (String) claims.get("email");
            final List<String> roleNames = (List<String>) claims.get("roles");
            final List<String> permissionNames = (List<String>) claims.get("permissions");
            final Date expirationDate = claims.getExpiration();
            LOGGER.info("Token expiration time ", expirationDate.getTime());
            return new JWTTokenBuilder(userName, email, roleNames, permissionNames, expirationDate.getTime());
        } catch (JwtException ex) {
            System.out.println(ex);
        }
        return null;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        signatureAlgorithm = SignatureAlgorithm.HS512;
        // String encodedKey =
        // "L7A/6zARSkK1j7Vd5SDD9pSSqZlqF7mAhiOgRbgv9Smce6tf4cJnvKOjtKPxNNnWQj+2lQEScm3XIUjhW+YVZg==";
        String key = "aditya";
        secretKey = deserializeKey(key);

    }
}
