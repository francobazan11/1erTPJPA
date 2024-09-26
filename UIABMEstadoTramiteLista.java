package ABMEstadoTramite.beans;
import utils.BeansUtils;
import ABMEstadoTramite.ControladorABMEstadoTramite;
import ABMEstadoTramite.dtos.EstadoTramiteDTO;
import ABMEstadoTramite.exceptions.EstadoTramiteException;
import jakarta.annotation.ManagedBean;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

@Named("uiabmEstadoLista")
@ViewScoped
public class UIABMEstadoTramiteLista implements Serializable {

    private ControladorABMEstadoTramite controladorABMEstado = new ControladorABMEstadoTramite();
    private int codigoFiltro=0;
    private String nombreFiltro="";

    public ControladorABMEstadoTramite getControladorABMEstado() {
        return controladorABMEstado;
    }

    public void setControladorABMEstado(ControladorABMEstadoTramite controladorABMEstado) {
        this.controladorABMEstado = controladorABMEstado;
    }

    public int getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(int codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }

    public String getNombreFiltro() {
        return nombreFiltro;
    }

    public void setNombreFiltro(String descripcionFiltro) {
        this.nombreFiltro = descripcionFiltro;
    }
    public void filtrar()
    {

    }

    public List<EstadoTramiteGrillaUI> buscarEstados(){
        System.out.println(codigoFiltro);
        System.out.println(nombreFiltro);
        List<EstadoTramiteGrillaUI> estadosGrilla = new ArrayList<>();
        List<EstadoTramiteDTO> estadosDTO = controladorABMEstado.buscarEstados(codigoFiltro,nombreFiltro);
        for (EstadoTramiteDTO estadoDTO : estadosDTO) {
            EstadoTramiteGrillaUI estadoGrillaUI = new EstadoTramiteGrillaUI();
            estadoGrillaUI.setCodigo(estadoDTO.getCodigo());
            estadoGrillaUI.setNombre(estadoDTO.getNombre());
            estadoGrillaUI.setFechaAlta(estadoDTO.getFechaAlta());
            estadoGrillaUI.setFechaBaja(estadoDTO.getFechaBaja());
            estadosGrilla.add(estadoGrillaUI);
        }
        return estadosGrilla;
    }

    public String irAgregarEstado() {
        BeansUtils.guardarUrlAnterior();
        return "abmEstado?faces-redirect=true&codigo=0"; // Usa '?faces-redirect=true' para hacer una redirección
    }

    
    public String irModificarEstado(int codigo) {
        BeansUtils.guardarUrlAnterior();
        return "abmEstado?faces-redirect=true&codigo=" + codigo; // Usa '?faces-redirect=true' para hacer una redirección
    }

    public void darDeBajaEstado(int codigo){
        try {
            controladorABMEstado.darDeBajaEstado(codigo);
            Messages.create("Anulado").detail("Anulado").add();
                    
        } catch (EstadoTramiteException e) {
            Messages.create("Error!").error().detail(e.getMessage()).add();
        }
    }
    
}
