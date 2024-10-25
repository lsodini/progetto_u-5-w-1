package lucasodini.gestioneprenotazioni.services;

import lucasodini.gestioneprenotazioni.entities.Prenotazione;
import lucasodini.gestioneprenotazioni.entities.Postazione;
import lucasodini.gestioneprenotazioni.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private PostazioneService postazioneService;

    public Prenotazione prenotaPostazione(Prenotazione prenotazione) {
        Long postazioneId = prenotazione.getPostazione().getId();
        Long utenteId = prenotazione.getUtente().getId();

        if (isUtenteGiàPrenotato(utenteId, prenotazione.getData())) {
            throw new IllegalArgumentException("L'utente ha già una prenotazione per questa data.");
        }

        if (!isPostazioneDisponibile(postazioneId, prenotazione.getData())) {
            throw new IllegalArgumentException("Non è possibile prenotare la postazione, il numero massimo di occupanti è già stato raggiunto.");
        }

        return prenotazioneRepository.save(prenotazione);
    }

    private boolean isUtenteGiàPrenotato(Long utenteId, LocalDate data) {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByUtenteIdAndData(utenteId, data);
        return !prenotazioni.isEmpty();
    }

    private boolean isPostazioneDisponibile(Long postazioneId, LocalDate data) {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findByPostazioneIdAndData(postazioneId, data);
        Postazione postazione = postazioneService.findById(postazioneId);
        int numeroMaxOccupanti = postazione.getNumeroMassimoOccupanti();

        return prenotazioni.size() <= numeroMaxOccupanti;
    }
}
