package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.ProductosConMasResenas;
import com.apiweb.backend.Model.ResenaModel;
import com.apiweb.backend.Repository.IResenaRepository;

@Service
public class ResenaServiceImp implements IResenaService {
    @Autowired IResenaRepository resenaRepository;
    @Override
    public String guardarResena(ResenaModel resena){
        resenaRepository.save(resena);
        return "La reseña con el id "+resena.getId()+
        " fue creada con exito";
    }
    @Override
    public ResenaModel actualizarResena(ObjectId id, ResenaModel resena) {
        Optional<ResenaModel> existingResenaOpt = resenaRepository.findById(id);
        if (existingResenaOpt.isPresent()) {
            ResenaModel existingResena = existingResenaOpt.get();
            existingResena.setComentarios(resena.getComentarios());;
            existingResena.setValoraciones(resena.getValoraciones());
            return resenaRepository.save(existingResena);
        } else {
            throw new RuntimeException("Reseña no encontrada con id: " + id);
        }
    }
    @Override
    public ResenaModel buscarResenaPorId(ObjectId id){
        Optional<ResenaModel> resenaEncontrada = resenaRepository.findById(id);
        return resenaEncontrada.
        orElseThrow(() -> new RecursoNoEncontradoException("Reseña no encontrada con el Id "+ id));
    }
    @Override
    public void eliminarResena(ObjectId id) {
        if (resenaRepository.existsById(id)) {
            resenaRepository.deleteById(id);
        } else {
            throw new RecursoNoEncontradoException("Resena no encontrada con el ID " + id);
        }
    }
    @Override
    public List<ResenaModel> listarResenas() {
        return resenaRepository.findAll();
    }
    @Override
    public List<ProductosConMasResenas> ProductoConMasResenas() {
        return resenaRepository.ProductoConMasResenas();
    }
}
