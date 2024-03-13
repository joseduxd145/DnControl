package dnc.personajescc;

import dnc.pojospersonajes.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Admin
 */
public class PersonajesCCIT {
    
    private final PersonajesCC pcc;
    
    
    public PersonajesCCIT() {
        Usuario u = new Usuario("", "", "");
        pcc = new PersonajesCC("192.168.1.205", 11037, u);
    }

    /**
     * Test of insertarUsuario method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testInsertarUsuario() throws Exception {
        Usuario u = new Usuario("", "", "");
        Integer ra;
        ra = pcc.insertarUsuario(u);
        assertEquals(0, ra);
    }

    /**
     * Test of eliminarUsuario method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testEliminarUsuario() throws Exception {
    }

    /**
     * Test of modificarUsuario method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testModificarUsuario() throws Exception {
    }

    /**
     * Test of leerUsuario method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerUsuario_0args() throws Exception {
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
    }

    /**
     * Test of leerSelNumDado method, of class PersonajesCC.
     */
    @org.junit.jupiter.api.Test
    public void testLeerSelNumDado_Integer() throws Exception {
    }
}
