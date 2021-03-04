package com.danielacosta.pruebabackend.service;

import java.util.Optional;

import com.danielacosta.pruebabackend.DAO.PedidoDao;
import com.danielacosta.pruebabackend.entity.Pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoDao pedidoDao;

    @Override
    @Transactional(readOnly = true)
    public Optional<Pedido> findById(Long id) {
        return pedidoDao.findById(id);
    }

    @Override
    @Transactional
    public Pedido save(Pedido pedido) {
        return pedidoDao.save(pedido);
    }
    
}