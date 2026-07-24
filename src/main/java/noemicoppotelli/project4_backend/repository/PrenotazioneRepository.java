package noemicoppotelli.project4_backend.repository;

import noemicoppotelli.project4_backend.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    // per verificare se la postazione e' gia' occupata in quella data
    boolean existsByPostazione_IdAndData(Long postazioneId, LocalDate data);

    // per verificare se l'utente ha gia' una prenotazione in quella data
    boolean existsByUtente_IdAndData(Long utenteId, LocalDate data);

    List<Prenotazione> findByUtente_Id(Long utenteId);
}
