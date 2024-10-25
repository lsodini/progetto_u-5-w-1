package lucasodini.gestioneprenotazioni.repositories;

import lucasodini.gestioneprenotazioni.entities.Postazione;
import lucasodini.gestioneprenotazioni.entities.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
    List<Postazione> findByTipoAndEdificioCitta(TipoPostazione tipo, String citta);
}
