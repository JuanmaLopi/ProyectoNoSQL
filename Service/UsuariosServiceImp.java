package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.UsuariosModel;
import com.apiweb.backend.Repository.IUsuariosRepository;

@Service
public class UsuariosServiceImp implements IUsuariosService{
    @Autowired IUsuariosRepository usuariosRepository;
    @Override
    public String guardarUsuario (UsuariosModel usuario){
        usuariosRepository.save(usuario);
        return "El usuario con el id: "+usuario.getId()+
        " fue Creado con exito";
    }
    @Override
    public UsuariosModel actualizarUsuario(ObjectId id, UsuariosModel usuario) {
        Optional<UsuariosModel> existingUsuarioOpt = usuariosRepository.findById(id);
        if (existingUsuarioOpt.isPresent()) {
            UsuariosModel existingUsuario = existingUsuarioOpt.get();
            existingUsuario.setNombre(usuario.getNombre());
            existingUsuario.setCorreo(usuario.getCorreo());
            existingUsuario.setEdad(usuario.getEdad());
            existingUsuario.setGenero(usuario.getGenero());
            existingUsuario.setTelefono(usuario.getTelefono());
            existingUsuario.setRegistro(usuario.getRegistro());
            return usuariosRepository.save(existingUsuario);
        } else {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
    }
    @Override
    public UsuariosModel buscarUsuarioPorId (ObjectId id){
        Optional<UsuariosModel> usuarioEncontrado = usuariosRepository.findById(id);
        return usuarioEncontrado.
        orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con el Id "+ id));
    }
    @Override
    public void eliminarUsuario(ObjectId id) {
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
        } else {
            throw new RecursoNoEncontradoException("Usuario no encontrada con el ID " + id);
        }
    }
    @Override
    public List<UsuariosModel> listarUsuarios() {
        return usuariosRepository.findAll();
    }
}
