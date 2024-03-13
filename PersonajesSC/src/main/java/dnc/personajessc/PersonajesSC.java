package dnc.personajessc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import dnc.pojospersonajes.*;
import dnc.cadpersonajes.CadPersonajes;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class PersonajesSC {

    public static void main(String[] args) {
        //Iniciar logger
        PropertyConfigurator.configure(PersonajesSC.class.getClassLoader().getResource("logs/log4j.properties"));
        Logger log = LogManager.getLogger("MAINLOG");
        
        int puertoServidor = 11037;
        try {
            CadPersonajes.cargarDriver();
            log.debug("JDBC cargado");
            ServerSocket sr = new ServerSocket(puertoServidor);
            log.info("Servidor iniciado, iniciando escucha");
            Socket clt;
            while (true) {
                clt = sr.accept();

                log.info(String.format("Conexion recivida desde %s y puerto %d",
                        clt.getInetAddress().getHostAddress(), clt.getPort()));

                manejadorPeticiones mp = new manejadorPeticiones(clt, log);
                new Thread(mp).start();
            }
        }
        catch (IOException | ExcepcionPersonajes ex) {
            log.fatal(ex);
        }
    }
}
