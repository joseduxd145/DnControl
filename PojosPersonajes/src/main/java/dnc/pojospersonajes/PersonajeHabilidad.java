package dnc.pojospersonajes;

import java.io.Serializable;

public class PersonajeHabilidad implements Serializable {

    public static final long serialVersionUID = 5L;
    private Personaje personajeId;
    private Habilidad habilidadId;

    public PersonajeHabilidad() {
    }

    public PersonajeHabilidad(Personaje personajeId, Habilidad habilidadId) {
        this.personajeId = personajeId;
        this.habilidadId = habilidadId;
    }

    public Habilidad getHabilidadId() {
        return habilidadId;
    }

    public void setHabilidadId(Habilidad habilidadId) {
        this.habilidadId = habilidadId;
    }

    public Personaje getPersonajeId() {
        return personajeId;
    }

    public void setPersonajeId(Personaje personajeId) {
        this.personajeId = personajeId;
    }

    @Override
    public String toString() {
        return "PersonajeHabilidad{" + "personajeId=" + personajeId + ", habilidadId=" + habilidadId + '}';
    }
}
