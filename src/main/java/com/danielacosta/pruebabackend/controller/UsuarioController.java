package com.danielacosta.pruebabackend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.danielacosta.pruebabackend.entity.Usuario;
import com.danielacosta.pruebabackend.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }


    @GetMapping
    public List<Usuario> getAll(){
        List<Usuario> usuario = StreamSupport
        .stream(usuarioService.findAll().spliterator(), false)
        .collect(Collectors.toList());
        return usuario;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@PathVariable(value = "id") Long usuarioID){
        
        Optional<Usuario> oUsuario = usuarioService.findById(usuarioID);

        if(!oUsuario.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuarioUpdate, @PathVariable(value ="id") Long usuarioId){
        Optional<Usuario> oUsuario = usuarioService.findById(usuarioId);
        
        if(!oUsuario.isPresent()){
            return ResponseEntity.notFound().build();
        }

        oUsuario.get().setDni(usuarioUpdate.getDni());
        oUsuario.get().setNombre(usuarioUpdate.getNombre());
        oUsuario.get().setApellido(usuarioUpdate.getApellido());
        oUsuario.get().setDireccion(usuarioUpdate.getDireccion());

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(oUsuario.get()));

    }
}