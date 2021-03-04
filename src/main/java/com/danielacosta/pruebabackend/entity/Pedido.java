package com.danielacosta.pruebabackend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable{
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = true)
    private String observacion;

    @Column(length = 20, nullable = false)
    private String estado;

    @Column(name = "fecha_pedido")
	@Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;

    @JsonIgnoreProperties(value={"pedidos", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;


    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
	private List<PedidoLinea> lineas;

    public Pedido() {
		this.lineas = new ArrayList<>();
	}

    // Generar la fecha del pedido
	@PrePersist
	public void prePersist() {
		this.fechaPedido = new Date();
	}

    public Double calcularSubTotal(){
        Double subTotal = 0.00;

        for(PedidoLinea linea: lineas){
            subTotal += linea.calcularValorLinea();
        }

        return subTotal;
    }

    public Double calcularIva(){
        Double porcentajeIva = 0.19;
        Double iva = this.calcularSubTotal() * porcentajeIva;
        return iva;
    }

    public Double calcularTotal(){
        Double aux = this.calcularSubTotal() + this.calcularIva() + this.calcularValorDomicilio();
        Double total = 0.00;
		
        if(this.estado.equals("cancelado1")) {
			total = aux * 0.10;
		}else if(this.estado.equals("cancelado2")) {
			total = 0.00;
		}
        
        return total;
    }


    public Double calcularValorDomicilio() {
		Double valorDomicilio = 0.00;
		Double subTotal = this.calcularSubTotal();
		
        if(subTotal > 70000 && subTotal < 100000) {
			valorDomicilio = 7000.00;
		}else if(subTotal> 100000) {
			valorDomicilio = 0.00;
		}
		
		return valorDomicilio;
	}

    private static final long serialVersionUID = 495551477344911789L;
    
}