package com.danielacosta.pruebabackend.service;

import java.util.Optional;

import com.danielacosta.pruebabackend.entity.Producto;

public interface ProductoService {
    public Iterable<Producto>findAll();	
	
	public Optional<Producto> findById(Long id);
	
	public Producto save(Producto producto);
	
	public void deleteById(Long id);
}