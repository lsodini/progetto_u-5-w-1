package lucasodini.gestioneprenotazioni.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "edifici")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Edificio {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String indirizzo;
    private String citta;
}
