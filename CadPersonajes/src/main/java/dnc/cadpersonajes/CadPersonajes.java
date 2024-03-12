package dnc.cadpersonajes;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import dnc.pojospersonajes.*;

/**
 * Clase para acceder a la base de datos
 *
 * @author DAM205
 */
public class CadPersonajes {

    /**
     * String que contiene la cadena de conexion de la base de datos
     */
    private final String bd;
    /**
     * String que el usuario al que conectarse en la base de datos
     */
    private final String user;
    /**
     * String que contiene la contraseña para acceder al usuario
     */
    private final String passwd;

    /**
     * Secuencia SQL a lanzar
     */
    private String sql;
    /**
     * Conexion con la base de datos
     */
    private Connection con;

    /**
     * Constructor del cad para especificar ip tipo BD y usuario y contraseña
     *
     * @param ip     Cadena que contiene la direccion IP del servidor con la BD
     * @param tipo   Tipo de conexion de la BD Puede ser: xe test
     * @param user   Usuario al que conectarse en la base de datos
     * @param passwd Contraseña del usuario
     */
    public CadPersonajes(String ip, String tipo, String user, String passwd) {
        bd = "jdbc:oracle:thin:@" + ip + ":1521:" + tipo;

        this.user = user;
        this.passwd = passwd;

        sql = "";
        con = null;
    }

    /**
     * Metodo que carga el driver de la base de datos para su uso posterior,
     * este metodo debe ser llamado antes de construir un objeto de esta clase
     *
     * @throws ExcepcionPersonajes Lanza una excepcion si ocurre algun error
     *                             durante la carga del driver
     */
    public static void cargarDriver() throws ExcepcionPersonajes {
        try {
            //Cargar los drivers de la BD
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch (ClassNotFoundException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");

            throw e;
        }
    }

    /**
     * Metodo que intenta conectarse a la base de datos
     *
     * @throws SQLException Lanza una excepcion cuando la conexion con la BD
     *                      falla
     */
    private void conectarBd() throws SQLException {
        //Crear una conexion a la BD
        con = DriverManager.getConnection(bd, user, passwd);
    }

//Metodos de Usuario
    /**
     * Metodo para insertar un usuario en la base de datos
     *
     * @param u Objeto que contiene los datos del usuario a insertar
     *
     * @return El numero de registros afectados
     *
     * @throws ExcepcionPersonajes Lanza una excepcion cuando alguna constraint
     *                             de la BD es violada, o cuando se produce otro
     *                             error
     */
    public int insertarUsuario(Usuario u) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "INSERT INTO USUARIO(USUARIO_ID, EMAIL, NOMBRE_USUARIO, PASSWD) "
                + "VALUES(SEC_USUARIO.NEXTVAL, ?, ?, ?)";
        PreparedStatement sp;

        try {
            conectarBd();

            sp = con.prepareStatement(sql);

            sp.setString(1, u.getEmail());
            sp.setString(2, u.getNombreUsuario());
            sp.setString(3, u.getPasswd());

            ra = sp.executeUpdate();

            sp.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();

            e.setMensajeErrorAdmin(ex.getMessage());
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setSentenciaSql(sql);

            switch (e.getCodigoErrorBd()) {
            case 1:     //UNIKE KEY
                e.setMensajeUsuario("El email o el nombre del usuario ya existe");
                break;
            case 1400:  //NOT NULL
                e.setMensajeUsuario("El email, Usuario y contraseña son obligatorios");
                break;
            case 2290:  //CHECK CONSTRAINT
                e.setMensajeUsuario("El email debe seguir el formato ejemplo@ejempo.ejemplo");
                break;
            default:
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }
            throw e;
        }
        return ra;
    }

    /**
     * Metodo que modifica un usuario con nuevos datos
     *
     * @param id El indice de la Primary Key que se debe modificar
     * @param u  Objeto que contiene los nuevos datos del registro
     *
     * @return El numero de registros afectados
     *
     * @throws ExcepcionPersonajes Lanza una excepcion cuando alguna constraint
     *                             de la BD es violada, o cuando se produce otro
     *                             error
     */
    public int modificarUsuario(Integer id, Usuario u) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "UPDATE USUARIO SET EMAIL=?, NOMBRE_USUARIO=?, PASSWD=? WHERE USUARIO_ID=?";
        PreparedStatement sp;

        try {
            conectarBd();

            //Generar la sentencia preparada
            sp = con.prepareStatement(sql);

            //Modificar los campos de la sentencia preparada
            sp.setString(1, u.getEmail());
            sp.setString(2, u.getNombreUsuario());
            sp.setString(3, u.getPasswd());
            sp.setObject(4, id, Types.INTEGER);

            //Ejecutar la sentencia y obtener la cantidad de registros modificados
            ra = sp.executeUpdate();

            //Cerrar la sentencia preparada
            sp.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setSentenciaSql(sql);

            switch (ex.getErrorCode()) {
            case 1:     //Unike Key
                e.setMensajeUsuario("El email o el nombre del usuario ya existe");
                break;
            case 1407:  //Not NULL
                e.setMensajeUsuario("El email, nombre de usuario y contraseña son obligatorios");
                break;
            case 2290:  //Check Constraint
                e.setMensajeUsuario("El email debe seguir el formato ejemplo@ejempo.ejemplo");
                break;
            default:    //Otros errores
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            }

            throw e;
        }

        return ra;
    }

    /**
     * Metodo para eliminar un registro de la base de datos
     *
     * @param id El indice de la primary key del Usuario a eliminar
     *
     * @return El numero de registros afectados
     *
     * @throws ExcepcionPersonajes Lanza una excepcion cuando alguna constraint
     *                             de la BD es violada, o cuando se produce otro
     *                             error
     */
    public int eliminarUsuario(Integer id) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "DELETE FROM USUARIO WHERE USUARIO_ID=" + id;
        Statement s;

        try {
            conectarBd();
            s = con.createStatement();
            ra = s.executeUpdate(sql);
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setSentenciaSql(sql);

            switch (ex.getErrorCode()) {
            case 2292:  //Foreign Key
                e.setMensajeUsuario("No se puede eliminar un usuario que tiene personajes");
                break;
            default:    //Otros errores
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }

            throw e;
        }

        return ra;
    }

    /**
     * Funcion que recupera los datos de un usuario concreto
     *
     * @param id El indice de la primary key a leer de la BD
     *
     * @return El objeto Usuario que contiene los datos solicitados
     *
     * @throws ExcepcionPersonajes Lanza una excepcion cuando alguna constraint
     *                             de la BD es violada, o cuando se produce otro
     *                             error
     */
    public Usuario leerUsuario(Integer id) throws ExcepcionPersonajes {
        Usuario u = new Usuario();
        sql = "SELECT * FROM USUARIO WHERE USUARIO_ID=?";
        PreparedStatement sp;
        ResultSet res;

        try {
            conectarBd();

            sp = con.prepareCall(sql);
            sp.setObject(1, id, Types.INTEGER);
            res = sp.executeQuery();
            if (res.next()) {
                u = new Usuario(
                        ((BigDecimal) res.getObject("USUARIO_ID")).intValue(),
                        res.getString("EMAIL"),
                        res.getString("NOMBRE_USUARIO"),
                        res.getString("PASSWD"));
            }

            res.close();
            sp.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }
        return u;
    }

    /**
     * Metodo que lee todos los usuarios de la BD
     *
     * @return Una estructura con todos los datos de los usuarios
     *
     * @throws ExcepcionPersonajes Lanza una excepcion cuando alguna constraint
     *                             de la BD es violada, o cuando se produce otro
     *                             error
     */
    public ArrayList<Usuario> leerUsuario() throws ExcepcionPersonajes {
        ArrayList<Usuario> Usuarios = new ArrayList<>();
        sql = "SELECT * FROM USUARIO";
        Statement s;
        ResultSet res;

        try {
            conectarBd();

            s = con.createStatement();

            res = s.executeQuery(sql);
            while (res.next()) {
                Usuario u;
                u = new Usuario(
                        ((BigDecimal) res.getObject("USUARIO_ID")).intValue(),
                        res.getString("EMAIL"),
                        res.getString("NOMBRE_USUARIO"),
                        res.getString("PASSWD"));

                Usuarios.add(u);
            }

            res.close();
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }
        return Usuarios;
    }

//Metodos de personaje
    /**
     * Metodo para insertar un personaje en la base de datos
     *
     * @param p Objeto que contiene los datos con los que insertar el registro
     *
     * @return El numero de registros afectados
     *
     * @throws ExcepcionPersonajes Lanza una excepcion cuando alguna constraint
     *                             de la BD es violada, o cuando se produce otro
     *                             error
     */
    public int insertarPersonaje(Personaje p) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "CALL INSERTAR_PERSONAJE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        CallableStatement cl;

        try {
            conectarBd();

            cl = con.prepareCall(sql);

            //Modificar los campos de la sentencia preparada
            cl.setObject(1, p.getUsuarioId().getUsuarioId(), Types.INTEGER);
            cl.setString(2, p.getNombrePersonaje());
            cl.setString(3, p.getApellido());
            cl.setString(4, p.getTransfondo());
            cl.setObject(5, p.getFuerza(), Types.INTEGER);
            cl.setObject(6, p.getDestreza(), Types.INTEGER);
            cl.setObject(7, p.getConstitucion(), Types.INTEGER);
            cl.setObject(8, p.getInteligencia(), Types.INTEGER);
            cl.setObject(9, p.getSabiduria(), Types.INTEGER);
            cl.setObject(10, p.getCarisma(), Types.INTEGER);
            cl.setString(11, String.valueOf((p.getJugador().toLowerCase()).charAt(0)));

            ra = cl.executeUpdate();
            cl.close();

            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setSentenciaSql(sql);

            switch (e.getCodigoErrorBd()) {
            case 1:     //UNIKE KEY
                e.setMensajeUsuario("El nombre de personaje debe ser unico");
                break;
            case 1400:  //NOT NULL
                e.setMensajeUsuario("Los campos nombre, transfondo, jugador y los de estadisticas son obligatorios");
                break;
            case 2290:  //CHECK CONSTRAINT
                //Tambien salta con el esta excepcion con el campo JUGADOR
                e.setMensajeUsuario("Los valores de las estadisticas deben estar entre 1 y 50. Y el campo jugador solo puede ser J o E");
                break;
            case 2291:  //FOREIGN KEY
                e.setMensajeUsuario("El usuario seleccionado no existe");
                break;
            default:
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }

            throw e;
        }

        return ra;
    }

    /**
     * Metodo para modificar un personaje de la base de datos
     *
     * @param id El indice de la PK del personaje en la base de datos
     * @param p  El objeto que contiene los nuevos datos para remplazar
     *
     * @return El numero de registros alterados
     *
     * @throws ExcepcionPersonajes Lanza una excepcion cuando alguna constraint
     *                             de la BD es violada, o cuando se produce otro
     *                             error
     */
    public int modificarPersonaje(Integer id, Personaje p) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "UPDATE PERSONAJE SET "
                + "USUARIO_ID=?, NOMBRE_PERSONAJE=?, APELLIDO=?, TRANSFONDO=?, "
                + "FUERZA=?, DESTREZA=?, CONSTITUCION=?, INTELIGENCIA=?, "
                + "SABIDURIA=?, CARISMA=?, JUGADOR=? where PERSONAJE_ID=?";
        PreparedStatement sp;
        try {
            conectarBd();

            //Generar la sentencia preparada
            sp = con.prepareStatement(sql);

            //Modificar los campos de la sentencia preparada
            sp.setObject(1, p.getUsuarioId().getUsuarioId(), Types.INTEGER);
            sp.setString(2, p.getNombrePersonaje());
            sp.setString(3, p.getApellido());
            sp.setString(4, p.getTransfondo());
            sp.setObject(5, p.getFuerza(), Types.INTEGER);
            sp.setObject(6, p.getDestreza(), Types.INTEGER);
            sp.setObject(7, p.getConstitucion(), Types.INTEGER);
            sp.setObject(8, p.getInteligencia(), Types.INTEGER);
            sp.setObject(9, p.getSabiduria(), Types.INTEGER);
            sp.setObject(10, p.getCarisma(), Types.INTEGER);
            sp.setString(11, String.valueOf((p.getJugador().toLowerCase()).charAt(0)));
            sp.setObject(12, id, Types.INTEGER);

            //Ejecutar la sentencia y obtener la cantidad de registros modificados
            ra = sp.executeUpdate();

            //Cerrar la sentencia preparada
            sp.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setSentenciaSql(sql);

            switch (ex.getErrorCode()) {
            case 1407:  //Not NULL
                e.setMensajeUsuario("Los campos son obligatorios excepto del apellido");
                break;
            case 1:     //Unike Key
                e.setMensajeUsuario("El nombre del personaje debe ser unico");
                break;
            case 2290:  //Check Constraint
                e.setMensajeUsuario("Las estadisticas deben estar entre 1 y 50. Y/o el personaje debe ser j o e");
                break;
            case 2291:  //Foreign Key
                e.setMensajeUsuario("El personaje debe pertenecer a un usuario valido");
                break;
            default:    //Otros errores
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }

            throw e;
        }

        return ra;
    }

    /**
     * Metodo para eliminar un personaje del la base de datos
     *
     * @param id El indice de la PK que identifica al personaje a eliminar
     *
     * @return El numero de registros afectados
     *
     * @throws ExcepcionPersonajes Lanza una excepcion cuando alguna constraint
     *                             de la BD es violada, o cuando se produce otro
     *                             error
     */
    public int eliminarPersonaje(Integer id) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "DELETE FROM PERSONAJE WHERE PERSONAJE_ID=" + id;
        Statement s;
        try {
            conectarBd();
            s = con.createStatement();
            ra = s.executeUpdate(sql);
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setSentenciaSql(sql);

            switch (ex.getErrorCode()) {
            case 2292:  //Foreign Key
                e.setMensajeUsuario("No se puede eliminar un personaje que tiene objetos o habilidades asignados");
                break;
            default:    //Otros errores
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }

            throw e;
        }

        return ra;
    }

    /**
     * Lee un personaje concreto de la base de datos
     *
     * @param id El indice de la PK de la base de datos
     *
     * @return El objeto con los datos del registro solicitado
     *
     * @throws ExcepcionPersonajes Lanza una excepcion cuando alguna constraint
     *                             de la BD es violada, o cuando se produce otro
     *                             error
     */
    public Personaje leerPersonaje(Integer id) throws ExcepcionPersonajes {
        Personaje p = new Personaje();
        sql = "SELECT * FROM PERSONAJE p LEFT JOIN USUARIO u ON p.USUARIO_ID = u.USUARIO_ID where p.PERSONAJE_ID = " + id;
        Statement s;
        ResultSet res;

        try {
            conectarBd();

            s = con.createStatement();

            res = s.executeQuery(sql);
            if (res.next()) {
                Usuario u;

                //Evitar la excepcion al intentar leer un personaje que no tiene un usuario asignado
                try {
                    u = new Usuario(
                            ((BigDecimal) res.getObject("USUARIO_ID")).intValue(),
                            res.getString("EMAIL"),
                            res.getString("NOMBRE_USUARIO"),
                            res.getString("PASSWD")
                    );
                }
                catch (NullPointerException e) {
                    u = null;
                }

                p = new Personaje(
                        ((BigDecimal) res.getObject("PERSONAJE_ID")).intValue(),
                        u,
                        res.getString("NOMBRE_PERSONAJE"),
                        res.getString("APELLIDO"),
                        res.getString("TRANSFONDO"),
                        ((BigDecimal) res.getObject("FUERZA")).intValue(),
                        ((BigDecimal) res.getObject("DESTREZA")).intValue(),
                        ((BigDecimal) res.getObject("CONSTITUCION")).intValue(),
                        ((BigDecimal) res.getObject("INTELIGENCIA")).intValue(),
                        ((BigDecimal) res.getObject("SABIDURIA")).intValue(),
                        ((BigDecimal) res.getObject("CARISMA")).intValue(),
                        res.getString("JUGADOR"));
            }

            res.close();
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }
        return p;
    }

    /**
     * Metodo para obtener todos los personajes de la base de datos
     *
     * @return Un ArrayList que contiene los objetos con todos los parametros
     *
     * @throws ExcepcionPersonajes Lanza una excepcion cuando alguna constraint
     *                             de la BD es violada, o cuando se produce otro
     *                             error
     */
    public ArrayList<Personaje> leerPersonaje() throws ExcepcionPersonajes {
        ArrayList<Personaje> Personajes = new ArrayList<>();
        sql = "SELECT * FROM PERSONAJE p LEFT JOIN USUARIO u ON p.USUARIO_ID = u.USUARIO_ID";
        Statement s;
        ResultSet res;

        try {
            conectarBd();

            s = con.createStatement();

            res = s.executeQuery(sql);
            while (res.next()) {
                Usuario u;

                //Evitar la excepcion al intentar leer un personaje que no tiene un usuario asignado
                try {
                    u = new Usuario(
                            ((BigDecimal) res.getObject("USUARIO_ID")).intValue(),
                            res.getString("EMAIL"),
                            res.getString("NOMBRE_USUARIO"),
                            res.getString("PASSWD")
                    );
                }
                catch (NullPointerException e) {
                    u = null;
                }

                Personaje p;
                p = new Personaje(
                        ((BigDecimal) res.getObject("PERSONAJE_ID")).intValue(),
                        u,
                        res.getString("NOMBRE_PERSONAJE"),
                        res.getString("APELLIDO"),
                        res.getString("TRANSFONDO"),
                        ((BigDecimal) res.getObject("FUERZA")).intValue(),
                        ((BigDecimal) res.getObject("DESTREZA")).intValue(),
                        ((BigDecimal) res.getObject("CONSTITUCION")).intValue(),
                        ((BigDecimal) res.getObject("INTELIGENCIA")).intValue(),
                        ((BigDecimal) res.getObject("SABIDURIA")).intValue(),
                        ((BigDecimal) res.getObject("CARISMA")).intValue(),
                        res.getString("JUGADOR"));

                Personajes.add(p);
            }

            res.close();
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }
        return Personajes;
    }

    //Metodos de objeto
    /**
     * Metodo para insertar un objeto en la base de datos
     *
     * @param o El objeto a insertar, no es necesario que el atributo objetoId
     *          tenga algun valor
     *
     * @return El numero de registros afectados
     *
     * @throws ExcepcionPersonajes Lanza alguna excepcion cuando se infringe
     *                             alguna de las constraint de la base de datos
     */
    public int insertarObjeto(Objeto o) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "INSERT INTO OBJETO(OBJETO_ID, PERSONAJE_ID, NOMBRE_OBJETO, DESCRIPCION, VALOR) "
                + "VALUES(SEC_OBJETO.NEXTVAL, ?, ?, ?, ?)";
        PreparedStatement sp;

        try {
            conectarBd();

            sp = con.prepareStatement(sql);

            sp.setObject(1, o.getPersonajeId().getPersonajeId(), Types.INTEGER);
            sp.setString(2, o.getNombreObjeto());
            sp.setString(3, o.getDescripcion());
            sp.setObject(4, o.getValor(), Types.INTEGER);

            ra = sp.executeUpdate();
            sp.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setSentenciaSql(sql);

            switch (e.getCodigoErrorBd()) {
            case 1400:  //NOT NULL
                e.setMensajeUsuario("El nombre de objeto, descripcion y valor son obligatorios");
                break;
            case 2291:  //FOREIGN KEY
                e.setMensajeUsuario("El personaje no existe");
                break;
            default:
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }

            throw e;
        }

        return ra;
    }

    /**
     * Metodo para modificar un objeto
     *
     * @param id El id del objeto a modificar
     * @param o  El pojo con los datos modificados
     *
     * @return El numero de registros afectados, en caso de exito es 1, si falla
     *         es 0
     *
     * @throws ExcepcionPersonajes Lanza excepcion en caso de que se incumplan
     *                             una de las restricciones de la base de datos
     *                             o algun otro error relacionado con la base de
     *                             datos
     */
    public int modificarObjeto(Integer id, Objeto o) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "UPDATE OBJETO SET PERSONAJE_ID=?, NOMBRE_OBJETO=?, DESCRIPCION=?,"
                + "VALOR=? WHERE OBJETO_ID=?";
        PreparedStatement ps;

        try {
            conectarBd();
            ps = con.prepareStatement(sql);

            ps.setObject(1, o.getPersonajeId().getPersonajeId(), Types.INTEGER);
            ps.setString(2, o.getNombreObjeto());
            ps.setString(3, o.getDescripcion());
            ps.setObject(4, o.getValor(), Types.INTEGER);
            ps.setObject(5, o.getObjetoId(), Types.INTEGER);

            ra = ps.executeUpdate();
            ps.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setSentenciaSql(sql);

            switch (ex.getErrorCode()) {
            case 1407:  //Not NULL
                e.setMensajeUsuario("Los campos nombre de objeto, descripcion y valor son obligatorios");
                break;
            case 2291:  //Foreign Key
                e.setMensajeUsuario("El objeto debe pertenecer a un personaje valido");
                break;
            default:    //Otros errores
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }

            throw e;
        }

        return ra;
    }

    /**
     * Metodo que elimina un objeto de la tabla
     *
     * @param id El id del objeto a eliminar de la base de datos
     *
     * @return El numero de registros afectados, en caso de exito 1, en caso de
     *         fallo 0
     *
     * @throws ExcepcionPersonajes Lanza una excepcion en caso de error de la
     *                             base de datos
     */
    public int eliminarObjeto(Integer id) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "DELETE FROM OBJETO WHERE OBJETO_ID=" + id;
        Statement s;

        try {
            conectarBd();
            s = con.createStatement();
            ra = s.executeUpdate(sql);
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setSentenciaSql(sql);
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            throw e;
        }
        return ra;
    }

    /**
     * Metodo para leer todos los objetos de la tabla
     *
     * @return Una lista con todos los objetos, sus Personajes asignados y sus
     *         Usuarios asignados
     *
     * @throws ExcepcionPersonajes Lanza una excepcion en caso de error general
     *                             de la base de datos
     */
    public ArrayList<Objeto> leerObjeto() throws ExcepcionPersonajes {
        ArrayList<Objeto> objetos = new ArrayList<>();
        sql = "SELECT * FROM OBJETO o "
                + "LEFT JOIN PERSONAJE p  ON o.PERSONAJE_ID = p.PERSONAJE_ID "
                + "LEFT JOIN USUARIO u ON p.USUARIO_ID = u.USUARIO_ID;";
        Statement s;
        ResultSet res;
        try {
            conectarBd();

            s = con.createStatement();
            res = s.executeQuery(sql);

            while (res.next()) {
                Usuario u;

                //Evitar la excepcion al intentar leer un personaje que no tiene un usuario asignado
                try {
                    u = new Usuario(
                            ((BigDecimal) res.getObject("USUARIO_ID")).intValue(),
                            res.getString("EMAIL"),
                            res.getString("NOMBRE_USUARIO"),
                            res.getString("PASSWD")
                    );
                }
                catch (NullPointerException e) {
                    u = null;
                }

                Personaje p;

                try {
                    p = new Personaje(
                            ((BigDecimal) res.getObject("PERSONAJE_ID")).intValue(),
                            u,
                            res.getString("NOMBRE_PERSONAJE"),
                            res.getString("APELLIDO"),
                            res.getString("TRANSFONDO"),
                            ((BigDecimal) res.getObject("FUERZA")).intValue(),
                            ((BigDecimal) res.getObject("DESTREZA")).intValue(),
                            ((BigDecimal) res.getObject("CONSTITUCION")).intValue(),
                            ((BigDecimal) res.getObject("INTELIGENCIA")).intValue(),
                            ((BigDecimal) res.getObject("SABIDURIA")).intValue(),
                            ((BigDecimal) res.getObject("CARISMA")).intValue(),
                            res.getString("JUGADOR"));
                }
                catch (NullPointerException e) {
                    p = null;
                }

                Objeto o;

                o = new Objeto(
                        ((BigDecimal) res.getObject("OBJETO_ID")).intValue(),
                        p,
                        res.getString("NOMBRE_OBJETO"),
                        res.getString("DESCRIPCION"),
                        ((BigDecimal) res.getObject("VALOR")).intValue());

                objetos.add(o);
            }
            res.close();
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }

        return objetos;
    }

    /**
     * Metodo para leer solo un objeto
     *
     * @param id La id del objeto a leer
     *
     * @return El objeto con su personaje asignado y usuario
     *
     * @throws ExcepcionPersonajes Lanza una excepcion en caso de error de la
     *                             base de datos
     */
    public Objeto leerObjeto(Integer id) throws ExcepcionPersonajes {
        Objeto o = new Objeto();
        sql = "SELECT * FROM OBJETO o "
                + "LEFT JOIN PERSONAJE p  ON o.PERSONAJE_ID = p.PERSONAJE_ID "
                + "LEFT JOIN USUARIO u ON p.USUARIO_ID = u.USUARIO_ID "
                + "WHERE o.OBJETO_ID = " + id;
        Statement s;
        ResultSet res;

        try {
            conectarBd();
            s = con.createStatement();
            res = s.executeQuery(sql);

            if (res.next()) {
                Usuario u;

                //Evitar la excepcion al intentar leer un personaje que no tiene un usuario asignado
                try {
                    u = new Usuario(
                            ((BigDecimal) res.getObject("USUARIO_ID")).intValue(),
                            res.getString("EMAIL"),
                            res.getString("NOMBRE_USUARIO"),
                            res.getString("PASSWD")
                    );
                }
                catch (NullPointerException e) {
                    u = null;
                }

                Personaje p;

                try {
                    p = new Personaje(
                            ((BigDecimal) res.getObject("PERSONAJE_ID")).intValue(),
                            u,
                            res.getString("NOMBRE_PERSONAJE"),
                            res.getString("APELLIDO"),
                            res.getString("TRANSFONDO"),
                            ((BigDecimal) res.getObject("FUERZA")).intValue(),
                            ((BigDecimal) res.getObject("DESTREZA")).intValue(),
                            ((BigDecimal) res.getObject("CONSTITUCION")).intValue(),
                            ((BigDecimal) res.getObject("INTELIGENCIA")).intValue(),
                            ((BigDecimal) res.getObject("SABIDURIA")).intValue(),
                            ((BigDecimal) res.getObject("CARISMA")).intValue(),
                            res.getString("JUGADOR"));
                }
                catch (NullPointerException e) {
                    p = null;
                }

                o = new Objeto(
                        ((BigDecimal) res.getObject("OBJETO_ID")).intValue(),
                        p,
                        res.getString("NOMBRE_OBJETO"),
                        res.getString("DESCRIPCION"),
                        ((BigDecimal) res.getObject("VALOR")).intValue());

            }

            res.close();
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }

        return o;
    }

    /**
     * Metodo para insertar una habilidad
     *
     * @param h La habilidad a insertar
     *
     * @return El numero de registros afectado 1 en exito, 0 en caso de fallo
     *
     * @throws ExcepcionPersonajes Lanza una excepcion si se incumple alguna de
     *                             las restricciones de la base de datos o un
     *                             error propio de la base de datos
     */
    public int insertarHabilidad(Habilidad h) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "INSERT INTO HABILIDAD(HABILIDAD_ID, NUM_DADO_ID, NOMBRE_HABILIDAD, "
                + "DESCRIPCION, CANTIDAD_DADO) VALUES "
                + "(SEC_HABILIDAD.NEXTVAL, ?, ?, ?, ?)";
        PreparedStatement ps;

        try {
            conectarBd();
            ps = con.prepareStatement(sql);

            ps.setObject(1, h.getNumDadoId().getNumDadoId(), Types.INTEGER);
            ps.setString(2, h.getNombreHabilidad());
            ps.setString(3, h.getDescripcion());
            ps.setObject(4, h.getCantidadDado(), Types.INTEGER);

            ra = ps.executeUpdate();
            ps.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setSentenciaSql(sql);

            switch (e.getCodigoErrorBd()) {
            case 1400:  //NOT NULL
                e.setMensajeUsuario("El nombre de la habilidad, descripcion y cantidad de dados son obligatorios");
                break;
            case 2291:  //FOREIGN KEY
                e.setMensajeUsuario("El tipo de dado no existe");
                break;
            default:
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }
            throw e;
        }
        return ra;
    }

    /**
     * Metodo para modificar una habilidad
     *
     * @param id El id de la habilidad a modificar
     * @param h  Un objeto con los nuevos datos
     *
     * @return El numero de registros afectados, debe ser 1 en caso de exito
     *
     * @throws ExcepcionPersonajes Lanza en caso de incumplir alguna restriccion
     *                             de la base de datos o un error de la base de
     *                             datos
     */
    public int modificarHabilidad(Integer id, Habilidad h) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "UPDATE HABILIDAD SET NUM_DADO_ID=?, NOMBRE_HABILIDAD=?, "
                + "DESCRIPCION=?, CANTIDAD_DADO=? WHERE HABILIDAD_ID=?";
        PreparedStatement ps;

        try {
            conectarBd();
            ps = con.prepareStatement(sql);

            ps.setObject(1, h.getNumDadoId().getNumDadoId(), Types.INTEGER);
            ps.setString(2, h.getNombreHabilidad());
            ps.setString(3, h.getDescripcion());
            ps.setObject(4, h.getCantidadDado(), Types.INTEGER);
            ps.setObject(5, h.getHabilidadId(), Types.INTEGER);

            ra = ps.executeUpdate();
            ps.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setSentenciaSql(sql);

            switch (ex.getErrorCode()) {
            case 1407:  //Not NULL
                e.setMensajeUsuario("El nombre de la habilidad, descripcion y cantidad de dados son obligatorios");
                break;
            case 2291:  //FOREIGN KEY
                e.setMensajeUsuario("El tipo de dado no existe");
                break;
            default:
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }

            throw e;
        }

        return ra;
    }

    /**
     * Metodo para eliminar una habilidad de la base de datos
     *
     * @param id La id del registro a eliminar
     *
     * @return El numero de registros afectados, debe ser 1
     *
     * @throws ExcepcionPersonajes Lanza excepcion en caso de incumplir alguna
     *                             restriccion o por la base de datos
     */
    public int eliminarHabilidad(Integer id) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "DELETE FROM HABILIDAD WHERE HABILIDAD_ID = " + id;
        Statement s;
        try {
            conectarBd();
            s = con.createStatement();
            ra = s.executeUpdate(sql);
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setSentenciaSql(sql);

            switch (ex.getErrorCode()) {
            case 2292:  //Foreign Key
                e.setMensajeUsuario("No se puede eliminar una habilidad que tenga personajes asignados");
                break;
            default:    //Otros errores
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }

            throw e;
        }
        return ra;
    }

    /**
     * Metodo para leer todas las habilidades de la tabla
     *
     * @return Una lista con todas las habilidades
     *
     * @throws ExcepcionPersonajes Lanza una excepcion en caso de fallar la base
     *                             de datos
     */
    public ArrayList<Habilidad> leerHabilidad() throws ExcepcionPersonajes {
        ArrayList<Habilidad> habilidades = new ArrayList<>();
        sql = "SELECT * FROM HABILIDAD h"
                + "LEFT JOIN SEL_NUM_DADO s ON h.NUM_DADO_ID = s.NUM_DADO_ID";
        Statement s;
        ResultSet res;
        try {
            conectarBd();
            s = con.createStatement();
            res = s.executeQuery(sql);

            while (res.next()) {
                SelNumDado snd;
                try {
                    snd = new SelNumDado(
                            ((BigDecimal) res.getObject("NUM_DADO_ID")).intValue(),
                            ((BigDecimal) res.getObject("NUM_DADO")).intValue());
                }
                catch (NullPointerException e) {
                    snd = null;
                }

                Habilidad h;

                h = new Habilidad(
                        ((BigDecimal) res.getObject("HABILIDAD_ID")).intValue(),
                        snd,
                        res.getString("NOMBRE_HABILIDAD"),
                        res.getString("DESCRIPCION"),
                        ((BigDecimal) res.getObject("CANTIDAD_DADO")).intValue());

                habilidades.add(h);
            }

            res.close();
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }

        return habilidades;
    }

    /**
     * Metodo para leer una habilidad
     *
     * @param id El id de la habilidad a leer
     *
     * @return La habilidad leida o un objeto con sus atributos a null
     *
     * @throws ExcepcionPersonajes Lanza una excepcion si ocurre un problema con
     *                             la base de datos
     */
    public Habilidad leerHabilidad(Integer id) throws ExcepcionPersonajes {
        Habilidad h = new Habilidad();
        sql = "SELECT * FROM HABILIDAD h"
                + "LEFT JOIN SEL_NUM_DADO s ON h.NUM_DADO_ID = s.NUM_DADO_ID"
                + "WHERE h.HABILIDAD_ID = " + id;
        Statement s;
        ResultSet res;
        try {
            conectarBd();
            s = con.createStatement();
            res = s.executeQuery(sql);

            if (res.next()) {
                SelNumDado snd;
                try {
                    snd = new SelNumDado(
                            ((BigDecimal) res.getObject("NUM_DADO_ID")).intValue(),
                            ((BigDecimal) res.getObject("NUM_DADO")).intValue());
                }
                catch (NullPointerException e) {
                    snd = null;
                }

                h = new Habilidad(
                        ((BigDecimal) res.getObject("HABILIDAD_ID")).intValue(),
                        snd,
                        res.getString("NOMBRE_HABILIDAD"),
                        res.getString("DESCRIPCION"),
                        ((BigDecimal) res.getObject("CANTIDAD_DADO")).intValue());
            }

            res.close();
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }
        return h;
    }

    /**
     * Metodo para insertar un tipo de dados a la bd
     *
     * @param snd Objeto con los datos a insertar
     *
     * @return 1 en caso de exito, 0 en caso de fallo
     *
     * @throws ExcepcionPersonajes Lanza una excepcion si se incumple alguna
     *                             constraint o falla la base de datos
     */
    public int insertarSelNumDado(SelNumDado snd) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "INSERT INTO SEL_NUM_DADO(NUM_DADO_ID, NUM_DADO) "
                + "VALUES(SEC_SEL_NUM_DADO.NEXTVAL, ?)";
        PreparedStatement ps;
        try {
            conectarBd();
            ps = con.prepareStatement(sql);
            ps.setObject(1, snd.getNumDado(), Types.INTEGER);
            ra = ps.executeUpdate();
            ps.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setSentenciaSql(sql);

            switch (e.getCodigoErrorBd()) {
            case 1400:  //NOT NULL
                e.setMensajeUsuario("El numero del dado es obligatorio");
                break;
            default:
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }

            throw e;
        }

        return ra;
    }

    /**
     * Metodo para modificar un tipo de dado
     *
     * @param id  El id del dado a modificar
     * @param snd Un objeto con los nuevos datos
     *
     * @return 1 en caso de exito, otro si no ocurrio
     *
     * @throws ExcepcionPersonajes Lanza una excepcion si se incumple alguna
     *                             constraint o falla la base de datos
     */
    public int modificarSelNumDado(Integer id, SelNumDado snd) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "UPDATE SEL_NUM_DADO SET NUM_DADO=? WHERE NUM_DADO_ID=?";
        PreparedStatement ps;
        try {
            conectarBd();
            ps = con.prepareStatement(sql);
            ps.setObject(1, snd.getNumDado(), Types.INTEGER);
            ps.setObject(2, snd.getNumDadoId(), Types.INTEGER);
            ra = ps.executeUpdate();
            ps.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setSentenciaSql(sql);

            switch (ex.getErrorCode()) {
            case 1407:  //Not NULL
                e.setMensajeUsuario("El numero del dado es obligatorio");
                break;
            default:    //Otros errores
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            }

            throw e;
        }

        return ra;
    }

    /**
     * Metodo para eliminar un tipo de dado
     *
     * @param id La id del dado a eliminar
     *
     * @return 1 En exito, 0 si no se pudo eliminar
     *
     * @throws ExcepcionPersonajes Lanza una excepcion si se incumple alguna
     *                             constraint o falla la base de datos
     */
    public int eliminarSelNumDado(Integer id) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "DELETE FROM SEL_NUM_DADO WHERE NUM_DADO_ID = " + id;
        Statement s;
        try {
            conectarBd();
            s = con.createStatement();
            ra = s.executeUpdate(sql);
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setSentenciaSql(sql);

            switch (ex.getErrorCode()) {
            case 2292:  //Foreign Key
                e.setMensajeUsuario("No se puede eliminar un tipo de dado que tiene habilidades asignadas");
                break;
            default:    //Otros errores
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }

            throw e;
        }

        return ra;
    }

    /**
     * Metodo para leer 1 tipo de dado de la bd
     *
     * @param id El id del dado a leer
     *
     * @return Un objeto con los datos del dado
     *
     * @throws ExcepcionPersonajes Lanza una excepcion si falla la base de datos
     */
    public SelNumDado leerSelNumDado(Integer id) throws ExcepcionPersonajes {
        SelNumDado snd = new SelNumDado();
        sql = "SELECT * FROM SEL_NUM_DADO WHERE NUM_DADO_ID = " + id;
        Statement s;
        ResultSet res;
        try {
            conectarBd();
            s = con.createStatement();
            res = s.executeQuery(sql);

            if (res.next()) {
                snd = new SelNumDado(
                        ((BigDecimal) res.getObject("NUM_DADO_ID")).intValue(),
                        ((BigDecimal) res.getObject("NUM_DADO")).intValue());
            }

            res.close();
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }

        return snd;
    }

    /**
     * Metodo para leer todos los dados de la bd
     *
     * @return Una lista con objetos que contienen los dados
     *
     * @throws ExcepcionPersonajes Lanza una excepcion si falla la base de datos
     */
    public ArrayList<SelNumDado> leerSelNumDado() throws ExcepcionPersonajes {
        ArrayList<SelNumDado> selNumDado = new ArrayList<>();
        sql = "SELECT * FROM SEL_NUM_DADO";
        Statement s;
        ResultSet res;
        try {
            conectarBd();
            s = con.createStatement();
            res = s.executeQuery(sql);

            if (res.next()) {
                SelNumDado snd;
                snd = new SelNumDado(
                        ((BigDecimal) res.getObject("NUM_DADO_ID")).intValue(),
                        ((BigDecimal) res.getObject("NUM_DADO")).intValue());
                selNumDado.add(snd);
            }

            res.close();
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }

        return selNumDado;
    }

    /**
     * Metodo para insertar una relacion entre Personaje y Habilidad
     *
     * @param ph Un objeto con el personaje y la habilidad a relacionar
     *
     * @return
     *
     * @throws ExcepcionPersonajes
     */
    public int insertarPersonajeHabilidad(PersonajeHabilidad ph) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "INSERT INTO PERSONAJE_HABILIDAD(PERSONAJE_ID, HABILIDAD_ID)"
                + "VALUES(?,?)";
        PreparedStatement ps;
        try {
            conectarBd();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ph.getPersonajeId().getPersonajeId(), Types.INTEGER);
            ps.setObject(2, ph.getHabilidadId().getHabilidadId(), Types.INTEGER);
            ra = ps.executeUpdate();
            ps.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setSentenciaSql(sql);

            switch (e.getCodigoErrorBd()) {
            case 1:     //UNIQUE KEY
                e.setMensajeUsuario("La union entre ese personaje y habilidad ya existe");
                break;
            case 1400:  //NOT NULL
                e.setMensajeUsuario("El personaje y habilidad son obligatorios");
                break;
            case 2291:  //FOREIGN KEY
                e.setMensajeUsuario("El personaje o habilidad no existe");
                break;
            default:
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }

            throw e;
        }

        return ra;
    }

    /**
     * Metodo para modificar la relacion entre un personaje y una habilidad
     *
     * @param personajeId La id del personaje actual
     * @param habilidadId La id de la habilidad actual
     * @param ph          Un objeto con los nuevos datos a remplazar
     *
     * @return Devuelve el numero de registros actualizados, debe ser 1 o cero
     *         si falla
     *
     * @throws ExcepcionPersonajes Lanza excepcion si se incumple alguna
     *                             restriccion de la base de datos o falla la
     *                             comunicacion con la base de datos
     */
    public int modificarPersonajeHabilidad(Integer personajeId, Integer habilidadId, PersonajeHabilidad ph) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "UPDATE PERSONAJE_HABILIDAD SET PERSONAJE_ID=?, HABILIDAD_ID=?"
                + "WHERE PERSONAJE_ID=?, HABILIDAD_ID=?";
        PreparedStatement ps;
        try {
            conectarBd();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ph.getPersonajeId().getPersonajeId(), Types.INTEGER);
            ps.setObject(2, ph.getHabilidadId().getHabilidadId(), Types.INTEGER);
            ps.setObject(3, personajeId, Types.INTEGER);
            ps.setObject(4, habilidadId, Types.INTEGER);
            ra = ps.executeUpdate();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setSentenciaSql(sql);

            switch (e.getCodigoErrorBd()) {
            case 1:     //UNIQUE KEY
                e.setMensajeUsuario("La union entre ese personaje y habilidad ya existe");
                break;
            case 1400:  //NOT NULL
                e.setMensajeUsuario("El personaje y habilidad son obligatorios");
                break;
            case 2291:  //FOREIGN KEY
                e.setMensajeUsuario("El personaje o habilidad no existe");
                break;
            default:
                e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
                break;
            }

            throw e;
        }

        return ra;
    }

    /**
     * Metodo para eliminar una relacion entre personaje y habilidad
     *
     * @param personajeId La id del personaje a eliminar
     * @param habilidadId La id de la habilidad a eliminar
     *
     * @return El numero de relaciones eliminadas, debe ser 1 o 0 en caso de no
     *         ser encontrada la relacion
     *
     * @throws ExcepcionPersonajes Lanza excepcion cuando falla la comunicacion
     *                             con la base de datos
     */
    public int eliminarPersonajeHabilidad(Integer personajeId, Integer habilidadId) throws ExcepcionPersonajes {
        int ra = 0;
        sql = "DELETE FROM PERSONAJE_HABILIDAD WHERE PERSONAJE_ID=? AND HABILIDAD_ID=?";
        PreparedStatement ps;
        try {
            conectarBd();
            ps = con.prepareStatement(sql);
            ps.setObject(1, personajeId, Types.INTEGER);
            ps.setObject(2, habilidadId, Types.INTEGER);
            ra = ps.executeUpdate();
            ps.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setSentenciaSql(sql);
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            throw e;
        }

        return ra;
    }

    /**
     * Metodo para leer una relacion entre personaje y habilidad
     *
     * @param personajeId El id del personaje
     * @param habilidadId El id de la habilidad
     *
     * @return Un objeto con los datos de cada relacion
     *
     * @throws ExcepcionPersonajes Lanza una excepcion si falla la comunicacion
     *                             con la bd
     * @deprecated Este metodo se considera deprecado debido a que si se conocen
     * los id de las relaciones entre un Personaje y una Habilidad, es
     * inecesario realizar un sql para verificarlo. Aunque este metodo se
     * mantendra actualizado por compatibilidad, se recomienda el uso de la
     * version que solo necesita un personaje. Este metodo se puede segir
     * utilizando sin problema, aunque es redundante
     *
     * @see CadPersonajes#leerPersonajeHabilidad(java.lang.Integer) Este metodo
     * se recomienda como sustituto
     */
    public PersonajeHabilidad leerPersonajeHabilidad(Integer personajeId, Integer habilidadId) throws ExcepcionPersonajes {
        PersonajeHabilidad ph = new PersonajeHabilidad();
        sql = "SELECT * FROM PERSONAJE_HABILIDAD ph "
                + "LEFT JOIN PERSONAJE p ON ph.PERSONAJE_ID = p.PERSONAJE_ID "
                + "LEFT JOIN USUARIO u ON p.USUARIO_ID = u.USUARIO_ID "
                + "LEFT JOIN HABILIDAD h ON ph.HABILIDAD_ID = h.HABILIDAD_ID "
                + "LEFT JOIN SEL_NUM_DADO snd ON h.NUM_DADO_ID = snd.NUM_DADO_ID "
                + "WHERE ph.PERSONAJE_ID=? AND HABILIDAD_ID=?";
        PreparedStatement ps;
        ResultSet res;
        try {
            conectarBd();
            ps = con.prepareStatement(sql);
            ps.setObject(1, personajeId, Types.INTEGER);
            ps.setObject(2, habilidadId, Types.INTEGER);
            res = ps.executeQuery();

            if (res.next()) {
                Usuario u;

                //Evitar la excepcion al intentar leer un personaje que no tiene un usuario asignado
                try {
                    u = new Usuario(
                            ((BigDecimal) res.getObject("USUARIO_ID")).intValue(),
                            res.getString("EMAIL"),
                            res.getString("NOMBRE_USUARIO"),
                            res.getString("PASSWD")
                    );
                }
                catch (NullPointerException e) {
                    u = null;
                }

                Personaje p;

                try {
                    p = new Personaje(
                            ((BigDecimal) res.getObject("PERSONAJE_ID")).intValue(),
                            u,
                            res.getString("NOMBRE_PERSONAJE"),
                            res.getString("APELLIDO"),
                            res.getString("TRANSFONDO"),
                            ((BigDecimal) res.getObject("FUERZA")).intValue(),
                            ((BigDecimal) res.getObject("DESTREZA")).intValue(),
                            ((BigDecimal) res.getObject("CONSTITUCION")).intValue(),
                            ((BigDecimal) res.getObject("INTELIGENCIA")).intValue(),
                            ((BigDecimal) res.getObject("SABIDURIA")).intValue(),
                            ((BigDecimal) res.getObject("CARISMA")).intValue(),
                            res.getString("JUGADOR"));
                }
                catch (NullPointerException e) {
                    p = null;
                }

                SelNumDado snd;
                try {
                    snd = new SelNumDado(
                            ((BigDecimal) res.getObject("NUM_DADO_ID")).intValue(),
                            ((BigDecimal) res.getObject("NUM_DADO")).intValue());
                }
                catch (NullPointerException e) {
                    snd = null;
                }

                Habilidad h;

                try {
                    h = new Habilidad(
                            ((BigDecimal) res.getObject("HABILIDAD_ID")).intValue(),
                            snd,
                            res.getString("NOMBRE_HABILIDAD"),
                            res.getString("DESCRIPCION"),
                            ((BigDecimal) res.getObject("CANTIDAD_DADO")).intValue());
                }
                catch (NullPointerException e) {
                    h = null;
                }

                if (p != null && h != null) {
                    ph = new PersonajeHabilidad(p, h);
                }
            }

            res.close();
            ps.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }

        return ph;
    }

    /**
     * Metodo para leer todas las habilidades a las que esta relacionado un
     * personaje concreto
     *
     * @param id La id del personaje del cual leer todas las habilidades
     *
     * @return Una lista con las habilidades de dicho personaje
     *
     * @throws ExcepcionPersonajes Lanza excepcion si falla la comunicacion con
     *                             la bd
     */
    public ArrayList<Habilidad> leerPersonajeHabilidad(Integer id) throws ExcepcionPersonajes {
        ArrayList<Habilidad> habilidades = new ArrayList<>();
        sql = "SELECT * FROM PERSONAJE_HABILIDAD ph "
                + "LEFT JOIN HABILIDAD h ON ph.HABILIDAD_ID = h.HABILIDAD_ID "
                + "LEFT JOIN SEL_NUM_DADO snd ON h.NUM_DADO_ID = snd.NUM_DADO_ID "
                + "WHERE ph.PERSONAJE_ID = " + id;
        Statement s;
        ResultSet res;

        try {
            conectarBd();
            s = con.createStatement();
            res = s.executeQuery(sql);

            while (res.next()) {
                SelNumDado snd;
                try {
                    snd = new SelNumDado(
                            ((BigDecimal) res.getObject("NUM_DADO_ID")).intValue(),
                            ((BigDecimal) res.getObject("NUM_DADO")).intValue());
                }
                catch (NullPointerException e) {
                    snd = null;
                }

                Habilidad h;
                h = new Habilidad(
                        ((BigDecimal) res.getObject("HABILIDAD_ID")).intValue(),
                        snd,
                        res.getString("NOMBRE_HABILIDAD"),
                        res.getString("DESCRIPCION"),
                        ((BigDecimal) res.getObject("CANTIDAD_DADO")).intValue());

                habilidades.add(h);
            }
            res.close();
            s.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }
        return habilidades;
    }

    /**
     * Metodo para leer todas las relaciones entre personajes y habilidades
     *
     * @return Una lista con las relaciones de los personajes y habilidades
     *
     * @throws ExcepcionPersonajes Lanza una excepcion si falla la comunicacion
     *                             con la bd
     */
    public ArrayList<PersonajeHabilidad> leerPersonajeHabilidad() throws ExcepcionPersonajes {
        ArrayList<PersonajeHabilidad> personajesHabilidades = new ArrayList<>();
        sql = "SELECT * FROM PERSONAJE_HABILIDAD ph "
                + "LEFT JOIN PERSONAJE p ON ph.PERSONAJE_ID = p.PERSONAJE_ID "
                + "LEFT JOIN USUARIO u ON p.USUARIO_ID = u.USUARIO_ID "
                + "LEFT JOIN HABILIDAD h ON ph.HABILIDAD_ID = h.HABILIDAD_ID "
                + "LEFT JOIN SEL_NUM_DADO snd ON h.NUM_DADO_ID = snd.NUM_DADO_ID";
        Statement s;
        ResultSet res;
        try {
            conectarBd();
            s = con.createStatement();
            res = s.executeQuery(sql);

            while (res.next()) {
                Usuario u;

                //Evitar la excepcion al intentar leer un personaje que no tiene un usuario asignado
                try {
                    u = new Usuario(
                            ((BigDecimal) res.getObject("USUARIO_ID")).intValue(),
                            res.getString("EMAIL"),
                            res.getString("NOMBRE_USUARIO"),
                            res.getString("PASSWD")
                    );
                }
                catch (NullPointerException e) {
                    u = null;
                }

                Personaje p;

                try {
                    p = new Personaje(
                            ((BigDecimal) res.getObject("PERSONAJE_ID")).intValue(),
                            u,
                            res.getString("NOMBRE_PERSONAJE"),
                            res.getString("APELLIDO"),
                            res.getString("TRANSFONDO"),
                            ((BigDecimal) res.getObject("FUERZA")).intValue(),
                            ((BigDecimal) res.getObject("DESTREZA")).intValue(),
                            ((BigDecimal) res.getObject("CONSTITUCION")).intValue(),
                            ((BigDecimal) res.getObject("INTELIGENCIA")).intValue(),
                            ((BigDecimal) res.getObject("SABIDURIA")).intValue(),
                            ((BigDecimal) res.getObject("CARISMA")).intValue(),
                            res.getString("JUGADOR"));
                }
                catch (NullPointerException e) {
                    p = null;
                }

                SelNumDado snd;
                try {
                    snd = new SelNumDado(
                            ((BigDecimal) res.getObject("NUM_DADO_ID")).intValue(),
                            ((BigDecimal) res.getObject("NUM_DADO")).intValue());
                }
                catch (NullPointerException e) {
                    snd = null;
                }

                Habilidad h;

                try {
                    h = new Habilidad(
                            ((BigDecimal) res.getObject("HABILIDAD_ID")).intValue(),
                            snd,
                            res.getString("NOMBRE_HABILIDAD"),
                            res.getString("DESCRIPCION"),
                            ((BigDecimal) res.getObject("CANTIDAD_DADO")).intValue());
                }
                catch (NullPointerException e) {
                    h = null;
                }

                if (p == null || h == null) {
                    continue;
                }

                PersonajeHabilidad ph = new PersonajeHabilidad(p, h);
                personajesHabilidades.add(ph);
            }

            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }

        return personajesHabilidades;
    }

    /**
     * Metodo que valida que un usuario se encuentra dentro de la tabla USUARIO
     *
     * @param u El usuario a comprobar, con los datos. Solo son necesarios el
     *          EMAIL, NOMBRE_USUARIO, PASSWD para verificar
     *
     * @return Devuelve true si el usuario se encuentra en la bd, devulve false
     *         en caso contrario
     *
     * @throws ExcepcionPersonajes Lanza una excepcion si ocurre algun error en
     *                             la comunicacion con la bd o los datos de
     *                             entrada son invalidos
     */
    public boolean validarUsuario(Usuario u) throws ExcepcionPersonajes {
        boolean validacion = false;
        sql = "SELECT EMAIL, NOMBRE_USUARIO, PASSWD FROM USUARIO"
                + "WHERE EMAIL LIKE ? AND NOMBRE_USUARIO LIKE ? AND PASSWD LIKE ?";
        PreparedStatement ps;
        ResultSet res;
        try {
            conectarBd();
            ps = con.prepareStatement(sql);

            ps.setString(1, u.getEmail());
            ps.setString(2, u.getNombreUsuario());
            ps.setString(3, u.getPasswd());

            res = ps.executeQuery();

            if (res.next()) {
                Usuario usuario;

                try {
                    usuario = new Usuario(res.getString("EMAIL"),
                            res.getString("NOMBRE_USUARIO"),
                            res.getString("PASSWD"));
                }
                catch (NullPointerException e) {
                    usuario = new Usuario();
                }

                validacion = u.equals(usuario);
            }

            res.close();
            ps.close();
            con.close();
        }
        catch (SQLException ex) {
            ExcepcionPersonajes e = new ExcepcionPersonajes();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorAdmin(ex.getMessage());
            e.setMensajeUsuario("Error de login, contacte con el administrador");
            e.setSentenciaSql(sql);

            throw e;
        }
        return validacion;
    }
}
