package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.PromocionModel;
import com.apiweb.backend.Repository.IPromocionRepository;

@Service
public class PromocionServiceImp implements IPromocionService{
    @Autowired IPromocionRepository promocionRepository;
    @Override
    public String guardarPromocion(PromocionModel promocion){
        promocionRepository.save(promocion);
        return "La promoción con el id "+promocion.getId()+
        " fue creada con exito";
    }
    @Override
    public PromocionModel actualizarPromocion(ObjectId id, PromocionModel promocion) {
        Optional<PromocionModel> existingPromocionOpt = promocionRepository.findById(id);
        if (existingPromocionOpt.isPresent()) {
            PromocionModel existingPromocion = existingPromocionOpt.get();
            existingPromocion.setDescripcion(promocion.getDescripcion());
            return promocionRepository.save(existingPromocion);
        } else {
            throw new RuntimeException("Promocion no encontrada con id: " + id);
        }
    }
    @Override
    public PromocionModel buscarPromocionPorId(ObjectId id){
        Optional<PromocionModel> promocionEncontrada = promocionRepository.findById(id);
        return promocionEncontrada.
        orElseThrow(() -> new RecursoNoEncontradoException("Promoción no encontrada con el Id "+ id));
    }
    @Override
    public void eliminarPromocion(ObjectId id) {
        if (promocionRepository.existsById(id)) {
            promocionRepository.deleteById(id);
        } else {
            throw new RecursoNoEncontradoException("Resena no encontrada con el ID " + id);
        }
    }
    @Override
    public List<PromocionModel> listarPromociones() {
        return promocionRepository.findAll();
    }
}
