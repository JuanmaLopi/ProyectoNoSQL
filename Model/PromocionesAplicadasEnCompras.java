package com.apiweb.backend.Model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PromocionesAplicadasEnCompras {
    private String id;
    private String idUsuario;
    private Date fechaCompra;
    private Double costoTotal;
    private DetallesCompra detallesCompra;
    private String promociones;
}
