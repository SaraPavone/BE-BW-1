package sarapavo.entities;

import jakarta.persistence.*;
import sarapavo.entities.enums.TipoMezzi;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mezzi")
@NamedQuery(
        name = "Mezzo.countValidatedTickets",
        query = "SELECT m.mezzo, COUNT(b.id) AS numBigliettiVidimati " +
                "FROM Mezzo m " +
                "LEFT JOIN m.biglietti b " +
                "WHERE b.data_vidimazione IS NOT NULL " +
                "GROUP BY m.mezzo"
)

@NamedQuery(
        name = "Mezzo.maintenancePeriods",
        query = "SELECT m.mezzo, p.data_inizio, p.data_fine, p.is_maintance " +
                "FROM Mezzo m " +
                "JOIN m.lista_periodi p " +
                "WHERE p.is_maintance = true " +
                "ORDER BY p.data_inizio"
)

@NamedQuery(name = "Mezzo.countValidatedTicketsInTimeRange",
        query = "SELECT m, COUNT(t) FROM Mezzo m JOIN m.validatedTickets t WHERE t.date BETWEEN :startDate AND :endDate GROUP BY m")
public class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Enumerated(EnumType.STRING)
    private TipoMezzi mezzo;

    private int capienza_massima;
    private boolean manutenzione;

    @OneToMany(mappedBy = "mezzo")
    private List<Tratta> tratte = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parco_mezzi_id")
    private ParcoMezzi parcomezzi;

    @OneToMany(mappedBy = "mezzo")
    private List<Biglietto> biglietti;

    @OneToMany(mappedBy = "mezzo")
    private List<Periodo> lista_periodi = new ArrayList<>();


    public Mezzo() {
    }

    public Mezzo(TipoMezzi mezzo, boolean manutenzione, List<Tratta> tratte, ParcoMezzi parcomezzi, List<Biglietto> biglietti) {
        this.mezzo = mezzo;
        this.capienza_massima = mezzo.equals(TipoMezzi.TRAM)? 40 : mezzo.equals(TipoMezzi.AUTOBUS)? 60 : 0;
        this.manutenzione = manutenzione;
        this.tratte = tratte;
        this.parcomezzi = parcomezzi;
        this.biglietti = biglietti;
    }


    public long getId() {
        return id;
    }

    public List<Biglietto> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(List<Biglietto> biglietti) {
        this.biglietti = biglietti;
    }

    public TipoMezzi getMezzo() {
        return mezzo;
    }

    public void setMezzo(TipoMezzi mezzo) {
        this.mezzo = mezzo;
    }

    public int getCapienza_massima() {
        return capienza_massima;
    }

    public void setCapienza_massima(int capienza_massima) {
        this.capienza_massima = capienza_massima;
    }

    public boolean isManutenzione() {
        return manutenzione;
    }

    public void setManutenzione(boolean manutenzione) {
        this.manutenzione = manutenzione;
    }

    public List<Tratta> getTratte() {
        return tratte;
    }

    public void setTratte(List<Tratta> tratte) {
        this.tratte = tratte;
    }

    public ParcoMezzi getParcomezzi() {
        return parcomezzi;
    }

    public void setParcomezzi(ParcoMezzi parcomezzi) {
        this.parcomezzi = parcomezzi;
    }

    public List<Periodo> getLista_periodi() {
        return lista_periodi;
    }

    public void setLista_periodi(List<Periodo> lista_periodi) {
        this.lista_periodi = lista_periodi;
    }

    public void addPeriodo(Periodo p){
        this.lista_periodi.add(p);
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", mezzo=" + mezzo +
                ", capienza_massima=" + capienza_massima +
                ", manutenzione=" + manutenzione +
                ", tratte=" + tratte +
                ", parcomezzi=" + parcomezzi +
                '}';
    }
}


