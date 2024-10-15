package sarapavo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tessere")
public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numero_tessera;

    private LocalDate data_emissione;
    private LocalDate data_scadenza;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "abbonamento_id")
    private Abbonamento abbonamento;

    public Tessera() {
    }

    public Tessera(User user) {
        this.user = user;
        this.data_emissione = LocalDate.now();
        this.data_scadenza = data_emissione.plusYears(1L);
    }

    public long getNumero_tessera() {
        return numero_tessera;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getData_scadenza() {
        return data_scadenza;
    }

    public void setData_scadenza(LocalDate data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    public LocalDate getData_emissione() {
        return data_emissione;
    }

    public void setData_emissione(LocalDate data_emissione) {
        this.data_emissione = data_emissione;
    }

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "numero_tessera=" + numero_tessera +
                ", data_emissione=" + data_emissione +
                ", data_scadenza=" + data_scadenza +
                ", user=" + user +
                ", abbonamento=" + abbonamento +
                '}';
    }
}
