package sarapavo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "periodo_manutenzione")
public class Periodo {
    @Id
    @GeneratedValue
    private long id;

    private LocalDate data_inizio = LocalDate.now();
    private LocalDate data_fine = null;
    private boolean is_maintance;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    public Periodo() {
    }

    public Periodo(Mezzo m){
        this.mezzo = m;
        setIs_maintance(!m.getLista_periodi().getLast().isIs_maintance());
        m.getLista_periodi().getLast().setData_fine(this.data_inizio);
    }

    public long getId() {
        return id;
    }

    public LocalDate getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(LocalDate data_inizio) {
        this.data_inizio = data_inizio;
    }

    public LocalDate getData_fine() {
        return data_fine;
    }

    public void setData_fine(LocalDate data_fine) {
        this.data_fine = data_fine;
    }

    public boolean isIs_maintance() {
        return is_maintance;
    }

    public void setIs_maintance(boolean is_maintance) {
        this.is_maintance = is_maintance;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    @Override
    public String toString() {
        return "Periodo{" +
                "id=" + id +
                ", data_inizio=" + data_inizio +
                ", data_fine=" + data_fine +
                ", is_maintance=" + is_maintance +
                ", mezzo=" + mezzo +
                '}';
    }
}
