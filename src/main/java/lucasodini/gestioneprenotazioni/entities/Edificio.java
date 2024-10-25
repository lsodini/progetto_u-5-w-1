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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String indirizzo;
    private String città;
}
