package lucasodini.gestioneprenotazioni.services;

import lucasodini.gestioneprenotazioni.entities.Postazione;
import lucasodini.gestioneprenotazioni.entities.TipoPostazione;
import lucasodini.gestioneprenotazioni.repositories.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostazioneService {
    @Autowired
    private PostazioneRepository postazioneRepository;

    public List<Postazione> cercaPostazioni(TipoPostazione tipo, String citta) {
        return postazioneRepository.findByTipoAndEdificioCitta(tipo, citta);
    }
    public Postazione salvaPostazione(Postazione postazione) {
        return postazioneRepository.save(postazione);
    }
}

