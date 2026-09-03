package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

// @SpringBootApplication: marca esta clase como el punto de arranque de la app Spring Boot.
// Combina @Configuration + @EnableAutoConfiguration + @ComponentScan, así que Spring
// escanea el paquete com.example.demo (y subpaquetes) buscando @Component, @Service, @Repository, etc.
@SpringBootApplication
// @ServletComponentScan: habilita el escaneo de Servlets anotados con @WebServlet (como
// VehicleServlet y DeliveryRecordServlet) para que se registren automáticamente en el
// contenedor embebido (Tomcat), sin necesidad de un web.xml.
@ServletComponentScan
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

}
