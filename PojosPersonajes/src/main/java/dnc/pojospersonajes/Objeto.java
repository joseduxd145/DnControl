package dnc.pojospersonajes;

import java.io.Serializable;

public class Objeto implements Serializable {

    public static final long serialVersionUID = 3L;
    private Integer objetoId;
    private Personaje personajeId;
    private String nombreObjeto;
    private String descripcion;
    private Integer valor;

    public Objeto() {
    }

    public Objeto(Integer objetoId, Personaje personajeId, String nombreObjeto, String descripcion, Integer valor) {
        this.objetoId = objetoId;
        this.personajeId = personajeId;
        this.nombreObjeto = nombreObjeto;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public Objeto(Integer objetoId) {
        this.objetoId = objetoId;
    }

    public Objeto(Personaje personajeId, String nombreObjeto, String descripcion, Integer valor) {
        this.personajeId = personajeId;
        this.nombreObjeto = nombreObjeto;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getObjetoId() {
        return objetoId;
    }

    public void setObjetoId(Integer objetoId) {
        this.objetoId = objetoId;
    }

    public Personaje getPersonajeId() {
        return personajeId;
    }

    public void setPersonajeId(Personaje personajeId) {
        this.personajeId = personajeId;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Objeto{" + "objetoId=" + objetoId + ", personajeId=" + personajeId + ", nombreObjeto=" + nombreObjeto + ", descripcion=" + descripcion + ", valor=" + valor + '}';
    }
}
