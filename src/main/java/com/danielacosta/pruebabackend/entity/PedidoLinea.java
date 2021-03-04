package com.danielacosta.pruebabackend.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido_lineas")
public class PedidoLinea implements Serializable{

    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private Integer cantidad;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	private Producto producto;

    
	public Double calcularValorLinea() {         
		return cantidad.doubleValue() * producto.getPrecio();
	}

    private static final long serialVersionUID = 3061572546838258492L;
    
}