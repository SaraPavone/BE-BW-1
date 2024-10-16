package sarapavo.entities;

import jakarta.persistence.*;
import sarapavo.entities.enums.TipiAbbonamento;

import java.time.LocalDate;

@NamedQuery(
        name = "countAbbonamentiP",
        query = "Select a.punto_emissione, count(a.id) from Abbonamento a group by a.punto_emissione"
)
@Entity
@Table(name = "abbonamenti")
public class Abbonamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private TipiAbbonamento tipo;
    private LocalDate data_emissione;
    private LocalDate data_scadenza;

    @ManyToOne
    @JoinColumn(name = "punto_emissione_id")
    private PuntoEmissione punto_emissione;

    @OneToOne(mappedBy = "abbonamento")
    private Tessera tessera;

    public Abbonamento() {
    }

    public Abbonamento(Tessera tessera, TipiAbbonamento tipo, PuntoEmissione punto_emissione) {
        this.tessera = tessera;
        this.tipo = tipo;
        this.punto_emissione = punto_emissione;
        this.data_emissione = LocalDate.now();
        switch (tipo) {
            case SETTIMANALE -> this.data_scadenza = data_emissione.plusWeeks(1L);
            case MENSILE -> this.data_scadenza = data_emissione.plusMonths(1L);
            case ANNUALE -> this.data_scadenza = data_emissione.plusYears(1L);
            default -> this.data_scadenza = data_emissione;
        }
    }

    public long getId() {
        return id;
    }

    public TipiAbbonamento getTipo() {
        return tipo;
    }

    public void setTipo(TipiAbbonamento tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData_emissione() {
        return data_emissione;
    }

    public void setData_emissione(LocalDate data_emissione) {
        this.data_emissione = data_emissione;
    }

    public LocalDate getData_scadenza() {
        return data_scadenza;
    }

    public void setData_scadenza(LocalDate data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public PuntoEmissione getPunto_emissione() {
        return punto_emissione;
    }

    public void setPunto_emissione(PuntoEmissione punto_emissione) {
        this.punto_emissione = punto_emissione;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "punto_emissione=" + punto_emissione +
                ", data_scadenza=" + data_scadenza +
                ", data_emissione=" + data_emissione +
                ", tipo=" + tipo +
                ", id=" + id +
                '}';
    }
}
