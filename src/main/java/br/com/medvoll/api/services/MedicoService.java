package br.com.medvoll.api.services;

import br.com.medvoll.api.models.DadosRetornoMedico;
import br.com.medvoll.api.models.Medico;
import br.com.medvoll.api.models.MedicoAtualizacao;
import br.com.medvoll.api.models.MedicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface MedicoService {

    Medico save(Medico medico);
    Medico getByID(Long id);
    Page<Medico> listar(Pageable pageable);
    Medico atualiza(MedicoAtualizacao medico);
    void deleta(Long id);

    Page<Medico> listarStatusATIVO(Pageable pageable);

}
