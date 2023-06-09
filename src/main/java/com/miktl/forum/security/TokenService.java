package com.miktl.forum.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.miktl.forum.domain.user.User;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    private Dotenv dotenv;
    private String  secret;
    public TokenService(Dotenv dotenv) {
        this.dotenv = dotenv;
        this.secret = dotenv.get("JWT_SECRET");
    }
    public String tokenGeneration(User user) {
        String token=null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            token = JWT.create()
                    .withIssuer("forum")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpireDate(5))
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            exception.printStackTrace();
        }
        return token;
    }

    public String getSubject(String token) {
        if(token==null){
            throw new RuntimeException("Token is Null");
        }
        DecodedJWT verifier=null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
             verifier = JWT.require(algorithm)
                    .withIssuer("forum")
                    .build().verify(token);
        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Invalid Verifier");
        }
        return verifier.getSubject();
    }

    private Instant generateExpireDate(int hours) {
        Instant now = Instant.now();
        Duration duration = Duration.ofHours(hours);
        return now.plus(duration);
    }
}
