package com.danielacosta.pruebabackend.DAO;

import com.danielacosta.pruebabackend.entity.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDao extends JpaRepository<Producto, Long> {

}