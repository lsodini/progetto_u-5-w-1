package lucasodini.gestioneprenotazioni.repositories;

import lucasodini.gestioneprenotazioni.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByPostazioneIdAndData(Long postazioneId, LocalDate data);
    List<Prenotazione> findByUtenteIdAndData(Long utenteId, LocalDate data);
}
