package com.apiweb.backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.UsuariosModel;

public interface IUsuariosService {
    String guardarUsuario(UsuariosModel usuario);
    UsuariosModel buscarUsuarioPorId(ObjectId id);
    UsuariosModel actualizarUsuario(ObjectId id, UsuariosModel usuario);
    void eliminarUsuario(ObjectId id);   
    List<UsuariosModel> listarUsuarios();
}
