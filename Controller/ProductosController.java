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
import com.apiweb.backend.Model.ProductosModel;
import com.apiweb.backend.Repository.IProductosRepository;
import com.apiweb.backend.Service.IProductoService;

@RestController
@RequestMapping ("/uao/proyecto/productos")
public class ProductosController {
    @Autowired IProductoService productoService;
    @Autowired IProductosRepository productosRepository;
    @PostMapping ("/")
    public ResponseEntity<String> crearProducto(@RequestBody ProductosModel producto){
        return new ResponseEntity<String>(productoService.guardarProducto(producto),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductosModel> actualizarProducto(@PathVariable ObjectId id, @RequestBody ProductosModel producto) {
        try {
            ProductosModel productoActualizado = productoService.actualizarProducto(id, producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable ObjectId id) {
        try {
            ProductosModel producto = productoService.buscarProductoPorId(id);
            return ResponseEntity.ok(producto);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable ObjectId id) {
    try {
        ProductosModel producto = productoService.buscarProductoPorId(id);
        if (producto == null) {
            throw new RecursoNoEncontradoException("Producto no encontrado con ID: " + id);
        }
        productoService.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado exitosamente");
        } catch (RecursoNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> buscarTodosLosProductos(){
        return ResponseEntity.ok(productosRepository.findAll());
    } 
}
