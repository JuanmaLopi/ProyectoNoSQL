package com.apiweb.backend.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuariosConMayorVariedad {
    private String id;
    private Integer variedadCompras;
    private String usuario;
}
