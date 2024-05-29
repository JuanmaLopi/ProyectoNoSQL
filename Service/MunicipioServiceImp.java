package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.MunicipioModel;
import com.apiweb.backend.Repository.IMunicipioRepository;

@Service
public class MunicipioServiceImp implements IMunicipioService {
    @Autowired IMunicipioRepository municipioRepository;
    @Override
    public String guardarMunicipio (MunicipioModel municipio){
        municipioRepository.save(municipio);
        return "El municipio con el id: "+municipio.getId()+
        " fue creado con exito";
    }
    @Override
    public MunicipioModel actualizarMunicipio(Integer id, MunicipioModel municipio) {
        Optional<MunicipioModel> existingMunicipioOpt = municipioRepository.findById(id);
        if (existingMunicipioOpt.isPresent()) {
            MunicipioModel existingMunicipio = existingMunicipioOpt.get();
            existingMunicipio.setNombre(municipio.getNombre());
            return municipioRepository.save(existingMunicipio);
        } else {
            throw new RuntimeException("Municipio no encontrado con id: " + id);
        }
    }
    @Override
    public MunicipioModel buscarMunicipioPorId(Integer id){
        Optional<MunicipioModel> municipioEncontrado = municipioRepository.findById(id);
        return municipioEncontrado.
        orElseThrow(() -> new RecursoNoEncontradoException("Municipio no encontrado con el Id "+ id));
    }
    @Override
    public void eliminarMunicipio(Integer id) {
        if (municipioRepository.existsById(id)) {
            municipioRepository.deleteById(id);
        } else {
            throw new RecursoNoEncontradoException("Usuario no encontrada con el ID " + id);
        }
    }
    @Override
    public List<MunicipioModel> listarMunicipios() {
        return municipioRepository.findAll();
    }
}
