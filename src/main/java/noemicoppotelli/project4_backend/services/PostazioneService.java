package noemicoppotelli.project4_backend.services;

import lombok.RequiredArgsConstructor;
import noemicoppotelli.project4_backend.entities.Postazione;
import noemicoppotelli.project4_backend.enums.TipoPostazione;
import noemicoppotelli.project4_backend.exceptions.ValidationException;
import noemicoppotelli.project4_backend.exceptions.NotFoundException;
import noemicoppotelli.project4_backend.repository.PostazioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostazioneService {

    private final PostazioneRepository postazioneRepository;

    public Postazione salva(Postazione postazione) {
        if (postazioneRepository.existsByCodiceUnivoco(postazione.getCodiceUnivoco())) {
            throw new ValidationException("Esiste gia' una postazione con codice " + postazione.getCodiceUnivoco());
        }
        return postazioneRepository.save(postazione);
    }

    public List<Postazione> trovaTutti() {
        return postazioneRepository.findAll();
    }

    public Postazione trovaPerId(Long id) {
        return postazioneRepository.findById(id)
                .orElseThrow(() -> NotFoundException.perId("Postazione", id));
    }

    // ricerca postazioni per tipo e citta' dell'edificio
    public List<Postazione> cerca(TipoPostazione tipo, String citta) {
        if (tipo != null && citta != null) {
            return postazioneRepository.findByTipoAndEdificio_Citta(tipo, citta);
        }
        if (tipo != null) {
            return postazioneRepository.findByTipo(tipo);
        }
        if (citta != null) {
            return postazioneRepository.findByEdificio_Citta(citta);
        }
        return postazioneRepository.findAll();
    }
}
