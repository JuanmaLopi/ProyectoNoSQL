package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;

import com.apiweb.backend.Model.ProductosModel;
import com.apiweb.backend.Repository.IProductosRepository;

@Service
public class ProductoServiceImp implements IProductoService{
    @Autowired IProductosRepository productosRepository;
    @Override
    public String guardarProducto (ProductosModel producto){
        productosRepository.save(producto);
        return "El producto con el id: "+producto.getId()+
        " fue Creado con exito";
    }
    @Override
    public ProductosModel actualizarProducto(ObjectId id, ProductosModel producto) {
        Optional<ProductosModel> existingProductoOpt = productosRepository.findById(id);
        if (existingProductoOpt.isPresent()) {
            ProductosModel existingProducto = existingProductoOpt.get();
            existingProducto.setPrecio(producto.getPrecio());
            existingProducto.setImagen(producto.getImagen());
            existingProducto.setCantidad(producto.getCantidad());
            existingProducto.setTallas(producto.getTallas());
            return productosRepository.save(existingProducto);
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }
    @Override
    public ProductosModel buscarProductoPorId(ObjectId id){
        Optional<ProductosModel> productoEncontrado = productosRepository.findById(id);
        return productoEncontrado.
        orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con el Id "+ id));
    }
    @Override
    public void eliminarProducto(ObjectId id) {
        if (productosRepository.existsById(id)) {
            productosRepository.deleteById(id);
        } else {
            throw new RecursoNoEncontradoException("Producto no encontrada con el ID " + id);
        }
    }
    @Override
    public List<ProductosModel> listarProductos() {
        return productosRepository.findAll();
    }
}
