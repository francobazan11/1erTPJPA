package ABMEstadoTramite.beans;

import java.sql.Timestamp;

// Clase usada para mostrar los estados en la grilla al iniciar el CU ABMEstados
public class EstadoTramiteGrillaUI {
    private int codEstadoTramite;
    private String nombreEstadoTramite;
    private String descripcionEstadoTramite;
    private Timestamp fechaAlta;
    private Timestamp fechaBaja;

    public int getCodigo() {
        return codEstadoTramite;
    }

    public void setCodigo(int codigo) {
        this.codEstadoTramite = codigo;
    }

    public String getNombre() {
        return nombreEstadoTramite;
    }

    public void setNombre(String nombre) {
        this.nombreEstadoTramite = nombre;
    }
     public String getDescripcion() {
        return descripcionEstadoTramite;
    }

    public void setDescripcion(String descripcion) {
        this.descripcionEstadoTramite = descripcion;
    }

    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Timestamp getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Timestamp fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
}
