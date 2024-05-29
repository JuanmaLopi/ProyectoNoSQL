package com.apiweb.backend.Service;

import java.util.List;

import com.apiweb.backend.Model.MunicipioModel;

public interface IMunicipioService {
    String guardarMunicipio(MunicipioModel municipio);
    MunicipioModel buscarMunicipioPorId(Integer id);
    MunicipioModel actualizarMunicipio(Integer id, MunicipioModel municipio);
    void eliminarMunicipio(Integer id); 
    List<MunicipioModel> listarMunicipios();
}
