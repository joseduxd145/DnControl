package dnc.pojospersonajes;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    public static final long serialVersionUID = 7L;
    private Integer usuarioId;
    private String email;
    private String nombreUsuario;
    private String passwd;

    public Usuario() {
        this.usuarioId = null;
        this.email = null;
        this.nombreUsuario = null;
        this.passwd = null;
    }

    public Usuario(Integer usuarioId, String email, String nombreUsuario, String passwd) {
        this.usuarioId = usuarioId;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.passwd = passwd;
    }

    public Usuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
        this.email = null;
        this.nombreUsuario = null;
        this.passwd = null;
    }

    public Usuario(String email, String nombreUsuario, String passwd) {
        this.usuarioId = null;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.passwd = passwd;
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

    @Override
    public String toString() {
        return "Usuario{" + "usuarioId=" + usuarioId + ", email=" + email + ", nombreUsuario=" + nombreUsuario + ", passwd=" + passwd + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.email);
        hash = 47 * hash + Objects.hashCode(this.nombreUsuario);
        hash = 47 * hash + Objects.hashCode(this.passwd);
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
        return Objects.equals(this.passwd, other.passwd);
    }

}
