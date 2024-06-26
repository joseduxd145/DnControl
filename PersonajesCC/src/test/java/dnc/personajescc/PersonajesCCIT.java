package dnc.personajescc;

import dnc.pojospersonajes.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Admin
 */
public class PersonajesCCIT {

    private final PersonajesCC pcc;

    public PersonajesCCIT() {
        //Usuario para poder realizar acciones a traves del servidor
        Usuario u = new Usuario("admin@gmail.com", "admin", "admin", false);
        pcc = new PersonajesCC("192.168.1.41", 11037, u);
    }

    private Usuario generarUsuarioRandom() {
        return new Usuario("testIt" + Math.random() + "@test" + Math.random() + ".es", "test" + Math.random(), "test" + Math.random(), false);
    }

    /**
     * Test of insertarUsuario method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testInsertarUsuario() throws Exception {
        Integer ra;
        ra = pcc.insertarUsuario(generarUsuarioRandom());
        assertEquals(1, ra);
        ra = pcc.insertarUsuario(generarUsuarioRandom());
        assertEquals(1, ra);
    }

    /**
     * Test of eliminarUsuario method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testEliminarUsuario() throws Exception {
        Integer id = 6;
        Integer ra;
        ra = pcc.eliminarUsuario(id);
        assertEquals(1, ra);
    }

    /**
     * Test of modificarUsuario method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testModificarUsuario() throws Exception {
        Integer id = 7;
        Integer ra;
        ra = pcc.modificarUsuario(id, generarUsuarioRandom());
        assertEquals(1, ra);
    }

    /**
     * Test of leerUsuario method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerUsuario_0args() throws Exception {
        ArrayList<Usuario> expected = new ArrayList<>();
        ArrayList<Usuario> actual = pcc.leerUsuario();
        assertNotEquals(expected, actual);
        assertNotEquals(0, actual.size());
    }

    /**
     * Test of leerUsuario method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerUsuario_int() throws Exception {
        
    }

    /**
     * Test of insertarPersonaje method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testInsertarPersonaje() throws Exception {
    }

    /**
     * Test of eliminarPersonaje method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testEliminarPersonaje() throws Exception {
    }

    /**
     * Test of modificarPersonaje method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testModificarPersonaje() throws Exception {
    }

    /**
     * Test of leerPersonaje method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerPersonaje_0args() throws Exception {
    }

    /**
     * Test of leerPersonaje method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerPersonaje_Integer() throws Exception {
    }

    /**
     * Test of insertarObjeto method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testInsertarObjeto() throws Exception {
    }

    /**
     * Test of eliminarObjeto method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testEliminarObjeto() throws Exception {
    }

    /**
     * Test of modificarObjeto method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testModificarObjeto() throws Exception {
    }

    /**
     * Test of leerObjeto method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerObjeto_0args() throws Exception {
    }

    /**
     * Test of leerObjeto method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerObjeto_Integer() throws Exception {
    }

    /**
     * Test of insertarHabilidad method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testInsertarHabilidad() throws Exception {
    }

    /**
     * Test of eliminarHabilidad method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testEliminarHabilidad() throws Exception {
    }

    /**
     * Test of modificarHabilidad method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testModificarHabilidad() throws Exception {
    }

    /**
     * Test of leerHabilidad method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerHabilidad_0args() throws Exception {
    }

    /**
     * Test of leerHabilidad method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerHabilidad_Integer() throws Exception {
    }

    /**
     * Test of insertarPersonajeHabilidad method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testInsertarPersonajeHabilidad() throws Exception {
    }

    /**
     * Test of eliminarPersonajeHabilidad method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testEliminarPersonajeHabilidad() throws Exception {
    }

    /**
     * Test of modificarPersonajeHabilidad method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testModificarPersonajeHabilidad() throws Exception {
    }

    /**
     * Test of leerPersonajeHabilidad method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerPersonajeHabilidad_0args() throws Exception {
    }

    /**
     * Test of leerPersonajeHabilidad method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerPersonajeHabilidad_Integer() throws Exception {
    }

    /**
     * Test of leerPersonajeHabilidad method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerPersonajeHabilidad_Integer_Integer() throws Exception {
    }

    /**
     * Test of insertarSelNumDado method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testInsertarSelNumDado() throws Exception {
    }

    /**
     * Test of eliminarSelNumDado method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testEliminarSelNumDado() throws Exception {
    }

    /**
     * Test of modificarSelNumDado method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testModificarSelNumDado() throws Exception {
    }

    /**
     * Test of leerSelNumDado method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerSelNumDado_0args() throws Exception {
        ArrayList<SelNumDado> lista =pcc.leerSelNumDado();
        System.out.println(lista);
    }

    /**
     * Test of leerSelNumDado method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerSelNumDado_Integer() throws Exception {
    }

    /**
     * Test of leerPersonajeUsuario method, of class PersonajesCC.
     */
    @Test
    public void testLeerPersonajeUsuario() throws Exception {
    }

    /**
     * Test of leerEnemigos method, of class PersonajesCC.
     */
    @Test
    public void testLeerEnemigos() throws Exception {
        ArrayList<Personaje> personajes;
        personajes = pcc.leerPersonajeUsuario();
        System.out.println(personajes);
    }

    /**
     * Test of validarConexion method, of class PersonajesCC.
     */
    @Test
    public void testValidarConexion() throws Exception {
    }
}
