package noemicoppotelli.project4_backend.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import noemicoppotelli.project4_backend.enums.TipoPostazione;

@Entity
@Table(name = "postazione")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codice_univoco", nullable = false, unique = true)
    private String codiceUnivoco;

    @Column(nullable = false)
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPostazione tipo;

    @Column(name = "numero_massimo_occupanti", nullable = false)
    private Integer numeroMassimoOccupanti;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;
}
