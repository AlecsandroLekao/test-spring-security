package br.com.medvoll.api.repositories;

import br.com.medvoll.api.models.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long> {

    @Query("SELECT u FROM Medico u WHERE u.status is null ")
    Page<Medico> findStatusATIVO(Pageable pageable);
}
