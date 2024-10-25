package lucasodini.gestioneprenotazioni.services;

import lucasodini.gestioneprenotazioni.entities.Prenotazione;
import lucasodini.gestioneprenotazioni.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;


    public Prenotazione prenotaPostazione(Prenotazione prenotazione) {
        Long postazioneId = prenotazione.getPostazione().getId();
        Long utenteId = prenotazione.getUtente().getId();


        if (isPostazioneGiàPrenotata(postazioneId, prenotazione.getData())) {
            throw new IllegalArgumentException("La postazione è già prenotata per la data selezionata.");
        }


        if (isUtenteGiàPrenotato(utenteId, prenotazione.getData())) {
            throw new IllegalArgumentException("L'utente ha già una prenotazione per questa data.");
        }

        return prenotazioneRepository.save(prenotazione);
    }


    private boolean isPostazioneGiàPrenotata(Long postazioneId, LocalDate data) {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByPostazioneIdAndData(postazioneId, data);
        return !prenotazioni.isEmpty();
    }


    private boolean isUtenteGiàPrenotato(Long utenteId, LocalDate data) {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByUtenteIdAndData(utenteId, data);
        return !prenotazioni.isEmpty();
    }
}
