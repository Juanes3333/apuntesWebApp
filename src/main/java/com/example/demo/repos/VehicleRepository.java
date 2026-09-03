package com.example.demo.repos;

import com.example.demo.model.Vehicle;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


// @Repository: estereotipo de Spring que marca esta clase como un bean del componente de acceso
// a datos. Al estar anotada, el @ComponentScan de @SpringBootApplication la detecta y crea una
// instancia (singleton) administrada por el contenedor, disponible para inyectarse con @Autowired
// (aquí no hay base de datos real: la "persistencia" es una List en memoria).
@Repository
public class VehicleRepository {
    private List<Vehicle> vehicles;

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
        this.vehicles.add(new Vehicle(1, "Toyota Hilux", "XYZ-123", "Truck", "Juan Perez", "2023-05-10", "Active"));
        this.vehicles.add(new Vehicle(2, "Ford Transit", "ABC-987", "Van", "Maria Gomez", "2024-01-15", "InMaintenance"));
        this.vehicles.add(new Vehicle(3, "Honda Civic", "LMN-456", "Sedan", "Carlos Ruiz", "2025-11-20", "Retired"));
    }

    public void addVehicle (Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public List<Vehicle> findAll(){
        return vehicles;
    }

    public Vehicle findById(Integer id){
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                return vehicle;
            }
        }
        return null;
    }
}
