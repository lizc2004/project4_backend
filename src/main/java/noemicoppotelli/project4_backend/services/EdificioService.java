package noemicoppotelli.project4_backend.services;

import lombok.RequiredArgsConstructor;
import noemicoppotelli.project4_backend.entities.Edificio;
import noemicoppotelli.project4_backend.exceptions.NotFoundException;
import noemicoppotelli.project4_backend.repository.EdificioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EdificioService {

    private final EdificioRepository edificioRepository;

    public Edificio salva(Edificio edificio) {
        return edificioRepository.save(edificio);
    }

    public List<Edificio> trovaTutti() {
        return edificioRepository.findAll();
    }

    public Edificio trovaPerId(Long id) {
        return edificioRepository.findById(id)
                .orElseThrow(() -> NotFoundException.perId("Edificio", id));
    }
}
