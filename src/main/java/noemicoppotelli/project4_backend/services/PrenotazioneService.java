package noemicoppotelli.project4_backend.services;

import lombok.RequiredArgsConstructor;
import noemicoppotelli.project4_backend.entities.Postazione;
import noemicoppotelli.project4_backend.entities.Prenotazione;
import noemicoppotelli.project4_backend.entities.Utente;
import noemicoppotelli.project4_backend.exceptions.ValidationException;
import noemicoppotelli.project4_backend.exceptions.NotFoundException;
import noemicoppotelli.project4_backend.repository.PrenotazioneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final PostazioneService postazioneService;
    private final UtenteService utenteService;

    @Transactional
    public Prenotazione prenota(Long utenteId, Long postazioneId, LocalDate data) {
        Utente utente = utenteService.trovaPerId(utenteId);
        Postazione postazione = postazioneService.trovaPerId(postazioneId);

        if (prenotazioneRepository.existsByPostazione_IdAndData(postazioneId, data)) {
            throw new ValidationException(
                    "La postazione " + postazione.getCodiceUnivoco() + " e' gia' occupata in data " + data);
        }
        if (prenotazioneRepository.existsByUtente_IdAndData(utenteId, data)) {
            throw new ValidationException(
                    "L'utente " + utente.getUsername() + " ha gia' una prenotazione in data " + data);
        }

        Prenotazione prenotazione = new Prenotazione(null, data, postazione, utente);
        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> trovaPerUtente(Long utenteId) {
        utenteService.trovaPerId(utenteId);
        return prenotazioneRepository.findByUtente_Id(utenteId);
    }

    public Prenotazione trovaPerId(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> NotFoundException.perId("Prenotazione", id));
    }
}
