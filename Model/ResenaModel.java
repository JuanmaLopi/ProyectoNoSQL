package com.apiweb.backend.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.apiweb.backend.Model.ENUM.Valoraciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document ("Resena")
@TypeAlias ("Resenas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResenaModel {
    @Id
    private ObjectId id;
    private ObjectId idUsuario;
    private ObjectId idProducto;
    private String comentarios;
    private Valoraciones valoraciones;
}
