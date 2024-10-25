package lucasodini.gestioneprenotazioni.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "postazioni")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codice;
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoPostazione tipo;

    private Integer numeroMassimoOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;
}
