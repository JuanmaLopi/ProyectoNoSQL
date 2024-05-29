package com.apiweb.backend.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuariosConMayorGasto {
    private String id;
    private Double totalGastado;
    private String usuario;
}
