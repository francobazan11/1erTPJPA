package com.pruebaSpring.helloSpring.entities;

import com.pruebaSpring.helloSpring.enums.EstadoPedido;
import com.pruebaSpring.helloSpring.enums.TipoEnvio;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codPedido;
    
    @Column(name = "estado")
    private EstadoPedido estado;
    
    @Column(name = "fecha")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "tipoEnvio")
    private TipoEnvio tipoEnvio;
    
    @Column(name = "total")
    private Double total;
    
    @OneToMany(cascade = CascadeType.ALL)
    @Column(name="detallesPedido")
    private List<DetallePedido> detallesPedido;
    
    @OneToOne(cascade = CascadeType.ALL)
    @Column(name="factura")
    private Factura factura;
}
