package ABMEstadoTramite.dtos;


public class ModificarEstadoTramiteDTO {
    private int codEstadoTramite;
    private String nombreEstadoTramite;
    private String descripcionEstadoTramite;

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
}
