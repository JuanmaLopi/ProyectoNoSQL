package com.apiweb.backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.ProductosConMasResenas;
import com.apiweb.backend.Model.ResenaModel;

public interface IResenaService {
    String guardarResena(ResenaModel resena);
    ResenaModel buscarResenaPorId(ObjectId id);
    ResenaModel actualizarResena(ObjectId id, ResenaModel resena);
    void eliminarResena(ObjectId id); 
    List<ResenaModel> listarResenas();
    List<ProductosConMasResenas> ProductoConMasResenas();
}
