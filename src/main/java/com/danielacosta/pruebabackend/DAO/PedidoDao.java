package com.danielacosta.pruebabackend.DAO;

import com.danielacosta.pruebabackend.entity.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDao extends JpaRepository<Pedido, Long>{
    
}