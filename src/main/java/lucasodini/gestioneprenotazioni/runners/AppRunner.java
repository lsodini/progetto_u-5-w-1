package lucasodini.gestioneprenotazioni.runners;

import lucasodini.gestioneprenotazioni.entities.*;
import lucasodini.gestioneprenotazioni.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppRunner implements CommandLineRunner {
    @Autowired
    private EdificioService edificioService;
    @Autowired
    private PostazioneService postazioneService;
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private UtenteService utenteService;

    @Override
    public void run(String... args) {

        Edificio edificio = new Edificio(null, "Edificio A", "Via Roma", "Milano");
        edificio = edificioService.salvaEdificio(edificio);

        Postazione postazione = new Postazione(null, "PST-001", "Postazione privata", TipoPostazione.PRIVATO, 1, edificio);
        postazione = postazioneService.salvaPostazione(postazione);

        Utente utente = new Utente(null, "jdoe", "John Doe", "jdoe@example.com");
        utente = utenteService.salvaUtente(utente);

        Prenotazione prenotazione = new Prenotazione(null, LocalDate.now(), postazione, utente);
        prenotazioneService.prenotaPostazione(prenotazione);

        System.out.println("Prenotazione effettuata con successo!");
    }
}

