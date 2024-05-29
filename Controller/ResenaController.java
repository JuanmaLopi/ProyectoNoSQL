package com.apiweb.backend.Controller;

import java.util.List;

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
import com.apiweb.backend.Model.ProductosConMasResenas;
import com.apiweb.backend.Model.ResenaModel;
import com.apiweb.backend.Repository.IResenaRepository;
import com.apiweb.backend.Service.IResenaService;

@RestController
@RequestMapping ("/uao/proyecto/resenas")
public class ResenaController {
    @Autowired IResenaService resenaService;
    @Autowired IResenaRepository resenaRepository;
    @PostMapping ("/")
    public ResponseEntity<String> crearResena(@RequestBody ResenaModel resena){
        return new ResponseEntity<String>(resenaService.guardarResena(resena),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResenaModel> actualizarResena(@PathVariable ObjectId id, @RequestBody ResenaModel resena) {
        try {
            ResenaModel resenaActualizada = resenaService.actualizarResena(id, resena);
            return ResponseEntity.ok(resenaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarResenaPorId(@PathVariable ObjectId id) {
        try {
            ResenaModel resena = resenaService.buscarResenaPorId(id);
            return ResponseEntity.ok(resena);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarResena(@PathVariable ObjectId id) {
    try {
        ResenaModel resena = resenaService.buscarResenaPorId(id);
        if (resena == null) {
            throw new RecursoNoEncontradoException("Reseña no encontrada con ID " + id);
        }
        resenaService.eliminarResena(id);
        return ResponseEntity.ok("Reseña eliminada exitosamente");
        } catch (RecursoNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> buscarTodasLasResenas(){
        return ResponseEntity.ok(resenaRepository.findAll());
    }
    @GetMapping("/productoconmasresenas/")
    List<ProductosConMasResenas> ProductoConMasResenas(){
        return resenaService.ProductoConMasResenas();
    }
}
