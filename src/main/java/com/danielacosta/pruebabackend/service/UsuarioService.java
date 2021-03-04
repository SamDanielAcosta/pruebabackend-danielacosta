package com.danielacosta.pruebabackend.service;

import java.util.Optional;

import com.danielacosta.pruebabackend.entity.Usuario;


public interface UsuarioService {
    public Iterable<Usuario> findAll();	
	
	public Optional<Usuario> findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void deleteById(Long id);
}