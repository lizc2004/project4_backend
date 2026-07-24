package noemicoppotelli.project4_backend.services;

import lombok.RequiredArgsConstructor;
import noemicoppotelli.project4_backend.entities.Utente;
import noemicoppotelli.project4_backend.exceptions.ValidationException;
import noemicoppotelli.project4_backend.exceptions.NotFoundException;
import noemicoppotelli.project4_backend.repository.UtenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteService {

    private final UtenteRepository utenteRepository;

    public Utente salva(Utente utente) {
        utenteRepository.findByUsername(utente.getUsername())
                .ifPresent(u -> {
                    throw new ValidationException("Esiste gia' un utente con username " + utente.getUsername());
                });
        return utenteRepository.save(utente);
    }

    public List<Utente> trovaTutti() {
        return utenteRepository.findAll();
    }

    public Utente trovaPerId(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> NotFoundException.perId("Utente", id));
    }

    public Utente trovaPerUsername(String username) {
        return utenteRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Utente con username " + username + " non trovato"));
    }
}
