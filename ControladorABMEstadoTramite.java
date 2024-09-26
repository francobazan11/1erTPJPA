package ABMEstadoTramite;

import ABMEstadoTramite.dtos.EstadoTramiteDTO;
import ABMEstadoTramite.dtos.ModificarEstadoTramiteDTO;
import ABMEstadoTramite.dtos.ModificarEstadoDTOIn;
import ABMEstadoTramite.dtos.NuevoEstadoTramiteDTO;
import ABMEstadoTramite.exceptions.EstadoTramiteException;

import java.util.List;

public class ControladorABMEstadoTramite {
    private ExpertoABMEstadoTramite expertoABMEstado = new ExpertoABMEstadoTramite();
    public List<EstadoTramiteDTO> buscarEstados(int codEstadoTramite, String nombreEstadoTramite){
        return expertoABMEstado.buscarEstados(codEstadoTramite,nombreEstadoTramite);
    }

    public void agregarEstado(NuevoEstadoTramiteDTO nuevoEstadoDTO) throws EstadoTramiteException{
        expertoABMEstado.agregarEstado(nuevoEstadoDTO);
    }

    public void modificarEstado(ModificarEstadoDTOIn modificarEstadoDTOIn){
        expertoABMEstado.modificarEstado(modificarEstadoDTOIn);
    }

    public ModificarEstadoTramiteDTO buscarEstadoAModificar(int codigo){
        return expertoABMEstado.buscarEstadoAModificar(codigo);
    }

    public void darDeBajaEstado(int codigo) throws EstadoTramiteException {
        expertoABMEstado.darDeBajaEstado(codigo);
    }
    
}
