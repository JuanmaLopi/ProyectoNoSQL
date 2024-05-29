package com.apiweb.backend.Controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.UsuariosModel;
import com.apiweb.backend.Repository.IUsuariosRepository;
import com.apiweb.backend.Service.IUsuariosService;

@RestController
@RequestMapping ("/uao/proyecto/usuarios")
public class UsuariosController {
    @Autowired IUsuariosService usuariosService;
    @Autowired IUsuariosRepository usuariosRepository;
    @PostMapping ("/")
    public ResponseEntity<String> crearUsuario(@RequestBody UsuariosModel usuario){
        return new ResponseEntity<String>(usuariosService.guardarUsuario(usuario),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuariosModel> actualizarUsuario(@PathVariable ObjectId id, @RequestBody UsuariosModel usuarios) {
        try {
            UsuariosModel usuarioActualizado = usuariosService.actualizarUsuario(id, usuarios);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable ObjectId id) {
        try {
            UsuariosModel usuario = usuariosService.buscarUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable ObjectId id) {
    try {
        UsuariosModel usuario = usuariosService.buscarUsuarioPorId(id);
        if (usuario == null) {
            throw new RecursoNoEncontradoException("Usuario no encontrado con ID: " + id);
        }
        usuariosService.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (RecursoNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> buscarTodosLosUsuarios(){
        return ResponseEntity.ok(usuariosRepository.findAll());
    }
}
