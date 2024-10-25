package lucasodini.gestioneprenotazioni.services;

import lucasodini.gestioneprenotazioni.entities.Utente;
import lucasodini.gestioneprenotazioni.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente salvaUtente(Utente utente) {
        return utenteRepository.save(utente);
    }
}
