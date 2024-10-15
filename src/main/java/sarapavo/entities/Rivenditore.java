package sarapavo.entities;


import jakarta.persistence.Entity;

@Entity
public class Rivenditore extends PuntoEmissione{

    public Rivenditore() {
    }

    public Rivenditore(String nome_punto_emissione) {
        super(nome_punto_emissione);
    }

    @Override
    public String toString() {
        return "Rivenditore{} " + super.toString();
    }
}
