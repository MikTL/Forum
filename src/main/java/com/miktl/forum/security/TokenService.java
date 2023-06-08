package com.miktl.forum.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.miktl.forum.domain.user.User;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    private Dotenv dotenv;

    public TokenService(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    public String tokenGeneration(User user) {
        String token=null;
        String secret = dotenv.get("JWT_SECRET");
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withIssuer("forum")
                    .withSubject(user.getName())
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

    private Instant generateExpireDate(int hours) {
        Instant ahora = Instant.now();
        Duration duration = Duration.ofHours(hours);
        return ahora.plus(duration);
    }
}
