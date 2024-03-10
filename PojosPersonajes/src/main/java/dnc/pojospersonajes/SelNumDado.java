package dnc.pojospersonajes;

import java.io.Serializable;

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
    public String toString() {
        return "SelNumDado{" + "numDadoId=" + numDadoId + ", numDado=" + numDado + '}';
    }
}
