package dnc.pojospersonajes;

import java.io.Serializable;
import java.util.Objects;

public class Peticion implements Serializable {

    public static final long serialVersionUID = 8L;
    private Operaciones op;
    private Integer arg1;
    private Integer arg2;
    private Object entidad;
    private Usuario usuario;

    public Peticion() {
    }

    public Peticion(Operaciones op, Integer arg1, Object entidad, Usuario usuario) {
        this.op = op;
        this.arg1 = arg1;
        this.entidad = entidad;
        this.usuario = usuario;
    }

    public Peticion(Operaciones op, Integer arg1, Integer arg2, Object entidad, Usuario usuario) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.entidad = entidad;
        this.usuario = usuario;
    }

    public Operaciones getOp() {
        return op;
    }

    public void setOp(Operaciones op) {
        this.op = op;
    }

    public Integer getArg1() {
        return arg1;
    }

    public void setArg1(Integer arg1) {
        this.arg1 = arg1;
    }

    public Integer getArg2() {
        return arg2;
    }

    public void setArg2(Integer arg2) {
        this.arg2 = arg2;
    }

    public Object getEntidad() {
        return entidad;
    }

    public void setEntidad(Object entidad) {
        this.entidad = entidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.op);
        hash = 47 * hash + Objects.hashCode(this.arg1);
        hash = 47 * hash + Objects.hashCode(this.arg2);
        hash = 47 * hash + Objects.hashCode(this.entidad);
        hash = 47 * hash + Objects.hashCode(this.usuario);
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
        final Peticion other = (Peticion) obj;
        if (this.op != other.op) {
            return false;
        }
        if (!Objects.equals(this.arg1, other.arg1)) {
            return false;
        }
        if (!Objects.equals(this.arg2, other.arg2)) {
            return false;
        }
        if (!Objects.equals(this.entidad, other.entidad)) {
            return false;
        }
        return Objects.equals(this.usuario, other.usuario);
    }

    @Override
    public String toString() {
        return "Peticion{" + "op=" + op + ", arg1=" + arg1 + ", arg2=" + arg2 + ", entidad=" + entidad + ", usuario=" + usuario + '}';
    }
}
