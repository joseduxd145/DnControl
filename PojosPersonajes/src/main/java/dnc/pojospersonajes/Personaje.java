package dnc.pojospersonajes;

import java.io.Serializable;

public class Personaje implements Serializable {

    public static final long serialVersionUID = 4L;
    private Integer personajeId;
    private Usuario usuarioId;
    private String nombrePersonaje;
    private String apellido;
    private String transfondo;
    private Integer fuerza;
    private Integer destreza;
    private Integer constitucion;
    private Integer inteligencia;
    private Integer sabiduria;
    private Integer carisma;
    private String jugador;

    public Personaje() {
        this.personajeId = null;
        this.usuarioId = null;
        this.nombrePersonaje = null;
        this.apellido = null;
        this.transfondo = null;
        this.fuerza = null;
        this.destreza = null;
        this.constitucion = null;
        this.inteligencia = null;
        this.sabiduria = null;
        this.carisma = null;
        this.jugador = null;
    }

    public Personaje(Integer personajeId, Usuario usuarioId, String nombrePersonaje, String apellido, String transfondo, Integer fuerza, Integer destreza, Integer constitucion, Integer inteligencia, Integer sabiduria, Integer carisma, String jugador) {
        this.personajeId = personajeId;
        this.usuarioId = usuarioId;
        this.nombrePersonaje = nombrePersonaje;
        this.apellido = apellido;
        this.transfondo = transfondo;
        this.fuerza = fuerza;
        this.destreza = destreza;
        this.constitucion = constitucion;
        this.inteligencia = inteligencia;
        this.sabiduria = sabiduria;
        this.carisma = carisma;
        this.jugador = jugador;
    }

    public Personaje(Integer personajeId) {
        this.personajeId = personajeId;
        this.usuarioId = null;
        this.nombrePersonaje = null;
        this.apellido = null;
        this.transfondo = null;
        this.fuerza = null;
        this.destreza = null;
        this.constitucion = null;
        this.inteligencia = null;
        this.sabiduria = null;
        this.carisma = null;
        this.jugador = null;
    }

    public Personaje(Usuario usuarioId, String nombrePersonaje, String apellido, String transfondo, Integer fuerza, Integer destreza, Integer constitucion, Integer inteligencia, Integer sabiduria, Integer carisma, String jugador) {
        this.personajeId = null;
        this.usuarioId = usuarioId;
        this.nombrePersonaje = nombrePersonaje;
        this.apellido = apellido;
        this.transfondo = transfondo;
        this.fuerza = fuerza;
        this.destreza = destreza;
        this.constitucion = constitucion;
        this.inteligencia = inteligencia;
        this.sabiduria = sabiduria;
        this.carisma = carisma;
        this.jugador = jugador;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public Integer getPersonajeId() {
        return personajeId;
    }

    public void setPersonajeId(Integer personajeId) {
        this.personajeId = personajeId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombrePersonaje() {
        return nombrePersonaje;
    }

    public void setNombrePersonaje(String nombrePersonaje) {
        this.nombrePersonaje = nombrePersonaje;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTransfondo() {
        return transfondo;
    }

    public void setTransfondo(String transfondo) {
        this.transfondo = transfondo;
    }

    public Integer getFuerza() {
        return fuerza;
    }

    public void setFuerza(Integer fuerza) {
        this.fuerza = fuerza;
    }

    public Integer getDestreza() {
        return destreza;
    }

    public void setDestreza(Integer destreza) {
        this.destreza = destreza;
    }

    public Integer getConstitucion() {
        return constitucion;
    }

    public void setConstitucion(Integer constitucion) {
        this.constitucion = constitucion;
    }

    public Integer getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(Integer inteligencia) {
        this.inteligencia = inteligencia;
    }

    public Integer getSabiduria() {
        return sabiduria;
    }

    public void setSabiduria(Integer sabiduria) {
        this.sabiduria = sabiduria;
    }

    public Integer getCarisma() {
        return carisma;
    }

    public void setCarisma(Integer carisma) {
        this.carisma = carisma;
    }

    @Override
    public String toString() {
        return "Personaje{" + "personajeId=" + personajeId + ", usuarioId=" + usuarioId + ", nombrePersonaje=" + nombrePersonaje + ", apellido=" + apellido + ", transfondo=" + transfondo + ", fuerza=" + fuerza + ", destreza=" + destreza + ", constitucion=" + constitucion + ", inteligencia=" + inteligencia + ", sabiduria=" + sabiduria + ", carisma=" + carisma + ", jugador=" + jugador + '}';
    }
}
