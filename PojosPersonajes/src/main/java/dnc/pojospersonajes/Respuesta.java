package dnc.pojospersonajes;

import java.io.Serializable;

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
    public String toString() {
        return "Respuesta{" + "op=" + op + ", entidad=" + entidad + ", e=" + e + '}';
    }
}
