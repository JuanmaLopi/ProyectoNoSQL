package com.apiweb.backend.Model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.apiweb.backend.Model.ENUM.Genero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document ("Usuario")
@TypeAlias ("Usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuariosModel {
    @Id
    private ObjectId id;
    private String nombre;
    private String correo;
    private Integer telefono;
    private Genero genero;
    private Integer edad;
    private List <RegistroUsuarios> Registro = new ArrayList<>();
}
