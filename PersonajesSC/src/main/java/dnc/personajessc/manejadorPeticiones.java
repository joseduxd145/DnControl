package dnc.personajessc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import dnc.pojospersonajes.*;
import dnc.cadpersonajes.CadPersonajes;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;

public class manejadorPeticiones implements Runnable {
    
    private final Socket clt;
    private CadPersonajes c;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private final Logger log;
    private MessageDigest md;
    
    public manejadorPeticiones(Socket clt, Logger log) {
        this.clt = clt;
        this.log = log;
    }
    
    @Override
    public void run() {
        log.info("Inicio de la comunicacion");
        try {
            md = MessageDigest.getInstance("SHA-256");
            
            c = new CadPersonajes("127.0.0.1", "DNCONTROL", "root", "");
            
            ois = new ObjectInputStream(clt.getInputStream());
            Peticion p = (Peticion) ois.readObject();
            
            if (!p.getUsuario().getHash()) {
                p.setUsuario(convertirUsuario(p.getUsuario()));
            }
            
            log.debug(p);
            
            Respuesta r = new Respuesta();
            r.setOp(p.getOp());
            oos = new ObjectOutputStream(clt.getOutputStream());

            //Tratar el caso de validacion de conexion
            if (p.getOp() == Operaciones.VALIDAR_CONEXION) {
                r.setEntidad(c.validarUsuario(p.getUsuario()));
                log.debug(r);
                oos.writeObject(r);
                ois.close();
                oos.close();
                clt.close();
                log.info("Fin de la comunicacion");
                return;
            }

            //Validar el usuario, si no se encuentra enviarle una excepcion
            if (!c.validarUsuario(p.getUsuario())) {
                r.setE(new ExcepcionPersonajes(
                        "El email, nombre de usuario o contraseña son incorrectos",
                        "El usuario no se encuentra registrado en la bd",
                        null,
                        null));
                log.error(r);
                oos.writeObject(r);
                ois.close();
                oos.close();
                clt.close();
                log.info("Fin de la comunicacion");
                return;
            }
            
            r = seleccionarOperacion(p, r);
            
            oos.writeObject(r);
            ois.close();
            oos.close();
            clt.close();
            log.info("Fin de la comunicacion");
        }
        catch (IOException ex) {
            manejarIOE(ex);
        }
        catch (ClassNotFoundException ex) {
            manejadorCNFE(ex);
        }
        catch (ExcepcionPersonajes ex) {
            manejadorEP(ex);
        }
        catch (NoSuchAlgorithmException ex) {
            manejardorNSAE(ex);
        }
        catch (NullPointerException ex) {
            manejadorNPE(ex);
        }
    }
    
    private Respuesta seleccionarOperacion(Peticion p, Respuesta r) throws ExcepcionPersonajes {
        //Switch manejador de las operaciones
        switch (p.getOp()) {
        case INSERTAR_USUARIO:
            r.setEntidad(insertarUsuario(p));
            break;
        case ELIMINAR_USUARIO:
            r.setEntidad(eliminarUsuario(p));
            break;
        case MODIFICAR_USUARIO:
            r.setEntidad(modificarUsuario(p));
            break;
        case LEER_USUARIO:
            r.setEntidad(leerUsuario(p));
            break;
        case INSERTAR_PERSONAJE:
            r.setEntidad(insertarPersonaje(p));
            break;
        case ELIMINAR_PERSONAJE:
            r.setEntidad(eliminarPersonaje(p));
            break;
        case MODIFICAR_PERSONAJE:
            r.setEntidad(modificarPersonaje(p));
            break;
        case LEER_PERSONAJE:
            r.setEntidad(leerPersonaje(p));
            break;
        case INSERTAR_OBJETO:
            r.setEntidad(insertarObjeto(p));
            break;
        case ELIMINAR_OBJETO:
            r.setEntidad(eliminarObjeto(p));
            break;
        case MODIFICAR_OBJETO:
            r.setEntidad(modificarObjeto(p));
            break;
        case LEER_OBJETO:
            r.setEntidad(leerObjeto(p));
            break;
        case INSERTAR_HABILIDAD:
            r.setEntidad(insertarHabilidad(p));
            break;
        case ELIMINAR_HABILIDAD:
            r.setEntidad(eliminarHabilidad(p));
            break;
        case MODIFICAR_HABILIDAD:
            r.setEntidad(modificarHabilidad(p));
            break;
        case LEER_HABILIDAD:
            r.setEntidad(leerHabilidad(p));
            break;
        case INSERTAR_PERSONAJE_HABILIDAD:
            r.setEntidad(insertarPersonajeHabilidad(p));
            break;
        case ELIMINAR_PERSONAJE_HABILIDAD:
            r.setEntidad(eliminarPersonajeHabilidad(p));
            break;
        case MODIFICAR_PERSONAJE_HABILIDAD:
            r.setEntidad(modificarPersonajeHabilidad(p));
            break;
        case LEER_PERSONAJE_HABILIDAD:
            r.setEntidad(leerPersonajeHabilidad(p));
            break;
        case INSERTAR_SEL_NUM_DADO:
            r.setEntidad(insertarSelNumDado(p));
            break;
        case ELIMINAR_SEL_NUM_DADO:
            r.setEntidad(eliminarSelNumDado(p));
            break;
        case MODIFICAR_SEL_NUM_DADO:
            r.setEntidad(modificarSelNumDado(p));
            break;
        case LEER_SEL_NUM_DADO:
            r.setEntidad(leerSelNumDado(p));
            break;
        default:
            r.setE(new ExcepcionPersonajes("Error general del sistema. Contacte con el administrador",
                    "Operacion desconocida",
                    null,
                    null));
            break;
        }
        log.debug(r);
        return r;
    }
    
    private Integer insertarUsuario(Peticion p) throws ExcepcionPersonajes {
        //Realizar hash sobre el objeto usuario para evitar problemas
        if (!p.getUsuario().getHash()) {
            p.setEntidad(convertirUsuario((Usuario) p.getEntidad()));
        }
        return c.insertarUsuario((Usuario) p.getEntidad());
    }
    
    private Integer eliminarUsuario(Peticion p) throws ExcepcionPersonajes {
        return c.eliminarUsuario(p.getArg1());
    }
    
    private Integer modificarUsuario(Peticion p) throws ExcepcionPersonajes {
        //Realizar hash sobre el objeto usuario para evitar problemas
        if (!p.getUsuario().getHash()) {
            p.setEntidad(convertirUsuario((Usuario) p.getEntidad()));
        }
        return c.modificarUsuario(p.getArg1(), (Usuario) p.getEntidad());
    }
    
    private Object leerUsuario(Peticion p) throws ExcepcionPersonajes {
        if (p.getArg1() == null) {
            return c.leerUsuario();
        }
        return c.leerUsuario(p.getArg1());
    }
    
    private Integer insertarPersonaje(Peticion p) throws ExcepcionPersonajes {
        return c.insertarPersonaje((Personaje) p.getEntidad());
    }
    
    private Integer eliminarPersonaje(Peticion p) throws ExcepcionPersonajes {
        return c.eliminarPersonaje(p.getArg1());
    }
    
    private Integer modificarPersonaje(Peticion p) throws ExcepcionPersonajes {
        return c.modificarPersonaje(p.getArg1(), (Personaje) p.getEntidad());
    }
    
    private Object leerPersonaje(Peticion p) throws ExcepcionPersonajes {
        if (p.getArg1() == null) {
            return c.leerPersonaje();
        }
        return c.leerPersonaje(p.getArg1());
    }
    
    private Integer insertarObjeto(Peticion p) throws ExcepcionPersonajes {
        return c.insertarObjeto((Objeto) p.getEntidad());
    }
    
    private Integer eliminarObjeto(Peticion p) throws ExcepcionPersonajes {
        return c.eliminarObjeto(p.getArg1());
    }
    
    private Integer modificarObjeto(Peticion p) throws ExcepcionPersonajes {
        return c.modificarObjeto(p.getArg1(), (Objeto) p.getEntidad());
    }
    
    private Object leerObjeto(Peticion p) throws ExcepcionPersonajes {
        if (p.getArg1() == null) {
            return c.leerObjeto();
        }
        return c.leerObjeto(p.getArg1());
    }
    
    private Integer insertarHabilidad(Peticion p) throws ExcepcionPersonajes {
        return c.insertarHabilidad((Habilidad) p.getEntidad());
    }
    
    private Integer eliminarHabilidad(Peticion p) throws ExcepcionPersonajes {
        return c.eliminarHabilidad(p.getArg1());
    }
    
    private Integer modificarHabilidad(Peticion p) throws ExcepcionPersonajes {
        return c.modificarHabilidad(p.getArg1(), (Habilidad) p.getEntidad());
    }
    
    private Object leerHabilidad(Peticion p) throws ExcepcionPersonajes {
        if (p.getArg1() == null) {
            return c.leerHabilidad();
        }
        return c.leerHabilidad(p.getArg1());
    }
    
    private Integer insertarPersonajeHabilidad(Peticion p) throws ExcepcionPersonajes {
        return c.insertarPersonajeHabilidad((PersonajeHabilidad) p.getEntidad());
    }
    
    private Integer eliminarPersonajeHabilidad(Peticion p) throws ExcepcionPersonajes {
        return c.eliminarPersonajeHabilidad(p.getArg1(), p.getArg2());
    }
    
    private Integer modificarPersonajeHabilidad(Peticion p) throws ExcepcionPersonajes {
        return c.modificarPersonajeHabilidad(p.getArg1(), p.getArg2(), (PersonajeHabilidad) p.getEntidad());
    }
    
    private Object leerPersonajeHabilidad(Peticion p) throws ExcepcionPersonajes {
        if (p.getArg1() == null && p.getArg2() == null) {
            return c.leerPersonajeHabilidad();
        }
        else if (p.getArg1() != null && p.getArg2() == null) {
            return c.leerPersonajeHabilidad(p.getArg1());
        }
        return c.leerPersonajeHabilidad(p.getArg1(), p.getArg2());
    }
    
    private Integer insertarSelNumDado(Peticion p) throws ExcepcionPersonajes {
        return c.insertarSelNumDado((SelNumDado) p.getEntidad());
    }
    
    private Integer eliminarSelNumDado(Peticion p) throws ExcepcionPersonajes {
        return c.eliminarSelNumDado(p.getArg1());
    }
    
    private Integer modificarSelNumDado(Peticion p) throws ExcepcionPersonajes {
        return c.modificarSelNumDado(p.getArg1(), (SelNumDado) p.getEntidad());
    }
    
    private Object leerSelNumDado(Peticion p) throws ExcepcionPersonajes {
        if (p.getArg1() == null) {
            return c.leerSelNumDado();
        }
        return c.leerSelNumDado(p.getArg1());
    }
    
    private void validarConexion() throws ExcepcionPersonajes {
        
    }
    
    private String realizarHash(String entrada) {
        String respuesta = "";
        String aux;
        byte[] hash = md.digest(entrada.getBytes(StandardCharsets.UTF_8));
        for (int i = 0; i < hash.length; i++) {
            aux = Integer.toHexString(hash[i] & 0xFF);
            if (aux.length() == 1) {
                aux = "0" + aux;
            }
            respuesta = respuesta + aux;
        }
        return respuesta;
    }
    
    private Usuario convertirUsuario(Usuario u) {
        u.setPasswd(realizarHash(u.getPasswd()));
        u.setHash(true);
        return u;
    }

    /**
     * Metodo para manejar una excepcion en las comunicaciones
     *
     * @param ex Excepcion de comunicaciones
     */
    private void manejarIOE(IOException ex) {
        log.error(ex);
    }

    /**
     * Metodo para manejar una ClassNotFoundException
     *
     * @param ex La excepcion manejar
     */
    private void manejadorCNFE(ClassNotFoundException ex) {
        log.error(ex);

        //Generar la excepcion
        ExcepcionPersonajes e = new ExcepcionPersonajes(
                "Error general del sistema. Contacte con el administrador",
                ex.getMessage(),
                null,
                null);
        
        if (oos != null) {
            Respuesta rError = new Respuesta();
            rError.setE(e);
            try {
                oos.writeObject(rError);
                ois.close();
                oos.close();
                clt.close();
            }
            catch (IOException ex1) {
                log.error(ex);
            }
        }
    }

    /**
     * Metodo para manejar una excepcion interna, producida por el cad
     *
     * @param ex La excepcion a tratar
     */
    private void manejadorEP(ExcepcionPersonajes ex) {
        log.error(ex);
        
        if (oos != null) {
            Respuesta rError = new Respuesta();
            rError.setE(ex);
            try {
                oos.writeObject(rError);
                ois.close();
                oos.close();
                clt.close();
            }
            catch (IOException ex1) {
                manejarIOE(ex1);
            }
        }
    }
    
    private void manejardorNSAE(NoSuchAlgorithmException ex) {
        log.error(ex);
        try {
            ois = new ObjectInputStream(clt.getInputStream());
            Peticion p = (Peticion) ois.readObject();
            log.debug(p);
            
            Respuesta r = new Respuesta();
            r.setOp(p.getOp());
            r.setE(new ExcepcionPersonajes(
                    "Error general del sistema, contacte con el administrador",
                    ex.getMessage(),
                    null,
                    null));
            oos = new ObjectOutputStream(clt.getOutputStream());
            
            oos.writeObject(r);
            ois.close();
            oos.close();
            clt.close();
        }
        catch (IOException ex1) {
            manejarIOE(ex1);
        }
        catch (ClassNotFoundException ex1) {
            manejadorCNFE(ex1);
        }
    }
    
    private void manejadorNPE(NullPointerException ex) {
        log.error(ex);
        
        if (oos != null) {
            Respuesta rError = new Respuesta();
            rError.setE(new ExcepcionPersonajes(
                    "Error general del sistema, contacte con el administrador",
                    ex.getMessage(),
                    null,
                    null));
            try {
                oos.writeObject(rError);
                ois.close();
                oos.close();
                clt.close();
            }
            catch (IOException ex1) {
                manejarIOE(ex1);
            }
        }
    }
}
