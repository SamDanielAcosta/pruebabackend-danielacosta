package com.danielacosta.pruebabackend.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.danielacosta.pruebabackend.entity.Pedido;
import com.danielacosta.pruebabackend.entity.PedidoLinea;
import com.danielacosta.pruebabackend.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;


    @GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable(value = "id") Long pedidoId) {
		Optional<Pedido> oPedido = pedidoService.findById(pedidoId);

		if (!oPedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(oPedido);
	}


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Pedido pedidoDetalles, @PathVariable(value = "id") Long pedidoId){
        
        Optional<Pedido> oPedido = pedidoService.findById(pedidoId);
        
        if (!oPedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}
        
        List<PedidoLinea> lineas = pedidoDetalles.getLineas();

        Double precioAnterior = oPedido.get().calcularSubTotal();
        
        //Calcular el nuevo subtotal del pedido
        Double nuevoSubTotal = 0.00;

        for(PedidoLinea linea: lineas){
            nuevoSubTotal += linea.calcularValorLinea();
        }

        //Calcular hace cuanto se realizo el pedido
        Long horasPedido = this.calcularHorasPedido(oPedido.get().getFechaPedido());

        //Calcular si el pedido fue hace menos de 5 horas
        if(horasPedido > 5){
            return ResponseEntity.badRequest().build();
        }

        //Verificar si el sub total de la factura es mayor o igual que el anterior
        if(nuevoSubTotal < precioAnterior){
            return ResponseEntity.badRequest().build();
        }

        //Altualizar nuevos datos del pedido
        oPedido.get().setObservacion(pedidoDetalles.getObservacion());
        oPedido.get().setLineas(pedidoDetalles.getLineas());


        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(oPedido.get()));      
    }
// Cancelar Pedido
@PutMapping("/cancelar/{id}")
public ResponseEntity<?> cancel(@PathVariable(value = "id") Long orderId) {
    Optional<Pedido> oPedido = pedidoService.findById(orderId);

    if (!oPedido.isPresent()) {
        return ResponseEntity.notFound().build();
    }
    
    // Verificacion de las 12 horas para cancelar
    Long hoursAgo = this.calcularHorasPedido(oPedido.get().getFechaPedido());
    
    if (hoursAgo > 12) {
        oPedido.get().setEstado("cancelado1");			
    } else {
        oPedido.get().setEstado("cancelado2");
    }
    
    return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(oPedido.get()));
}
    //Metodo para calcular hace cuantas horas el usuario o cliente realizo su pedido
    public Long calcularHorasPedido(Date fechaPedido){

        Calendar fechaActual = Calendar.getInstance();

        Long diferenciaMilisegundos = Math.abs(fechaActual.getTimeInMillis() - fechaPedido.getTime());

        Long horas = TimeUnit.MILLISECONDS.toHours(diferenciaMilisegundos) + 1;
        
        return horas;
    }

}