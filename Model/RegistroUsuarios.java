package com.apiweb.backend.Model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.apiweb.backend.Model.ENUM.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegistroUsuarios {
    private ObjectId idRegistro;
    private String username;
    private String password;
    private Rol rol;
}
