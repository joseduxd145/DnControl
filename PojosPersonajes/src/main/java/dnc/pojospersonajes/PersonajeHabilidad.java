package dnc.pojospersonajes;

import java.io.Serializable;
import java.util.Objects;

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
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.personajeId);
        hash = 53 * hash + Objects.hashCode(this.habilidadId);
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
        final PersonajeHabilidad other = (PersonajeHabilidad) obj;
        if (!Objects.equals(this.personajeId, other.personajeId)) {
            return false;
        }
        return Objects.equals(this.habilidadId, other.habilidadId);
    }

    @Override
    public String toString() {
        return "PersonajeHabilidad{" + "personajeId=" + personajeId + ", habilidadId=" + habilidadId + '}';
    }
}
