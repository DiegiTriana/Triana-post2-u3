# tienda-patrones-decorator-facade

Proyecto Unidad 3.
Se implementaron los patrones Decorator y Facade en un proyecto Spring Boot
que extiende el sistema de la tienda virtual con procesamiento modular de ordenes
y un subsistema de notificaciones multicanal.

## Que hace el proyecto

El sistema permite procesar ordenes de compra pasando por una cadena de capas
opcionales (logging, validacion, auditoria) usando el patron Decorator. Ademas,
cuando una compra se completa o falla, se notifica al cliente por email, SMS y push
usando el patron Facade que oculta la complejidad del subsistema de notificaciones.

## Patrones implementados

### Decorator
El problema era que el servicio de ordenes necesitaba capas adicionales de
logging, validacion y auditoria, pero agregar estas funcionalidades directamente
a la clase base la haria muy grande y dificil de mantener. Ademas, no siempre
se necesitan todas las capas al mismo tiempo.

La solucion fue crear decoradores independientes que envuelven el servicio base.
Cada decorador agrega una capa de comportamiento sin modificar las clases
existentes. La cadena se construye en DecoratorConfig de esta forma:
Auditoria -> Validacion -> Logging -> Base

### Facade
El problema era que el controlador de la aplicacion tendria que conocer y coordinar
tres servicios diferentes (EmailService, SMSService, PushService) cada vez que
necesitara notificar al cliente. Eso genera mucho acoplamiento.

La solucion fue crear NotificacionFacade que expone metodos simples como
notificarCompraExitosa() y notificarErrorPago(). El cliente solo llama un metodo
y la facade se encarga de coordinar los tres canales internamente.

## Como ejecutar

Requisitos: Java 17 y Maven 3.8 o superior.

Compilar y empaquetar:
mvn clean package

Ejecutar la aplicacion:
mvn spring-boot:run

Ejecutar los tests:
mvn test

##capturas de pantalla de main, clean y test

![image alt](https://github.com/DiegiTriana/Triana-post2-u3/blob/9f3c0c23465bcb4586b16e7d86daf2500964046e/Captura%20de%20pantalla%201.png)

![image alt](https://github.com/DiegiTriana/Triana-post2-u3/blob/9f3c0c23465bcb4586b16e7d86daf2500964046e/Captura%20de%20pantalla%202.png)

![image alt](https://github.com/DiegiTriana/Triana-post2-u3/blob/9f3c0c23465bcb4586b16e7d86daf2500964046e/captura%20de%20pantalla%203.png)
