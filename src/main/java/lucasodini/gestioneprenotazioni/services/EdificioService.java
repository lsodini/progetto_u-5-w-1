package lucasodini.gestioneprenotazioni.services;

import lucasodini.gestioneprenotazioni.entities.Edificio;
import lucasodini.gestioneprenotazioni.repositories.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;

    public Edificio salvaEdificio(Edificio edificio) {
        return edificioRepository.save(edificio);
    }
}

