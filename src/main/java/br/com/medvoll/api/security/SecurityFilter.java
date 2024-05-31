package br.com.medvoll.api.security;

import br.com.medvoll.api.repositories.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Value("${api.security.token.secret}")
    private String chave;

    @Autowired
    private  TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJwt = recuperaToken(request);

        if (tokenJwt !=null){
           var subject = tokenService.validaToken(tokenJwt);
           var usuario = repository.findByLogin(subject);

           var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
           SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request,response);
    }

    private String recuperaToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");

        if (header != null){
            return  header.replace("Bearer ","");
        }

        return null;
    }
}
