package com.danielacosta.pruebabackend.service;

import java.util.Optional;

import com.danielacosta.pruebabackend.entity.Pedido;

public interface PedidoService {
    
    public Optional<Pedido> findById(Long id);
	
	public Pedido save(Pedido pedidop);
	
}