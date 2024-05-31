package br.com.medvoll.api.security;

import br.com.medvoll.api.models.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    public static final String API_VOLL_MED = "API Voll.med";
    @Value("${api.security.token.secret}")


private String chave;

    public String createToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(chave);
            return JWT.create()
                    .withIssuer(API_VOLL_MED)
                    .withSubject(usuario.getLogin())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
           throw new RuntimeException("Erro ao gerar token");
        }
    }

    public String validaToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(chave);
            return JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer(API_VOLL_MED)
                    // reusable verifier instance
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token invalido ou expirado");
        }

    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}



