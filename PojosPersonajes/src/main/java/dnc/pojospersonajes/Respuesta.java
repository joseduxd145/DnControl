package dnc.pojospersonajes;

import java.io.Serializable;
import java.util.Objects;

public class Respuesta implements Serializable {

    public static final long serialVersionUID = 8L;
    private Operaciones op;
    private Object entidad;
    private ExcepcionPersonajes e;

    public Respuesta() {
    }

    public Respuesta(Operaciones op, Object entidad) {
        this.op = op;
        this.entidad = entidad;
    }

    public Respuesta(ExcepcionPersonajes e) {
        this.e = e;
    }

    public Respuesta(Operaciones op, Object entidad, ExcepcionPersonajes e) {
        this.op = op;
        this.entidad = entidad;
        this.e = e;
    }

    public Operaciones getOp() {
        return op;
    }

    public void setOp(Operaciones op) {
        this.op = op;
    }

    public Object getEntidad() {
        return entidad;
    }

    public void setEntidad(Object entidad) {
        this.entidad = entidad;
    }

    public ExcepcionPersonajes getE() {
        return e;
    }

    public void setE(ExcepcionPersonajes e) {
        this.e = e;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.op);
        hash = 43 * hash + Objects.hashCode(this.entidad);
        hash = 43 * hash + Objects.hashCode(this.e);
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
        final Respuesta other = (Respuesta) obj;
        if (this.op != other.op) {
            return false;
        }
        if (!Objects.equals(this.entidad, other.entidad)) {
            return false;
        }
        return Objects.equals(this.e, other.e);
    }

    @Override
    public String toString() {
        return "Respuesta{" + "op=" + op + ", entidad=" + entidad + ", e=" + e + '}';
    }
}
