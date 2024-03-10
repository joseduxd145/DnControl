package dnc.pojospersonajes;

import java.io.Serializable;

public class ExcepcionPersonajes extends Exception implements Serializable {

    public static final long serialVersionUID = 1L;
    private String mensajeUsuario;
    private String mensajeErrorAdmin;
    private String sentenciaSql;
    private Integer codigoErrorBd;

    public ExcepcionPersonajes() {
        mensajeUsuario = "";
        mensajeErrorAdmin = "";
        sentenciaSql = "";
        codigoErrorBd = null;
    }

    public ExcepcionPersonajes(String mensajeUsuario, String mensajeErrorBd, String sentenciaSql, Integer codigoErrorBd) {
        this.mensajeUsuario = mensajeUsuario;
        this.mensajeErrorAdmin = mensajeErrorBd;
        this.sentenciaSql = sentenciaSql;
        this.codigoErrorBd = codigoErrorBd;
    }

    public Integer getCodigoErrorBd() {
        return codigoErrorBd;
    }

    public void setCodigoErrorBd(Integer codigoErrorBd) {
        this.codigoErrorBd = codigoErrorBd;
    }

    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    public void setMensajeUsuario(String mensajeUsuario) {
        this.mensajeUsuario = mensajeUsuario;
    }

    public String getMensajeErrorAdmin() {
        return mensajeErrorAdmin;
    }

    public void setMensajeErrorAdmin(String mensajeErrorAdmin) {
        this.mensajeErrorAdmin = mensajeErrorAdmin;
    }

    public String getSentenciaSql() {
        return sentenciaSql;
    }

    public void setSentenciaSql(String sentenciaSql) {
        this.sentenciaSql = sentenciaSql;
    }

    @Override
    public String toString() {
        return codigoErrorBd + "\t" + mensajeErrorAdmin + "\n\t" + sentenciaSql;
    }

}
