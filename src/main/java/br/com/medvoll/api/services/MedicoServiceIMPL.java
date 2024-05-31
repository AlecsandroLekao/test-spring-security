package br.com.medvoll.api.services;

import br.com.medvoll.api.models.Medico;
import br.com.medvoll.api.models.MedicoAtualizacao;
import br.com.medvoll.api.models.Status;
import br.com.medvoll.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MedicoServiceIMPL implements MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Override
    public Medico save(Medico medico) {
        return repository.save(medico);
    }

    @Override
    public Medico getByID(Long id) {
       return repository.findById(id).orElseThrow(() -> new RuntimeException("id não localizada"));
    }

    @Override
    public Page<Medico> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Medico atualiza(MedicoAtualizacao medico) {
     var med = repository.getReferenceById(medico.id());
     med.atualizaInformacoes(medico);
     return repository.save(med);
    }

    @Override
    public void deleta(Long id) {
        var med = repository.getReferenceById(id);
        med.setStatus(Status.INATIVO);
        repository.save(med);
    }

    @Override
    public Page<Medico> listarStatusATIVO(Pageable pageable) {
        return repository.findStatusATIVO(pageable);
    }
}
