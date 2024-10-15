package sarapavo.entities;

import jakarta.persistence.Entity;

@Entity

public class DistributoreAutomatico extends PuntoEmissione {
    private boolean fuori_uso;

    public DistributoreAutomatico() {
    }

    public DistributoreAutomatico(String nome, boolean fuori_uso) {
        super(nome);
        this.fuori_uso = fuori_uso;
    }

    public boolean isFuori_uso() {
        return fuori_uso;
    }

    public void setFuori_uso(boolean fuori_uso) {
        this.fuori_uso = fuori_uso;
    }

    @Override
    public String toString() {
        return "DistributoreAutomatico{" +
                "fuori_uso=" + fuori_uso +
                "} " + super.toString();
    }
}
