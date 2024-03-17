package dnc.pojospersonajes;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    public static final long serialVersionUID = 7L;
    private Integer usuarioId;
    private String email;
    private String nombreUsuario;
    private String passwd;
    private Boolean hash;

    public Usuario() {
        this.usuarioId = null;
        this.email = null;
        this.nombreUsuario = null;
        this.passwd = null;
        this.hash = false;
    }

    public Usuario(Integer usuarioId, String email, String nombreUsuario, String passwd, Boolean hash) {
        this.usuarioId = usuarioId;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.passwd = passwd;
        this.hash = hash;
    }

    public Usuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
        this.email = null;
        this.nombreUsuario = null;
        this.passwd = null;
        this.hash = false;
    }

    public Usuario(String email, String nombreUsuario, String passwd, Boolean hash) {
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.passwd = passwd;
        this.hash = hash;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Boolean getHash() {
        return hash;
    }

    public void setHash(Boolean hash) {
        this.hash = hash;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.email);
        hash = 17 * hash + Objects.hashCode(this.nombreUsuario);
        hash = 17 * hash + Objects.hashCode(this.passwd);
        hash = 17 * hash + Objects.hashCode(this.hash);
        return hash;
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.nombreUsuario, other.nombreUsuario)) {
            return false;
        }
        if (!Objects.equals(this.passwd, other.passwd)) {
            return false;
        }
        return Objects.equals(this.hash, other.hash);
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", nombreUsuario=" + nombreUsuario + ", passwd=" + passwd + ", hash=" + hash + '}';
    }
}
