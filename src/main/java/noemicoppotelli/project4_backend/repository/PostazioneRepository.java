package noemicoppotelli.project4_backend.repository;

import noemicoppotelli.project4_backend.entities.Postazione;
import noemicoppotelli.project4_backend.enums.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

    // ricerca postazioni per tipo e citta' dell'edificio
    List<Postazione> findByTipoAndEdificio_Citta(TipoPostazione tipo, String citta);

    List<Postazione> findByTipo(TipoPostazione tipo);

    List<Postazione> findByEdificio_Citta(String citta);

    boolean existsByCodiceUnivoco(String codiceUnivoco);
}
