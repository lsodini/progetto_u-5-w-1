package lucasodini.gestioneprenotazioni.services;

import lucasodini.gestioneprenotazioni.entities.Postazione;
import lucasodini.gestioneprenotazioni.entities.Prenotazione;
import lucasodini.gestioneprenotazioni.entities.TipoPostazione;
import lucasodini.gestioneprenotazioni.repositories.PostazioneRepository;
import lucasodini.gestioneprenotazioni.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostazioneService {
    @Autowired
    private PostazioneRepository postazioneRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public List<Postazione> cercaPostazioni(TipoPostazione tipo, String citta) {
        return postazioneRepository.findByTipoAndEdificioCitta(tipo, citta);
    }

    public Postazione salvaPostazione(Postazione postazione) {
        return postazioneRepository.save(postazione);
    }

    public Postazione findById(Long id) {
        return postazioneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Postazione non trovata con ID: " + id));
    }


    public boolean isPostazioneLibera(Postazione postazione, LocalDate data) {

        List<Prenotazione> prenotazioni = prenotazioneRepository.findByPostazioneIdAndData(postazione.getId(), data);
        return prenotazioni.isEmpty();
    }
}
