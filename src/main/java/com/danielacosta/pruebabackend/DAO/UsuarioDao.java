package com.danielacosta.pruebabackend.DAO;

import com.danielacosta.pruebabackend.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository <Usuario, Long>{
    
}