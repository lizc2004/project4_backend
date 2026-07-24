package noemicoppotelli.project4_backend.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
        name = "prenotazione",
        uniqueConstraints = {
                // una postazione non puo' avere due prenotazioni nello stesso giorno
                @UniqueConstraint(name = "uk_postazione_data", columnNames = {"postazione_id", "data"}),
                // un utente non puo' prenotare piu' di una postazione nello stesso giorno
                @UniqueConstraint(name = "uk_utente_data", columnNames = {"utente_id", "data"})
        }
)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "postazione_id", nullable = false)
    private Postazione postazione;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;
}
