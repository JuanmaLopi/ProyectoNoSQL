package com.apiweb.backend.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document ("Compra")
@TypeAlias ("Compras")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CompraModel {
    @Id
    private ObjectId id;
    private ObjectId idUsuario;
    private Date fechaCompra;
    private Double costoTotal;
    private List<DetallesCompra> detallesCompra = new ArrayList<>();
    private DetallesEnvio detallesEnvio;
}
