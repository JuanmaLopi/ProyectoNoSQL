package com.apiweb.backend.Controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.CompraModel;
import com.apiweb.backend.Model.ComprasPorGeneroYSeccion;
import com.apiweb.backend.Model.NumeroDeComprasPorUsuarios;
import com.apiweb.backend.Model.ProductoMasVendido;
import com.apiweb.backend.Model.ProductosConMayorIngresoPorCantidadVendida;
import com.apiweb.backend.Model.PromocionesAplicadasEnCompras;
import com.apiweb.backend.Model.TallasMasVendidasPorSeccion;
import com.apiweb.backend.Model.UsuariosConMayorGasto;
import com.apiweb.backend.Model.UsuariosConMayorVariedad;
import com.apiweb.backend.Repository.IComprasRepository;
import com.apiweb.backend.Service.ICompraService;


@RestController
@RequestMapping ("/uao/proyecto/compras")
public class CompraController {
    @Autowired ICompraService compraService;
    @Autowired IComprasRepository comprasRepository;
    @PostMapping ("/")
    public ResponseEntity<String> crearCompra(@RequestBody CompraModel compra){
        return new ResponseEntity<String>(compraService.guardarCompra(compra),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CompraModel> actualizarCompra(@PathVariable ObjectId id, @RequestBody CompraModel compra) {
        try {
            CompraModel compraActualizada = compraService.actualizarCompra(id, compra);
            return ResponseEntity.ok(compraActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCompraPorId(@PathVariable ObjectId id) {
        try {
            CompraModel compra = compraService.buscarCompraPorId(id);
            return ResponseEntity.ok(compra);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCompra(@PathVariable ObjectId id) {
    try {
        CompraModel compra = compraService.buscarCompraPorId(id);
        if (compra == null) {
            throw new RecursoNoEncontradoException("Compra no encontrada con ID " + id);
        }
        compraService.eliminarCompra(id);
        return ResponseEntity.ok("Compra eliminada exitosamente");
        } catch (RecursoNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping ("/")
    public ResponseEntity<List<CompraModel>> listarCompras(){
        List<CompraModel> compras = compraService.listarCompras();
        return new ResponseEntity<List<CompraModel>>(compras, HttpStatus.OK);
    }
    @GetMapping("/productomasvendido/")
    List<ProductoMasVendido> ProductoMasVendido(){
        return compraService.ProductoMasVendido();
    }
    @GetMapping("/usuariosconmayorgasto/")
    List<UsuariosConMayorGasto> UsuariosConMayorGasto(){
        return compraService.UsuariosConMayorGasto();
    }
    @GetMapping("/promocionesaplicadasencompras/")
    List<PromocionesAplicadasEnCompras> PromocionesAplicadasEnCompras(){
        return compraService.PromocionesAplicadasEnCompras();
    }
    @GetMapping("/usuariosconmayorvariedad/")
    List<UsuariosConMayorVariedad> UsuariosConMayorVariedad(){
        return compraService.UsuariosConMayorVariedad();
    }
    @GetMapping("/comprasporgeneroyseccion/")
    List<ComprasPorGeneroYSeccion> ComprasPorGeneroYSeccion(){
        return compraService.ComprasPorGeneroYSeccion();
    }
    @GetMapping("/tallasmasvendidasporseccion/")
    List<TallasMasVendidasPorSeccion> TallasMasVendidasPorSeccion(){
        return compraService.TallasMasVendidasPorSeccion();
    }
    @GetMapping("/productosconmayoringresoporcantidadvendida/")
    List<ProductosConMayorIngresoPorCantidadVendida> ProductosConMayorIngresoPorCantidadVendida(){
        return compraService.ProductosConMayorIngresoPorCantidadVendida();
    }
    @GetMapping("/numerodecomprasporusuario/")
    List<NumeroDeComprasPorUsuarios> NumeroDeComprasPorUsuarios(){
        return compraService.NumeroDeComprasPorUsuarios();
    }
}
