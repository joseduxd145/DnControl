package dnc.personajescc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import dnc.pojospersonajes.*;

public class PersonajesCC {

    /**
     * Conexion con el servidor
     */
    private Socket con;
    /**
     * Direccion ip del servidor
     */
    private final String ipServidor;
    /**
     * Puerto del servidor
     */
    private final int puerto;

    /**
     * Constructor del cliente, que asigna los parametros de conexion con el
     * servidor
     *
     * @param ipServidor La direccion IPv4 en un string en el que se encuentra
     *                   el servidor al que conectar
     * @param puerto     El puerto que utilizar para la conexion con el servidor
     */
    public PersonajesCC(String ipServidor, int puerto) {
        this.ipServidor = ipServidor;
        this.puerto = puerto;
    }

    /**
     * Metodo interno que gestiona la comunicacion con el servidor
     *
     * @param p La peticion generada por los metodos de la clase
     *
     * @return La respuesta del servidor
     *
     * @throws ExcepcionPersonajes Lanza la excepcion en caso de recivirla del
     *                             servidor o ser generada por el cliente de
     *                             comunicaciones
     */
    private Object conectar(Peticion p) throws ExcepcionPersonajes {
        Object res = null;
        try {
            //Conectar al servidor
            con = new Socket(ipServidor, puerto);
            con.setSoTimeout(10000);

            //Generar el output stream y enviar la peticion al servidor
            ObjectOutputStream oos = new ObjectOutputStream(con.getOutputStream());
            oos.writeObject(p);

            //Generar el input stream y leer la respuesta del servidor
            ObjectInputStream ois = new ObjectInputStream(con.getInputStream());
            Respuesta r = (Respuesta) ois.readObject();

            //Cerrar los stream
            oos.close();
            ois.close();
            //Cerrar la comunicacion con servidor
            con.close();

            //Comprobar si se devolvio una excepcion, y si es asi lanzarla
            if (r.getE() != null) {
                throw r.getE();
            }

            //Devolver el resultado
            res = r.getEntidad();
        }
        catch (IOException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes(
                    "Error en las comunicaciones. Vuelva a intentar en unos minutos",
                    ex.getMessage(),
                    null,
                    null);

            throw e;
        }
        catch (ClassNotFoundException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes(
                    "Error general del sistema. Contacte con el administrador",
                    ex.getMessage(),
                    null,
                    null);

            throw e;
        }

        return res;
    }

    /**
     * Metodo para insertar un usuario del cliente de comunicaciones en la base
     * de datos
     *
     * @param u El usuario a insertar en la BD
     *
     * @return Un entero que determina si se realizo correctamente la insercion,
     *         1 = correcto, 0 = no se inserto
     *
     * @throws ExcepcionPersonajes Lanza una excepcion causada por el metodo
     *                             conectar
     * @see #conectar(pojospersonajes.Peticion)
     */
    public Integer insertarUsuario(Usuario u) throws ExcepcionPersonajes {
        Peticion p = new Peticion(Operaciones.INSERTAR_USUARIO, null, u);
        return (Integer) conectar(p);
    }

    /**
     * Metodo para leer todos los usuarios del servidor
     *
     * @return Un ArrayList con todos los usuarios de la base de datos
     *
     * @throws ExcepcionPersonajes Lanza una excepcion causada por el metodo
     *                             conectar
     * @see #conectar(pojospersonajes.Peticion)
     */
    public ArrayList<Usuario> leerUsuario() throws ExcepcionPersonajes {
        Peticion p = new Peticion(Operaciones.LEER_USUARIO, null, null);
        return (ArrayList<Usuario>) conectar(p);
    }

    /**
     * Metodo para leer un usuario dela BD
     *
     * @param id El identificador del usuario a leer
     *
     * @return El usuario solicitado o un objeto con sus campos a null
     *
     * @throws ExcepcionPersonajes Lanza una excepcion causada por el metodo
     *                             conectar
     * @see #conectar(pojospersonajes.Peticion)
     */
    public Usuario leerUsuario(int id) throws ExcepcionPersonajes {
        Peticion p = new Peticion(Operaciones.LEER_USUARIO, id, null);
        return (Usuario) conectar(p);
    }
}
