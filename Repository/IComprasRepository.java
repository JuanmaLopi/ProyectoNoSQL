package com.apiweb.backend.Repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apiweb.backend.Model.CompraModel;
import com.apiweb.backend.Model.ComprasPorGeneroYSeccion;
import com.apiweb.backend.Model.NumeroDeComprasPorUsuarios;
import com.apiweb.backend.Model.ProductoMasVendido;
import com.apiweb.backend.Model.ProductosConMayorIngresoPorCantidadVendida;
import com.apiweb.backend.Model.PromocionesAplicadasEnCompras;
import com.apiweb.backend.Model.UsuariosConMayorGasto;
import com.apiweb.backend.Model.UsuariosConMayorVariedad;

public interface IComprasRepository extends MongoRepository<CompraModel,ObjectId>{
    @Aggregation (pipeline = {
        "{$unwind: '$detallesCompra'}",
        "{$group: {_id: '$detallesCompra.productoId',totalVendidos: { $sum: '$detallesCompra.cantidad' }}}",
        "{$lookup: {from: 'Producto',localField: '_id',foreignField: '_id',as: 'producto'}}",
        "{$project: {producto: { $arrayElemAt: ['$producto.nombre', 0] },totalVendidos: 1}}",
        "{$sort: { totalVendidos: -1 }}"
    })
    List<ProductoMasVendido> ProductoMasVendido();
    @Aggregation (pipeline = {
        "{$group: {_id: '$idUsuario',totalGastado: { $sum: '$costoTotal'}}}",
        "{$lookup: {from: 'Usuario',localField: '_id',foreignField: '_id',as: 'usuario'}}",
        "{$project: {usuario: { $arrayElemAt: ['$usuario.nombre', 0] },totalGastado: 1}}",
        "{$sort: { totalGastado: -1 }}"
    })
    List<UsuariosConMayorGasto> UsuariosConMayorGasto();
    @Aggregation (pipeline = {
        "{$unwind: '$detallesCompra'}",
        "{$lookup: {from: 'Promocion',localField: 'detallesCompra.productoId',foreignField: 'ProductosConPromocion.productoId',as: 'promociones'}}",
        "{$match: { 'promociones': { $ne: [] } }}",
        "{$project: {idUsuario: 1,fechaCompra: 1,costoTotal: 1,'detallesCompra.productoId': 1,'detallesCompra.cantidad': 1,'detallesCompra.valorUnidad': 1,promociones: { $arrayElemAt: ['$promociones.descripcion', 0]}}}"
    })
    List<PromocionesAplicadasEnCompras> PromocionesAplicadasEnCompras();
    @Aggregation (pipeline = {
        "{$unwind: '$detallesCompra'}",
        "{$group: {_id: {usuario: '$idUsuario',producto: '$detallesCompra.productoId'}}}",
        "{$group: {_id: '$_id.usuario',variedadCompras: { $sum: 1 }}}",
        "{$lookup: {from: 'Usuario',localField: '_id',foreignField: '_id',as: 'usuario'}}",
        "{$project: {usuario: { $arrayElemAt: ['$usuario.nombre', 0] },variedadCompras: 1}}",
        "{$sort: { variedadCompras: -1 }}"
    })
    List<UsuariosConMayorVariedad> UsuariosConMayorVariedad();
    @Aggregation (pipeline = {
        "{$lookup: {from: 'Usuario',localField: 'idUsuario',foreignField: '_id',as: 'usuario'}}",
        "{$unwind: '$usuario'}",
        "{$unwind: '$detallesCompra'}",
        "{$lookup: {from: 'Producto',localField: 'detallesCompra.productoId',foreignField: '_id',as: 'producto'}}",
        "{$unwind: '$producto'}",
        "{$group: {_id: {genero: '$usuario.genero',seccion: '$producto.seccion'},totalCompras: { $sum: 1 }}}",
        "{$project: {genero: '$_id.genero',seccion: '$_id.seccion',totalCompras: 1}}",
        "{$sort: { totalCompras: -1 }}"
    })
    List<ComprasPorGeneroYSeccion> ComprasPorGeneroYSeccion();
    @Aggregation (pipeline = {
        "{$unwind: '$detallesCompra'}",
        "{$lookup: {from: 'Producto',localField: 'detallesCompra.productoId',foreignField: '_id',as: 'producto'}}",
        "{$unwind: '$producto'}",
        "{$unwind: '$producto.tallas'}",
        "{$group: {_id: {seccion: '$producto.seccion',talla: '$producto.tallas.talla'},totalVendidos: { $sum: '$detallesCompra.cantidad' }}}",
        "{$project: {seccion: '$_id.seccion',talla: '$_id.talla',totalVendidos: 1}}",
        "{$sort: { totalVendidos: -1 }}"
    })
    List<com.apiweb.backend.Model.TallasMasVendidasPorSeccion> TallasMasVendidasPorSeccion();
    @Aggregation (pipeline = {
        "{$unwind: '$detallesCompra'}",
        "{$lookup: {from: 'Producto',localField: 'detallesCompra.productoId',foreignField: '_id',as: 'producto'}}",
        "{$unwind: '$producto'}",
        "{$group: {_id: '$producto._id',nombre: { $first: '$producto.nombre' },totalIngresos: { $sum: { $multiply: ['$detallesCompra.cantidad', '$detallesCompra.valorUnidad'] } },totalVentas: { $sum: '$detallesCompra.cantidad' }}}",
        "{$lookup: {from: 'Resena',localField: '_id',foreignField: 'idProducto',as: 'resenas'}}",
        "{$unwind: '$resenas'}",
        "{$group: {_id: '$_id',nombre: { $first: '$nombre' },totalIngresos: { $first: '$totalIngresos' },totalVentas: { $first: '$totalVentas' }}}",
        "{$sort: { totalIngresos: -1}}"
    })
    List<ProductosConMayorIngresoPorCantidadVendida> ProductosConMayorIngresoPorCantidadVendida();
    @Aggregation (pipeline = {
        "{$group: {_id: '$idUsuario',totalCompras: { $sum: 1 }}}",
        "{$lookup: {from: 'Usuario',localField: '_id',foreignField: '_id',as: 'usuario'}}",
        "{$project: {usuario: { $arrayElemAt: ['$usuario.nombre', 0] },totalCompras: 1}}",
        "{$sort: { totalCompras: -1 }}"
    })
    List<NumeroDeComprasPorUsuarios> NumeroDeComprasPorUsuarios();
}
