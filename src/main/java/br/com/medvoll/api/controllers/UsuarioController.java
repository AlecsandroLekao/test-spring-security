package br.com.medvoll.api.controllers;

import br.com.medvoll.api.models.DadosLogin;
import br.com.medvoll.api.models.DadosTokenJWT;
import br.com.medvoll.api.models.Usuario;
import br.com.medvoll.api.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {


    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuaLogin(@RequestBody DadosLogin dadosLogin){
        var token = new UsernamePasswordAuthenticationToken(dadosLogin.login(),dadosLogin.senha());

        var auth = manager.authenticate(token);
        return ResponseEntity.ok().body(new DadosTokenJWT(tokenService.createToken((Usuario) auth.getPrincipal())));
    }

}
