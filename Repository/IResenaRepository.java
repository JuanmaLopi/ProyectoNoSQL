package com.apiweb.backend.Repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apiweb.backend.Model.ProductosConMasResenas;
import com.apiweb.backend.Model.ResenaModel;

public interface IResenaRepository extends MongoRepository<ResenaModel,ObjectId>{
    @Aggregation (pipeline = {
        "{$group: {_id: '$idProducto', totalResenas: { $sum: 1 }}}",
        "{$lookup: {from: 'Producto',localField: '_id',foreignField: '_id',as: 'producto'}}",
        "{$project: {producto: { $arrayElemAt: ['$producto.nombre', 0] },totalResenas: 1}}",
        "{$sort: { totalResenas: -1 }}"
    })
    List<ProductosConMasResenas> ProductoConMasResenas();
}
