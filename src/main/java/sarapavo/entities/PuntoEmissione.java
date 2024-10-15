package sarapavo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "punti_emissione")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_punto_emissione")


public abstract class PuntoEmissione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome_punto_emissione;

    @OneToMany(mappedBy = "punto_emissione")
    private List<Abbonamento> abbonamenti= new ArrayList<>();

    @OneToMany(mappedBy = "punto_emisssione")
    private List<Biglietto> biglietti;

    public PuntoEmissione() {
    }

    public PuntoEmissione(String nome_punto_emissione) {
        this.nome_punto_emissione = nome_punto_emissione;
    }

    public long getId() {
        return id;
    }

    public String getNome_punto_emissione() {
        return nome_punto_emissione;
    }

    public void setNome_punto_emissione(String nome_punto_emissione) {
        this.nome_punto_emissione = nome_punto_emissione;
    }

    @Override
    public String toString() {
        return "PuntoEmissione{" +
                "id=" + id +
                ", nome_punto_emissione='" + nome_punto_emissione + '\'' +
                '}';
    }
}
