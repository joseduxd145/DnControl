package dnc.pojospersonajes;

import java.io.Serializable;
import java.util.Objects;

public class Habilidad implements Serializable {

    public static final long serialVersionUID = 2L;
    private Integer habilidadId;
    private SelNumDado numDadoId;
    private String nombreHabilidad;
    private String descripcion;
    private Integer cantidadDado;

    public Habilidad() {
        this.cantidadDado = 0;
        this.numDadoId = new SelNumDado(null, 0);
    }

    public Habilidad(Integer habilidadId, SelNumDado numDadoId, String nombreHabilidad, String descripcion, Integer cantidadDado) {
        this.habilidadId = habilidadId;
        this.numDadoId = numDadoId;
        this.nombreHabilidad = nombreHabilidad;
        this.descripcion = descripcion;
        this.cantidadDado = cantidadDado;
    }

    public Habilidad(Integer habilidadId) {
        this.habilidadId = habilidadId;
    }

    public Habilidad(SelNumDado numDadoId, String nombreHabilidad, String descripcion, Integer cantidadDado) {
        this.numDadoId = numDadoId;
        this.nombreHabilidad = nombreHabilidad;
        this.descripcion = descripcion;
        this.cantidadDado = cantidadDado;
    }

    public Integer getCantidadDado() {
        return cantidadDado;
    }

    public void setCantidadDado(Integer cantidadDado) {
        this.cantidadDado = cantidadDado;
    }

    public Integer getHabilidadId() {
        return habilidadId;
    }

    public void setHabilidadId(Integer habilidadId) {
        this.habilidadId = habilidadId;
    }

    public SelNumDado getNumDadoId() {
        return numDadoId;
    }

    public void setNumDadoId(SelNumDado numDadoId) {
        this.numDadoId = numDadoId;
    }

    public String getNombreHabilidad() {
        return nombreHabilidad;
    }

    public void setNombreHabilidad(String nombreHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.numDadoId);
        hash = 41 * hash + Objects.hashCode(this.nombreHabilidad);
        hash = 41 * hash + Objects.hashCode(this.descripcion);
        hash = 41 * hash + Objects.hashCode(this.cantidadDado);
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
        final Habilidad other = (Habilidad) obj;
        if (!Objects.equals(this.nombreHabilidad, other.nombreHabilidad)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.numDadoId, other.numDadoId)) {
            return false;
        }
        return Objects.equals(this.cantidadDado, other.cantidadDado);
    }

    @Override
    public String toString() {
        return "Habilidad{" + "habilidadId=" + habilidadId + ", numDadoId=" + numDadoId + ", nombreHabilidad=" + nombreHabilidad + ", descripcion=" + descripcion + ", cantidadDado=" + cantidadDado + '}';
    }

}
