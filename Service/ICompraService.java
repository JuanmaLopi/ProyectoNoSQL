package com.apiweb.backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.CompraModel;
import com.apiweb.backend.Model.ComprasPorGeneroYSeccion;
import com.apiweb.backend.Model.NumeroDeComprasPorUsuarios;
import com.apiweb.backend.Model.ProductoMasVendido;
import com.apiweb.backend.Model.ProductosConMayorIngresoPorCantidadVendida;
import com.apiweb.backend.Model.PromocionesAplicadasEnCompras;
import com.apiweb.backend.Model.UsuariosConMayorGasto;
import com.apiweb.backend.Model.UsuariosConMayorVariedad;

public interface ICompraService {
    String guardarCompra(CompraModel compra);
    CompraModel buscarCompraPorId(ObjectId id);
    CompraModel actualizarCompra(ObjectId id, CompraModel compra);
    void eliminarCompra(ObjectId id); 
    List<CompraModel> listarCompras();
    List<ProductoMasVendido> ProductoMasVendido();
    List<UsuariosConMayorGasto> UsuariosConMayorGasto();
    List<PromocionesAplicadasEnCompras> PromocionesAplicadasEnCompras();
    List<UsuariosConMayorVariedad> UsuariosConMayorVariedad();
    List<ComprasPorGeneroYSeccion> ComprasPorGeneroYSeccion();
    List<com.apiweb.backend.Model.TallasMasVendidasPorSeccion> TallasMasVendidasPorSeccion();
    List<ProductosConMayorIngresoPorCantidadVendida> ProductosConMayorIngresoPorCantidadVendida();
    List<NumeroDeComprasPorUsuarios> NumeroDeComprasPorUsuarios();
}
