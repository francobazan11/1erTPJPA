package com.pruebaSpring.helloSpring.entities;

import com.pruebaSpring.helloSpring.enums.TipoProducto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "producto")
public class Producto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codProducto;
    
    @Column(name = "tipoProducto")
    private TipoProducto tipo;
    
    @Column(name = "tiempoEstimadoCocina")
    private int tiempoEstimadoCocina;
            
    @Column(name = "denominacion")
    private String denominacion;
    
    @Column(name = "precioVenta")
    private Double precioVenta;
    
    @Column(name = "precioCompra")
    private Double precioCompra;
    
    @Column(name = "stockActual")
    private int stockActual;
    
    @Column(name = "stockMinimo")
    private int stockMinimo;
    
    @Column(name = "unidadVedida")
    private String unidadVedida;
    
    @Column(name = "receta")
    private String receta;
    

}
