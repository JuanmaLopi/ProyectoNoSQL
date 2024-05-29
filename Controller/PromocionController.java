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
import com.apiweb.backend.Model.PromocionModel;
import com.apiweb.backend.Repository.IPromocionRepository;
import com.apiweb.backend.Service.IPromocionService;

@RestController
@RequestMapping ("/uao/proyecto/promociones")
public class PromocionController {
    @Autowired IPromocionService promocionService;
    @Autowired IPromocionRepository promocionRepository;
    @PostMapping ("/")
    public ResponseEntity<String> crearPromocion(@RequestBody PromocionModel promocion){
        return new ResponseEntity<String>(promocionService.guardarPromocion(promocion),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PromocionModel> actualizarPromocion(@PathVariable ObjectId id, @RequestBody PromocionModel promocion) {
        try {
            PromocionModel promocionActualizada = promocionService.actualizarPromocion(id, promocion);
            return ResponseEntity.ok(promocionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPromocionPorId(@PathVariable ObjectId id) {
        try {
            PromocionModel promocion = promocionService.buscarPromocionPorId(id);
            return ResponseEntity.ok(promocion);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPromocion(@PathVariable ObjectId id) {
    try {
        PromocionModel promocion = promocionService.buscarPromocionPorId(id);
        if (promocion == null) {
            throw new RecursoNoEncontradoException("Promoción no encontrada con ID " + id);
        }
        promocionService.eliminarPromocion(id);
        return ResponseEntity.ok("Promoción eliminada exitosamente");
        } catch (RecursoNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> buscarTodasLasPromociones(){
        return ResponseEntity.ok(promocionRepository.findAll());
    }
}
