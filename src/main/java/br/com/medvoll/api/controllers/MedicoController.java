package br.com.medvoll.api.controllers;

import br.com.medvoll.api.models.*;
import br.com.medvoll.api.repositories.MedicoRepository;
import br.com.medvoll.api.services.MedicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDetalhes> saveMedico(@Valid @RequestBody MedicoDTO medico, UriComponentsBuilder uriComponentsBuilder){

        final var med = medicoService.save(new Medico(medico));

        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(med.getId()).toUri();
        return ResponseEntity.created(uri).body( new MedicoDetalhes(med));
    }

    @GetMapping
    public Page<DadosRetornoMedico> listar(Pageable pageable){
        return new PageImpl<>(
                medicoService.listar(pageable).stream()
                        .map(DadosRetornoMedico::new).toList());
    }

    @PutMapping
    @Transactional
    public MedicoDetalhes atualiza(@Valid @RequestBody MedicoAtualizacao medico){
        return new MedicoDetalhes(medicoService.atualiza(medico));
    }

    @DeleteMapping(value = "/{id}")
    public void atualiza(@PathVariable(value = "id") Long id){
         medicoService.deleta(id);
    }

    @GetMapping(value = "/ativos")
    public Page<DadosRetornoMedico> listarAtivos(Pageable pageable){
        return new PageImpl<>(
                medicoService.listarStatusATIVO(pageable).stream()
                        .map(DadosRetornoMedico::new).toList());
    }

    @GetMapping(value = "/{id}")
    public MedicoDetalhes getById(@PathVariable(value = "id") Long id){
        return  new MedicoDetalhes(medicoService.getByID(id));
    }
}
