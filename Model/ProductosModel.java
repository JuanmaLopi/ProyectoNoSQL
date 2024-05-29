package com.apiweb.backend.Model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.apiweb.backend.Model.ENUM.Atuendos;
import com.apiweb.backend.Model.ENUM.Seccion;
import com.apiweb.backend.Model.ENUM.Tipo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document ("Producto")
@TypeAlias ("Productos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductosModel {
    @Id
    private ObjectId id;
    private Double precio;
    private String nombre;
    private String descripcion;
    private String imagen;
    private Tipo tipo;
    private Integer cantidad;
    private Seccion seccion;
    private Atuendos atuendos;
    private List <TallasProductos> tallas = new ArrayList<>();
    private Boolean esPaquete;
    private List <ArticulosProductos> articulos = new ArrayList<>();
}
