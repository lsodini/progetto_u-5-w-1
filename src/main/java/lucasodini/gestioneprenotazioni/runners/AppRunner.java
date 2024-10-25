package lucasodini.gestioneprenotazioni.runners;

import lucasodini.gestioneprenotazioni.entities.Edificio;
import lucasodini.gestioneprenotazioni.entities.Postazione;
import lucasodini.gestioneprenotazioni.entities.Prenotazione;
import lucasodini.gestioneprenotazioni.entities.Utente;
import lucasodini.gestioneprenotazioni.entities.TipoPostazione;
import lucasodini.gestioneprenotazioni.repositories.EdificioRepository;
import lucasodini.gestioneprenotazioni.repositories.PostazioneRepository;
import lucasodini.gestioneprenotazioni.repositories.PrenotazioneRepository;
import lucasodini.gestioneprenotazioni.repositories.UtenteRepository;
import lucasodini.gestioneprenotazioni.services.PostazioneService;
import lucasodini.gestioneprenotazioni.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private EdificioRepository edificioRepository;

    @Override
    public void run(String... args) throws Exception {

        Edificio edificio = new Edificio(null,"Edificio A", "Via Roma 1", "Milano");
        edificioRepository.save(edificio);
        Postazione postazione1 = new Postazione(null,"P001", "Postazione Privata", TipoPostazione.PRIVATO, 1, edificio);
        Postazione postazione2 = new Postazione(null,"P002", "Postazione OpenSpace", TipoPostazione.OPENSPACE, 2, edificio);
        Postazione postazione3 = new Postazione(null,"P003", "Sala Riunioni", TipoPostazione.SALA_RIUNIONI, 10, edificio);

       postazioneRepository.save(postazione1);
        postazioneRepository.save(postazione2);
        postazioneRepository.save(postazione3);


        Utente utente = new Utente(null,"username1", "Nome Cognome", "email@example.com");
      utenteRepository.save(utente);


        try { //creo prenotazione
            Prenotazione prenotazione1 = new Prenotazione(null,LocalDate.now(),postazione1, utente);
            prenotazioneService.prenotaPostazione(prenotazione1);
            System.out.println("Prenotazione 1 avvenuta con successo per " + postazione1.getCodice());
        } catch (Exception e) {
            System.out.println("Errore nella prenotazione 1: " + e.getMessage());
        }


        try { //provo a riprenotare la stessa postazione di prima
            Prenotazione prenotazione2 = new Prenotazione(null, LocalDate.now(),postazione1, utente);
            prenotazioneService.prenotaPostazione(prenotazione2);
            System.out.println("Prenotazione 2 avvenuta con successo per " + postazione1.getCodice());
        } catch (Exception e) {
            System.out.println("Errore nella prenotazione 2: " + e.getMessage());
        }


        try { //prenoto òa stessa postazione di prima ma un altro giorno
            Prenotazione prenotazione3 = new Prenotazione(null,  LocalDate.now().plusDays(1),postazione1, utente);
            prenotazioneService.prenotaPostazione(prenotazione3);
            System.out.println("Prenotazione 3 avvenuta con successo per " + postazione1.getCodice());
        } catch (Exception e) {
            System.out.println("Errore nella prenotazione 3: " + e.getMessage());
        }


        try {//prenoto un altra postazione ma stesso giorno della prima prenotazione
            Prenotazione prenotazione4 = new Prenotazione(null, LocalDate.now(),postazione2, utente);
            prenotazioneService.prenotaPostazione(prenotazione4);
            System.out.println("Prenotazione 4 avvenuta con successo per " + postazione2.getCodice());
        } catch (Exception e) {
            System.out.println("Errore nella prenotazione 4: " + e.getMessage());
        }

        Utente utente2 = new Utente(null,"username2", "Nome Cognome 2", "email2@example.com");
        utenteRepository.save(utente2);
        try { //prenoto postazione2 con altro utente
            Prenotazione prenotazione5 = new Prenotazione(null, LocalDate.now(),postazione2, utente2);
            prenotazioneService.prenotaPostazione(prenotazione5);
            System.out.println("Prenotazione 5 avvenuta con successo per " + postazione2.getCodice());
        } catch (Exception e) {
            System.out.println("Errore nella prenotazione 5: " + e.getMessage());
        }

        List<Postazione> postazioniLibere = postazioneService.cercaPostazioni(TipoPostazione.PRIVATO, "Milano");
        System.out.println("Postazioni disponibili per PRIVATO a Milano: " + postazioniLibere.size());
    }
}
