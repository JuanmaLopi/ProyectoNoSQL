package com.apiweb.backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.ProductosModel;

public interface IProductoService {
    String guardarProducto(ProductosModel producto);
    ProductosModel buscarProductoPorId(ObjectId id);
    ProductosModel actualizarProducto(ObjectId id, ProductosModel producto);
    void eliminarProducto(ObjectId id); 
    List<ProductosModel> listarProductos();
}
