package dnc.pojospersonajes;

import java.io.Serializable;

/**
 *
 * @author Jose Ramon Bravo
 */
public class DncImagen implements Serializable {

    private String id;
    private byte[] datos;

    public DncImagen() {
        id = "NULL";
        datos = null;
    }

    public DncImagen(String id) {
        this.id = id;
    }

    public DncImagen(String id, byte[] datos) {
        this.id = id;
        this.datos = datos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }

    @Override
    public String toString() {
        return "DncImagen{" + "id=" + id + ", datos=" + datos + '}';
    }

}
