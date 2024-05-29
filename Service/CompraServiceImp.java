package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.CompraModel;
import com.apiweb.backend.Model.ComprasPorGeneroYSeccion;
import com.apiweb.backend.Model.NumeroDeComprasPorUsuarios;
import com.apiweb.backend.Repository.IComprasRepository;


@Service
public class CompraServiceImp implements ICompraService{
    @Autowired IComprasRepository comprasRepository;
    @Override
    public String guardarCompra(CompraModel compra){
        comprasRepository.save(compra);
        return "La compra con el id "+compra.getId()+
        " fue creada con exito";
    }
    @Override
    public CompraModel actualizarCompra(ObjectId id, CompraModel compra) {
        Optional<CompraModel> existingCompraOpt = comprasRepository.findById(id);
        if (existingCompraOpt.isPresent()) {
            CompraModel existingCompra = existingCompraOpt.get();
            existingCompra.setIdUsuario(compra.getIdUsuario());
            existingCompra.setCostoTotal(compra.getCostoTotal());
            existingCompra.setFechaCompra(compra.getFechaCompra());
            existingCompra.setDetallesCompra(compra.getDetallesCompra());
            existingCompra.setDetallesEnvio(compra.getDetallesEnvio());
            return comprasRepository.save(existingCompra);
        } else {
            throw new RuntimeException("Compra no encontrada con id: " + id);
        }
    }
    @Override
    public CompraModel buscarCompraPorId(ObjectId id){
        Optional<CompraModel> compraEncontrada = comprasRepository.findById(id);
        return compraEncontrada.
        orElseThrow(() -> new RecursoNoEncontradoException("Compra no encontrada con el Id "+ id));
    }
    @Override
    public void eliminarCompra(ObjectId id) {
        if (comprasRepository.existsById(id)) {
            comprasRepository.deleteById(id);
        } else {
            throw new RecursoNoEncontradoException("Compra no encontrada con el ID " + id);
        }
    }
    @Override
    public List<CompraModel> listarCompras() {
        return comprasRepository.findAll();
    }
    @Override
    public List<com.apiweb.backend.Model.ProductoMasVendido> ProductoMasVendido() {
        return comprasRepository.ProductoMasVendido();
    }
    @Override
    public List<com.apiweb.backend.Model.UsuariosConMayorGasto> UsuariosConMayorGasto() {
        return comprasRepository.UsuariosConMayorGasto();
    }
    @Override
    public List<com.apiweb.backend.Model.PromocionesAplicadasEnCompras> PromocionesAplicadasEnCompras() {
        return comprasRepository.PromocionesAplicadasEnCompras();
    }
    @Override
    public List<com.apiweb.backend.Model.UsuariosConMayorVariedad> UsuariosConMayorVariedad(){
        return comprasRepository.UsuariosConMayorVariedad();
    }
    @Override
    public List<ComprasPorGeneroYSeccion> ComprasPorGeneroYSeccion(){
        return comprasRepository.ComprasPorGeneroYSeccion();
    }
    @Override
    public List<com.apiweb.backend.Model.TallasMasVendidasPorSeccion> TallasMasVendidasPorSeccion(){
        return comprasRepository.TallasMasVendidasPorSeccion();
    }
    @Override
    public List<com.apiweb.backend.Model.ProductosConMayorIngresoPorCantidadVendida> ProductosConMayorIngresoPorCantidadVendida(){
        return comprasRepository.ProductosConMayorIngresoPorCantidadVendida();
    }
    @Override
    public List<NumeroDeComprasPorUsuarios> NumeroDeComprasPorUsuarios(){
        return comprasRepository.NumeroDeComprasPorUsuarios();
    }
}