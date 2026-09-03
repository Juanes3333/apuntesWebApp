package com.example.demo;

import com.example.demo.model.Vehicle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// @WebServlet: anotación de la API de Servlets (no de Spring) que registra esta clase como
// Servlet y la mapea a la ruta "/vehicles". Gracias a @ServletComponentScan en
// Demo1Application, el contenedor embebido (Tomcat) la descubre y la registra automáticamente.
@WebServlet("/vehicles")
// @Component: estereotipo genérico de Spring. Lo necesitamos además de @WebServlet porque
// queremos que el propio Servlet sea un bean administrado por el contenedor de Spring, para
// poder inyectarle FleetService con @Autowired en vez de hacer "new FleetService()".
@Component
public class VehicleServlet extends HttpServlet {

    // @Autowired de campo: Spring inyecta el único bean @Service (FleetService) disponible.
    // Así el Servlet nunca instancia el Service ni los Repository directamente.
    @Autowired
    private FleetService fleetService;

    // GET /vehicles: lista todos los vehículos registrados en una tabla HTML simple.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        List<Vehicle> vehicles = fleetService.getAllVehicles();

        out.println("<html><head><title>Vehicles</title></head><body>");
        out.println("<h1>Vehicles</h1>");
        out.println("<table border='1' cellpadding='5'>");
        out.println("<tr><th>ID</th><th>Name</th><th>Plate Code</th><th>Vehicle Type</th>"
                + "<th>Driver</th><th>Registration Date</th><th>Status</th></tr>");
        for (Vehicle vehicle : vehicles) {
            out.println("<tr>");
            out.println("<td>" + vehicle.getId() + "</td>");
            out.println("<td>" + vehicle.getName() + "</td>");
            out.println("<td>" + vehicle.getPlateCode() + "</td>");
            out.println("<td>" + vehicle.getVehicleType() + "</td>");
            out.println("<td>" + vehicle.getDriver() + "</td>");
            out.println("<td>" + vehicle.getRegistrationDate() + "</td>");
            out.println("<td>" + vehicle.getStatus() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<form method='post' action='vehicles'>");
        out.println("Name: <input type='text' name='name'><br>");
        out.println("Plate Code: <input type='text' name='plateCode'><br>");
        out.println("Vehicle Type: <input type='text' name='vehicleType'><br>");
        out.println("Driver: <input type='text' name='driver'><br>");
        out.println("Registration Date: <input type='text' name='registrationDate'><br>");
        out.println("Status: <input type='text' name='status'><br>");
        out.println("<input type='submit' value='Register vehicle'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    // POST /vehicles: lee los parámetros del formulario, arma un Vehicle y delega la
    // validación/registro en fleetService.registerVehicle (las 7 reglas de negocio viven ahí).
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String plateCode = req.getParameter("plateCode");
        String vehicleType = req.getParameter("vehicleType");
        String driver = req.getParameter("driver");
        String registrationDate = req.getParameter("registrationDate");
        String status = req.getParameter("status");

        // El id no viene del formulario: se genera aquí en base al tamaño de la lista actual,
        // ya que el repositorio en memoria no genera ids automáticamente.
        int newId = fleetService.getAllVehicles().size() + 1;
        Vehicle vehicle = new Vehicle(newId, name, plateCode, vehicleType, driver, registrationDate, status);

        boolean registered = fleetService.registerVehicle(vehicle);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        if (registered) {
            out.println("<p>Vehicle registered successfully.</p>");
        } else {
            out.println("<p>Vehicle could not be registered (invalid data or duplicate plate code).</p>");
        }
        out.println("<a href='vehicles'>Back to vehicle list</a>");
        out.println("</body></html>");
    }
}
