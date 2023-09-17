package com.pruebaSpring.helloSpring.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codCliente;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "apellido")
    private String apellido;
    
    @Column(name = "apellido")
    private String telefono;
    
    @Column(name = "email")
    private String email;
    
    @OneToMany(cascade = CascadeType.ALL)
    @Column(name="domicilios")
    private List<Domicilio> domicilios;
    
    @OneToMany
    @Column(name="pedidos")
    private List<Pedido> pedidos;
    
}
