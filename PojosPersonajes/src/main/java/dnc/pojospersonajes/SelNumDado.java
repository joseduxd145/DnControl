package dnc.pojospersonajes;

import java.io.Serializable;
import java.util.Objects;

public class SelNumDado implements Serializable {

    public static final long serialVersionUID = 6L;
    private Integer numDadoId;
    private Integer numDado;

    public SelNumDado() {
    }

    public SelNumDado(Integer numDadoId, Integer numDado) {
        this.numDadoId = numDadoId;
        this.numDado = numDado;
    }

    public SelNumDado(Integer numDadoId) {
        this.numDadoId = numDadoId;
    }

    public Integer getNumDado() {
        return numDado;
    }

    public void setNumDado(Integer numDado) {
        this.numDado = numDado;
    }

    public Integer getNumDadoId() {
        return numDadoId;
    }

    public void setNumDadoId(Integer numDadoId) {
        this.numDadoId = numDadoId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.numDado);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SelNumDado other = (SelNumDado) obj;
        return Objects.equals(this.numDado, other.numDado);
    }

    @Override
    public String toString() {
        return "SelNumDado{" + "numDadoId=" + numDadoId + ", numDado=" + numDado + '}';
    }
}
