package com.pruebaSpring.helloSpring.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detallePedido")
public class DetallePedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codDetallePedido;
    
    @Column(name="cantidad")
    private int cantidad;
    
    @Column(name="subTotal")
    private double subTotal;
    
    @ManyToOne
    @Column(name="producto")
    private Producto producto;
    
}
