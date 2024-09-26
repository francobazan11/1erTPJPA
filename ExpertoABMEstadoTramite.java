package ABMEstadoTramite;

import ABMEstadoTramite.dtos.EstadoTramiteDTO;
import ABMEstadoTramite.dtos.ModificarEstadoTramiteDTO;
import ABMEstadoTramite.dtos.ModificarEstadoDTOIn;
import ABMEstadoTramite.dtos.NuevoEstadoTramiteDTO;
import entidades.Estado;
import ABMEstadoTramite.exceptions.EstadoTramiteException;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ExpertoABMEstadoTramite {
    public List<EstadoTramiteDTO> buscarEstados(int codEstadoTramite,String nombreEstadoTramite){
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if(codEstadoTramite>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codigo");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codEstadoTramite);
            lCriterio.add(unCriterio);
        }
        if(nombreEstadoTramite.trim().length()>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nombre");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombreEstadoTramite);
            lCriterio.add(unCriterio);
        }
        List objetoList = FachadaPersistencia.getInstance().buscar("Estado", lCriterio);
        List<EstadoTramiteDTO> estadosResultado = new ArrayList<>();
        for (Object x : objetoList) {
            Estado estado = (Estado) x;
            EstadoTramiteDTO estadoDTO = new EstadoTramiteDTO();
            estadoDTO.setCodigo(estado.getCodigo());
            estadoDTO.setNombre(estado.getNombre());
            estadoDTO.setFechaBaja(estado.getFechaBaja());
            estadoDTO.setFechaAlta(estado.getFechaAlta());
            estadosResultado.add(estadoDTO);
        }
        return estadosResultado;
    }

    public void agregarEstado(NuevoEstadoTramiteDTO nuevoEstadoDTO) throws EstadoTramiteException{
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        //buscar('Estado','codigo='+nuevoEstadoDTO.getCodigo() and fechaBaja=null' ) 
        
        List<DTOCriterio> criterioList = new ArrayList<>();
        
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codigo");
        dto.setOperacion("=");
        dto.setValor(nuevoEstadoDTO.getCodigo());
        criterioList.add(dto);
        
        DTOCriterio dto2  = new DTOCriterio();

        dto2.setAtributo("fechaBaja");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);
        
        List lEstado=FachadaPersistencia.getInstance().buscar("Estado", criterioList);
        System.out.println("Tamaño:"+lEstado.size());   
        if(lEstado.size()> 0)
        {
                throw new EstadoTramiteException("El código de estado ya existe");
        }
        else
        {
        Estado estado = new Estado();
        estado.setCodigo(nuevoEstadoDTO.getCodigo());
        estado.setNombre(nuevoEstadoDTO.getNombre());
        estado.setFechaAlta(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(estado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
        }
    }

    public ModificarEstadoTramiteDTO buscarEstadoAModificar(int codigo){
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("codigo");
        dto.setOperacion("=");
        dto.setValor(codigo);

        criterioList.add(dto);

        Estado estadoEncontrado = (Estado) FachadaPersistencia.getInstance().buscar("Estado", criterioList).get(0);

        ModificarEstadoTramiteDTO modificarEstadoDTO = new ModificarEstadoTramiteDTO();
        modificarEstadoDTO.setNombre(estadoEncontrado.getNombre());
        modificarEstadoDTO.setCodigo(estadoEncontrado.getCodigo());
        return modificarEstadoDTO;
    }

    public void modificarEstado(ModificarEstadoDTOIn modificarEstadoDTOIn){
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("codigo");
        dto.setOperacion("=");
        dto.setValor(modificarEstadoDTOIn.getCodigo());

        criterioList.add(dto);

        Estado estadoEncontrado = (Estado) FachadaPersistencia.getInstance().buscar("Estado", criterioList).get(0);

        estadoEncontrado.setCodigo(modificarEstadoDTOIn.getCodigo());
        estadoEncontrado.setNombre(modificarEstadoDTOIn.getNombre());

        FachadaPersistencia.getInstance().guardar(estadoEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }

    public void darDeBajaEstado(int codigo) throws EstadoTramiteException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("codigo");
        dto.setOperacion("=");
        dto.setValor(codigo);

        criterioList.add(dto);

        Estado estadoEncontrado = (Estado) FachadaPersistencia.getInstance().buscar("Estado", criterioList).get(0);

        criterioList.clear();
        dto = new DTOCriterio();

        dto.setAtributo("estado");
        dto.setOperacion("=");
        dto.setValor(estadoEncontrado);

        criterioList.add(dto);

        DTOCriterio dto2 = new DTOCriterio();

        dto2.setAtributo("fechaBaja");
        dto2.setOperacion("=");
        dto2.setValor(null);

        criterioList.add(dto2);

        List objetoList = FachadaPersistencia.getInstance().buscar("Articulo", criterioList);

        if (objetoList.size() != 0) {
            throw new EstadoTramiteException("Estado no puede darse de baja por estar asignado en al menos un articulo");
        }

        estadoEncontrado.setFechaBaja(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(estadoEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
}
