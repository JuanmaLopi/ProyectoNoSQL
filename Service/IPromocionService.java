package com.apiweb.backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.PromocionModel;

public interface IPromocionService {
    String guardarPromocion(PromocionModel promocion);
    PromocionModel buscarPromocionPorId(ObjectId id);
    PromocionModel actualizarPromocion(ObjectId id, PromocionModel promocion);
    void eliminarPromocion(ObjectId id); 
    List<PromocionModel> listarPromociones();
}
