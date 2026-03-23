package com.universidad.tienda;

import com.universidad.tienda.decorator.OrdenServicio;
import com.universidad.tienda.facade.NotificacionFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiendaApp implements CommandLineRunner {
    private final OrdenServicio ordenServicio;
    private final NotificacionFacade notificacionFacade;

    public TiendaApp(@Qualifier("ordenCompleto") OrdenServicio ordenServicio,
                     NotificacionFacade notificacionFacade) {
        this.ordenServicio = ordenServicio;
        this.notificacionFacade = notificacionFacade;
    }

    public static void main(String[] args) {
        SpringApplication.run(TiendaApp.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Demo: Decorator + Facade ===\n");

        System.out.println("-- Procesando orden con cadena de decoradores --");
        String resultado = ordenServicio.procesarOrden("ORD-001", 50000.0);
        System.out.println("Resultado final: " + resultado);

        System.out.println("\n-- Notificando compra exitosa via Facade --");
        notificacionFacade.notificarCompraExitosa(
                "cliente@mail.com", "+57-300-0000001", "token-push-abc", "ORD-001");

        System.out.println("\n-- Notificando error de pago via Facade --");
        notificacionFacade.notificarErrorPago(
                "cliente@mail.com", "+57-300-0000001", "ORD-002");
    }
}