package dnc.cadpersonajes;

import dnc.pojospersonajes.ExcepcionPersonajes;
import dnc.pojospersonajes.Usuario;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Admin
 */
public class CadPersonajesTest {
    
    CadPersonajes c;
    public CadPersonajesTest() throws ExcepcionPersonajes{
        CadPersonajes.cargarDriver();
        c = new CadPersonajes("127.0.0.1", "dncontrol", "root", "");
    }

    /**
     * Test of insertarUsuario method, of class CadPersonajes.
     */
    @Test
    public void testInsertarUsuario() throws Exception {
    }

    /**
     * Test of modificarUsuario method, of class CadPersonajes.
     */
    @Test
    public void testModificarUsuario() throws Exception {
    }

    /**
     * Test of eliminarUsuario method, of class CadPersonajes.
     */
    @Test
    public void testEliminarUsuario() throws Exception {
    }

    /**
     * Test of leerUsuario method, of class CadPersonajes.
     */
    @Test
    public void testLeerUsuario_Integer() throws Exception {
        Integer id = 4;
        Usuario expected = new Usuario();
        Usuario actual = c.leerUsuario(id);
        assertNotEquals(expected, actual);
    }

    /**
     * Test of leerUsuario method, of class CadPersonajes.
     */
    @Test
    public void testLeerUsuario_0args() throws Exception {
        ArrayList<Usuario> expected = new ArrayList<>();
        ArrayList<Usuario> actual = c.leerUsuario();
        assertNotEquals(expected, actual);
        assertNotEquals(0, actual.size());
    }

    /**
     * Test of insertarPersonaje method, of class CadPersonajes.
     */
    @Test
    public void testInsertarPersonaje() throws Exception {
    }

    /**
     * Test of modificarPersonaje method, of class CadPersonajes.
     */
    @Test
    public void testModificarPersonaje() throws Exception {
    }

    /**
     * Test of eliminarPersonaje method, of class CadPersonajes.
     */
    @Test
    public void testEliminarPersonaje() throws Exception {
    }

    /**
     * Test of leerPersonaje method, of class CadPersonajes.
     */
    @Test
    public void testLeerPersonaje_Integer() throws Exception {
    }

    /**
     * Test of leerPersonaje method, of class CadPersonajes.
     */
    @Test
    public void testLeerPersonaje_0args() throws Exception {
    }

    /**
     * Test of insertarObjeto method, of class CadPersonajes.
     */
    @Test
    public void testInsertarObjeto() throws Exception {
    }

    /**
     * Test of modificarObjeto method, of class CadPersonajes.
     */
    @Test
    public void testModificarObjeto() throws Exception {
    }

    /**
     * Test of eliminarObjeto method, of class CadPersonajes.
     */
    @Test
    public void testEliminarObjeto() throws Exception {
    }

    /**
     * Test of leerObjeto method, of class CadPersonajes.
     */
    @Test
    public void testLeerObjeto_0args() throws Exception {
    }

    /**
     * Test of leerObjeto method, of class CadPersonajes.
     */
    @Test
    public void testLeerObjeto_Integer() throws Exception {
    }

    /**
     * Test of insertarHabilidad method, of class CadPersonajes.
     */
    @Test
    public void testInsertarHabilidad() throws Exception {
    }

    /**
     * Test of modificarHabilidad method, of class CadPersonajes.
     */
    @Test
    public void testModificarHabilidad() throws Exception {
    }

    /**
     * Test of eliminarHabilidad method, of class CadPersonajes.
     */
    @Test
    public void testEliminarHabilidad() throws Exception {
    }

    /**
     * Test of leerHabilidad method, of class CadPersonajes.
     */
    @Test
    public void testLeerHabilidad_0args() throws Exception {
    }

    /**
     * Test of leerHabilidad method, of class CadPersonajes.
     */
    @Test
    public void testLeerHabilidad_Integer() throws Exception {
    }

    /**
     * Test of insertarSelNumDado method, of class CadPersonajes.
     */
    @Test
    public void testInsertarSelNumDado() throws Exception {
    }

    /**
     * Test of modificarSelNumDado method, of class CadPersonajes.
     */
    @Test
    public void testModificarSelNumDado() throws Exception {
    }

    /**
     * Test of eliminarSelNumDado method, of class CadPersonajes.
     */
    @Test
    public void testEliminarSelNumDado() throws Exception {
    }

    /**
     * Test of leerSelNumDado method, of class CadPersonajes.
     */
    @Test
    public void testLeerSelNumDado_Integer() throws Exception {
    }

    /**
     * Test of leerSelNumDado method, of class CadPersonajes.
     */
    @Test
    public void testLeerSelNumDado_0args() throws Exception {
    }

    /**
     * Test of insertarPersonajeHabilidad method, of class CadPersonajes.
     */
    @Test
    public void testInsertarPersonajeHabilidad() throws Exception {
    }

    /**
     * Test of modificarPersonajeHabilidad method, of class CadPersonajes.
     */
    @Test
    public void testModificarPersonajeHabilidad() throws Exception {
    }

    /**
     * Test of eliminarPersonajeHabilidad method, of class CadPersonajes.
     */
    @Test
    public void testEliminarPersonajeHabilidad() throws Exception {
    }

    /**
     * Test of leerPersonajeHabilidad method, of class CadPersonajes.
     */
    @Test
    public void testLeerPersonajeHabilidad_Integer_Integer() throws Exception {
    }

    /**
     * Test of leerPersonajeHabilidad method, of class CadPersonajes.
     */
    @Test
    public void testLeerPersonajeHabilidad_Integer() throws Exception {
    }

    /**
     * Test of leerPersonajeHabilidad method, of class CadPersonajes.
     */
    @Test
    public void testLeerPersonajeHabilidad_0args() throws Exception {
    }

    /**
     * Test of validarUsuario method, of class CadPersonajes.
     */
    @Test
    public void testValidarUsuario() throws Exception {
    }
    
}
