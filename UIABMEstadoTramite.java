package ABMEstadoTramite.beans;

import utils.BeansUtils;
import ABMEstadoTramite.ControladorABMEstadoTramite;
import ABMEstadoTramite.dtos.ModificarEstadoTramiteDTO;
import ABMEstadoTramite.dtos.ModificarEstadoDTOIn;
import ABMEstadoTramite.dtos.NuevoEstadoTramiteDTO;
import ABMEstadoTramite.exceptions.EstadoTramiteException;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import org.omnifaces.util.Messages;


@Named("uiabmEstado")
@ViewScoped

public class UIABMEstadoTramite implements Serializable{

    private ControladorABMEstadoTramite controladorABMEstado = new ControladorABMEstadoTramite();
    private boolean insert;
    private String nombreEstadoTramite;
    private int codEstadoTramite;
    private String descripcionEstadoTramite;    
    
    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }


    public String getNombre() {
        return nombreEstadoTramite;
    }

    public void setNombre(String nombre) {
        this.nombreEstadoTramite = nombre;
    }

    public int getCodigo() {
        return codEstadoTramite;
    }

    public void setCodigo(int codigo) {
        this.codEstadoTramite = codigo;
    }
       public String getDescripcion() {
        return descripcionEstadoTramite;
    }

    public void setDescripcion(String descripcion) {
        this.descripcionEstadoTramite = descripcion; 
    }
    public UIABMEstadoTramite() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        insert=true;
        if(codigo > 0)
        {
            insert=false;
            ModificarEstadoTramiteDTO modificarEstadoDTO = controladorABMEstado.buscarEstadoAModificar(codigo);
            setNombre(modificarEstadoDTO.getNombre());
            setCodigo(modificarEstadoDTO.getCodigo());
        }
        
    }
    public String agregarEstado(){
        try {

        
            if(!insert)
            {

                ModificarEstadoDTOIn modificarEstadoDTOIn = new ModificarEstadoDTOIn();
                modificarEstadoDTOIn.setNombre(getNombre());
                modificarEstadoDTOIn.setCodigo(getCodigo());
                controladorABMEstado.modificarEstado(modificarEstadoDTOIn);
                return BeansUtils.redirectToPreviousPage();
            }
            else
            {
                NuevoEstadoTramiteDTO nuevoEstadoDTO = new NuevoEstadoTramiteDTO();
                nuevoEstadoDTO.setNombre(getNombre());
                nuevoEstadoDTO.setCodigo(getCodigo());
                controladorABMEstado.agregarEstado(nuevoEstadoDTO);

            }
            return BeansUtils.redirectToPreviousPage();
        }
        
        catch (EstadoTramiteException e) {
                Messages.create(e.getMessage()).fatal().add();
                return "";
         }
    }

}
