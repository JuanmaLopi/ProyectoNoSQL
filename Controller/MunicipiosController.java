package com.apiweb.backend.Controller;

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
import com.apiweb.backend.Model.MunicipioModel;
import com.apiweb.backend.Repository.IMunicipioRepository;
import com.apiweb.backend.Service.IMunicipioService;

@RestController
@RequestMapping ("/uao/proyecto/municipios")
public class MunicipiosController {
    @Autowired IMunicipioService municipioService;
    @Autowired IMunicipioRepository municipioRepository;
    @PostMapping ("/")
    public ResponseEntity<String> crearMunicipio(@RequestBody MunicipioModel municipio){
        return new ResponseEntity<String>(municipioService.guardarMunicipio(municipio),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MunicipioModel> actualizarMunicipio(@PathVariable Integer id, @RequestBody MunicipioModel municipio) {
        try {
            MunicipioModel municipioActualizado = municipioService.actualizarMunicipio(id, municipio);
            return ResponseEntity.ok(municipioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarMunicipioPorId(@PathVariable Integer id) {
        try {
            MunicipioModel municipio = municipioService.buscarMunicipioPorId(id);
            return ResponseEntity.ok(municipio);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMunicipio(@PathVariable Integer id) {
    try {
        MunicipioModel municipio = municipioService.buscarMunicipioPorId(id);
        if (municipio == null) {
            throw new RecursoNoEncontradoException("Municipio no encontrado con ID: " + id);
        }
        municipioService.eliminarMunicipio(id);
        return ResponseEntity.ok("Municipio eliminado exitosamente");
        } catch (RecursoNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> buscarTodosLosMunicipios(){
        return ResponseEntity.ok(municipioRepository.findAll());
    } 
}
