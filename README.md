# demo1 — Gestión de flota de reparto (parcial de práctica)

Proyecto de práctica académica para un examen de Spring. Implementa un sistema simple de
gestión de una flota de vehículos de reparto y sus registros de entrega, usando **Spring
Context + Servlets** (sin Spring MVC, sin JPA/Hibernate y sin base de datos: la persistencia
es en memoria).

## Cómo ejecutarlo

```bash
mvn spring-boot:run
```

O alternativamente, ejecutar la clase `Demo1Application` desde el IDE.

## Dónde corre

Por defecto en `http://localhost:8080` (puerto por defecto de Spring Boot; no hay
`server.port` configurado en `application.properties`).

## Endpoints disponibles

- `http://localhost:8080/vehicles` — listar y registrar vehículos (GET/POST).
- `http://localhost:8080/deliveries` — listar y registrar registros de entrega (GET/POST).

## Estructura del proyecto

El proyecto sigue el patrón **Model → Repository → Service → Servlet**:

- `model/` — POJOs planos (`Vehicle`, `DeliveryRecord`), sin anotaciones.
- `repos/` — repositorios (`VehicleRepository`, `DeliveryRecordRepository`) anotados con
  `@Repository`, con una `List` en memoria como almacenamiento.
- `FleetService.java` — capa de lógica de negocio (`@Service`), con las reglas de validación
  para registrar vehículos y registros de entrega.
- Servlets (`VehicleServlet`, `DeliveryRecordServlet`) — capa de presentación con
  `@WebServlet` + `@Component`, expuestos gracias a `@ServletComponentScan` en
  `Demo1Application`.
