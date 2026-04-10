package dnc.pojospersonajes;

import java.io.Serializable;
import java.util.Objects;

public class ExcepcionPersonajes extends Exception implements Serializable {

    public static final long serialVersionUID = 1L;
    private String mensajeUsuario;
    private String mensajeErrorAdmin;
    private String sentenciaSql;
    private Integer codigoErrorBd;
    private ErrorUsuario error;

    public ExcepcionPersonajes() {
        mensajeUsuario = "";
        mensajeErrorAdmin = "";
        sentenciaSql = "";
        codigoErrorBd = null;
        error = ErrorUsuario.DEFAULT;
    }

    public ExcepcionPersonajes(String mensajeUsuario, String mensajeErrorAdmin, String sentenciaSql, Integer codigoErrorBd, ErrorUsuario error) {
        this.mensajeUsuario = mensajeUsuario;
        this.mensajeErrorAdmin = mensajeErrorAdmin;
        this.sentenciaSql = sentenciaSql;
        this.codigoErrorBd = codigoErrorBd;
        this.error = error;
    }

    public ExcepcionPersonajes(String mensajeUsuario, String mensajeErrorAdmin, String sentenciaSql, Integer codigoErrorBd) {
        this.mensajeUsuario = mensajeUsuario;
        this.mensajeErrorAdmin = mensajeErrorAdmin;
        this.sentenciaSql = sentenciaSql;
        this.codigoErrorBd = codigoErrorBd;
        error = ErrorUsuario.DEFAULT;
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

    public Integer getCodigoErrorBd() {
        return codigoErrorBd;
    }

    public void setCodigoErrorBd(Integer codigoErrorBd) {
        this.codigoErrorBd = codigoErrorBd;
    }

    public ErrorUsuario getError() {
        return error;
    }

    public void setError(ErrorUsuario error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExcepcionPersonajes other = (ExcepcionPersonajes) obj;
        if (!Objects.equals(this.mensajeUsuario, other.mensajeUsuario)) {
            return false;
        }
        if (!Objects.equals(this.mensajeErrorAdmin, other.mensajeErrorAdmin)) {
            return false;
        }
        if (!Objects.equals(this.sentenciaSql, other.sentenciaSql)) {
            return false;
        }
        if (!Objects.equals(this.codigoErrorBd, other.codigoErrorBd)) {
            return false;
        }
        return this.error == other.error;
    }

    @Override
    public String toString() {
        return codigoErrorBd + "\t" + mensajeErrorAdmin + "\n\t" + sentenciaSql;
    }
}
