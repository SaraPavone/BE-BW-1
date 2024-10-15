package sarapavo.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "biglietti")

public class Biglietto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate data_vidimazione;

    @ManyToOne
    @JoinColumn(name = "punto_emissione_id")
    private PuntoEmissione punto_emisssione;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    

}
