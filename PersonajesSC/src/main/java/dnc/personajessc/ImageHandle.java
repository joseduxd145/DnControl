package dnc.personajessc;

import dnc.pojospersonajes.DncImagen;
import dnc.pojospersonajes.ExcepcionPersonajes;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Jose Ramon Bravo
 */
public class ImageHandle {

    /**
     * Directorio en el que se almacena, busca y elimina las imagenes
     */
    private String imagenDir;

    public ImageHandle(String imagenDir) {
        this.imagenDir = imagenDir;
    }

    public String getImagenDir() {
        return imagenDir;
    }

    public void setImagenDir(String imagenDir) {
        this.imagenDir = imagenDir;
    }

    public synchronized DncImagen leerImagen(String id) throws ExcepcionPersonajes {
        DncImagen img = new DncImagen(id);
        File f = new File(imagenDir, id);

        try {
            img.setDatos(FileUtils.readFileToByteArray(f));

        }
        catch (IOException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes("Error general del sistema. Contacte con el administrador", ex.getMessage(), null, null);
            throw e;
        }

        return img;
    }

    public synchronized void escribirImagen(DncImagen img) throws ExcepcionPersonajes {
        File f = new File(imagenDir, img.getId());

        try {
            FileUtils.writeByteArrayToFile(f, img.getDatos());
        }
        catch (IOException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes("Error general del sistema. Contacte con el administrador", ex.getMessage(), null, null);
            throw e;
        }
    }
}
