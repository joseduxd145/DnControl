package dnc.pojospersonajes;

import java.io.Serializable;

public class Peticion implements Serializable {

    public static final long serialVersionUID = 8L;
    private Operaciones op;
    private Integer arg1;
    private Integer arg2;
    private Object entidad;

    public Peticion() {
    }

    public Peticion(Operaciones op, Integer arg1, Object entidad) {
        this.op = op;
        this.arg1 = arg1;
        this.entidad = entidad;
    }

    public Peticion(Operaciones op, Integer arg1, Integer arg2, Object entidad) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.entidad = entidad;
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

    @Override
    public String toString() {
        return "Peticion{" + "op=" + op + ", arg1=" + arg1 + ", arg2=" + arg2 + ", entidad=" + entidad + '}';
    }
    
    
}
