package sarapavo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "periodo_manutenzione")
public class Periodo {
    @Id
    @GeneratedValue
    private long id;

    private LocalDate data_inizio;
    private LocalDate data_fine;
    private boolean is_maintance;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    public Periodo() {
    }

    public Periodo(Mezzo m, boolean is_maintance) {
        this.mezzo = m;
        this.data_inizio = LocalDate.now();
        if (m.getLista_periodi() == null) {
            m.setLista_periodi(new ArrayList<>());
            if (!m.getLista_periodi().isEmpty()) {
                Periodo ultimoPeriodo = m.getLista_periodi().get(m.getLista_periodi().size() - 1);
                ultimoPeriodo.setData_fine(this.data_inizio);
            }
        }
        m.getLista_periodi().add(this);
        this.is_maintance = is_maintance;
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
