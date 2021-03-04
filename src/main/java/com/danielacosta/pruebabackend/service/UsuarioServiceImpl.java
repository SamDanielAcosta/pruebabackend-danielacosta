package com.danielacosta.pruebabackend.service;

import java.util.Optional;



import com.danielacosta.pruebabackend.DAO.UsuarioDao;
import com.danielacosta.pruebabackend.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly=true)
    public Iterable<Usuario> findAll() {
        
        return usuarioDao.findAll();
    }
    
    @Override
    @Transactional(readOnly=true)
    public Optional<Usuario> findById(Long id) {
        
        return usuarioDao.findById(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        
        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        usuarioDao.deleteById(id);

    }

}