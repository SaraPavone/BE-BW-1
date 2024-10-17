package sarapavo.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "tratte")
@NamedQuery(
        name = "Tratta.averageTravelTimeForSpecificRoute",
        query = "SELECT AVG(t.tempo_percorrenza_previsto) AS avgTempoPrevisto " +
                "FROM Tratta t " +
                "WHERE t.zona_partenza = :zonaPartenza AND t.capolinea = :capolinea"
)

public class Tratta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String zona_partenza;
    private String capolinea;
    private int tempo_percorrenza_effettivo;
    private int tempo_percorrenza_previsto;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo;


    public Tratta() {
    }

    public Tratta(Mezzo mezzo, int tempo_percorrenza_previsto, String capolinea, String zona_partenza) {
        this.mezzo = mezzo;
        this.tempo_percorrenza_previsto = tempo_percorrenza_previsto;
        this.capolinea = capolinea;
        this.zona_partenza = zona_partenza;
    }

    public long getId() {
        return id;
    }


    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public int getTempo_percorrenza_previsto() {
        return tempo_percorrenza_previsto;
    }

    public void setTempo_percorrenza_previsto(int tempo_percorrenza_previsto) {
        this.tempo_percorrenza_previsto = tempo_percorrenza_previsto;
    }

    public int getTempo_percorrenza_effettivo() {
        return tempo_percorrenza_effettivo;
    }

    public void setTempo_percorrenza_effettivo(int tempo_percorrenza_effettivo) {
        this.tempo_percorrenza_effettivo = tempo_percorrenza_effettivo;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public String getZona_partenza() {
        return zona_partenza;
    }

    public void setZona_partenza(String zona_partenza) {
        this.zona_partenza = zona_partenza;
    }


    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", zona_partenza='" + zona_partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempo_percorrenza_effettivo=" + tempo_percorrenza_effettivo +
                ", tempo_percorrenza_previsto=" + tempo_percorrenza_previsto +
                ", mezzo=" + mezzo +
                '}';
    }
}
