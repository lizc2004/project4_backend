package noemicoppotelli.project4_backend.runners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noemicoppotelli.project4_backend.entities.Edificio;
import noemicoppotelli.project4_backend.entities.Postazione;
import noemicoppotelli.project4_backend.entities.Utente;
import noemicoppotelli.project4_backend.enums.TipoPostazione;
import noemicoppotelli.project4_backend.services.EdificioService;
import noemicoppotelli.project4_backend.services.PostazioneService;
import noemicoppotelli.project4_backend.services.PrenotazioneService;
import noemicoppotelli.project4_backend.services.UtenteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final EdificioService edificioService;
    private final PostazioneService postazioneService;
    private final UtenteService utenteService;
    private final PrenotazioneService prenotazioneService;

    @Override
    public void run(String... args) {
        if (!edificioService.trovaTutti().isEmpty()) {
            log.info("Dati gia' presenti, salto il popolamento iniziale");
            return;
        }

        Edificio sedeMilano = edificioService.salva(
                new Edificio(null, "Sede Milano", "Via Torino 10", "Milano", new java.util.ArrayList<>()));
        Edificio sedeRoma = edificioService.salva(
                new Edificio(null, "Sede Roma", "Via Appia 25", "Roma", new java.util.ArrayList<>()));

        Postazione p1 = postazioneService.salva(new Postazione(null, "MI-P-01", "Postazione privata open space nord",
                TipoPostazione.PRIVATO, 1, sedeMilano));
        Postazione p2 = postazioneService.salva(new Postazione(null, "MI-OS-01", "Open space piano 2",
                TipoPostazione.OPENSPACE, 20, sedeMilano));
        Postazione p3 = postazioneService.salva(new Postazione(null, "RM-SR-01", "Sala riunioni Colosseo",
                TipoPostazione.SALA_RIUNIONI, 8, sedeRoma));

        Utente utente1 = utenteService.salva(new Utente(null, "mrossi", "Mario Rossi", "mario.rossi@example.com"));
        Utente utente2 = utenteService.salva(new Utente(null, "gverdi", "Giulia Verdi", "giulia.verdi@example.com"));

        prenotazioneService.prenota(utente1.getId(), p1.getId(), LocalDate.now().plusDays(1));
        prenotazioneService.prenota(utente2.getId(), p3.getId(), LocalDate.now().plusDays(2));

        log.info("Postazioni trovate a Milano di tipo OPENSPACE: {}",
                postazioneService.cerca(TipoPostazione.OPENSPACE, "Milano").size());
        log.info("Dati di esempio caricati correttamente (postazione {} inserita ma non usata nelle prenotazioni)",
                p2.getCodiceUnivoco());
    }
}
