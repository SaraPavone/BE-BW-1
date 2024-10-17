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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    public Biglietto() {
    }

    public Biglietto(PuntoEmissione punto_emisssione, Mezzo mezzo) {
        this.punto_emisssione = punto_emisssione;
        this.mezzo = mezzo;
    }

    public LocalDate getData_vidimazione() {
        return data_vidimazione;
    }

    public void setData_vidimazione(LocalDate data_vidimazione) {
        this.data_vidimazione = data_vidimazione;
    }

    public PuntoEmissione getPunto_emisssione() {
        return punto_emisssione;
    }

    public void setPunto_emisssione(PuntoEmissione punto_emisssione) {
        this.punto_emisssione = punto_emisssione;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", data_vidimazione=" + data_vidimazione +
                ", punto_emisssione=" + punto_emisssione +
                ", mezzo=" + mezzo +
                '}';
    }
}
