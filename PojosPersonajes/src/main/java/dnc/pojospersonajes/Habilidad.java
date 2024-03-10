package dnc.pojospersonajes;

import java.io.Serializable;

public class Habilidad implements Serializable {

    public static final long serialVersionUID = 2L;
    private Integer habilidadId;
    private SelNumDado numDadoId;
    private String nombreHabilidad;
    private String descripcion;
    private Integer cantidadDado;

    public Habilidad() {
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
    public String toString() {
        return "Habilidad{" + "habilidadId=" + habilidadId + ", numDadoId=" + numDadoId + ", nombreHabilidad=" + nombreHabilidad + ", descripcion=" + descripcion + ", cantidadDado=" + cantidadDado + '}';
    }

}
