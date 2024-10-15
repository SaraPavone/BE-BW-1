package sarapavo.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "parco_mezzi")
public class ParcoMezzi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "parcomezzi",cascade = CascadeType.ALL)
    private List<Mezzo> mezzi;


    public ParcoMezzi() {
    }

    public ParcoMezzi(List<Mezzo> mezzi) {
        this.mezzi = mezzi;
    }

    public long getId() {
        return id;
    }

    public List<Mezzo> getMezzi() {
        return mezzi;
    }

    public void setMezzi(List<Mezzo> mezzi) {
        this.mezzi = mezzi;
    }

    @Override
    public String toString() {
        return "ParcoMezzi{" +
                "id=" + id +
                ", mezzi=" + mezzi +
                '}';
    }
}
