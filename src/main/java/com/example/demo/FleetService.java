package com.example.demo;

import com.example.demo.repos.*;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


// @Service: estereotipo de Spring para la capa de lógica de negocio. Igual que @Repository,
// hace que el component scan registre esta clase como bean, de modo que los Servlets puedan
// inyectarla con @Autowired en vez de instanciarla con "new".
@Service
public class FleetService {
    // @Autowired de campo: le pide a Spring que inyecte automáticamente el bean de tipo
    // VehicleRepository (el único registrado con @Repository) en este campo, resolviendo la
    // dependencia sin necesidad de un constructor ni de código de configuración manual.
    @Autowired
    private VehicleRepository vehicleRepository;

    // Misma idea que arriba, pero para el repositorio de registros de entrega.
    @Autowired
    private DeliveryRecordRepository deliveryRecordRepository;

    private boolean existVehicleValidation(List<Vehicle> vehicles, Vehicle vehicleToValidate) {
        for (Vehicle v : vehicles) {
            if (vehicleToValidate.getPlateCode().equalsIgnoreCase(v.getPlateCode())) {
                return true;
            }
        }
        return false;
    }


    public boolean registerVehicle(Vehicle vehicle) {
        if (vehicle != null && vehicle.getPlateCode().trim().length() == 6) {
            if (!existVehicleValidation(vehicleRepository.findAll(), vehicle)) {
                vehicleRepository.addVehicle(vehicle);
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean existDeliveryRecordValidation(List<DeliveryRecord> deliveryRecord, DeliveryRecord deliveryRecordToValidate) {
        for (DeliveryRecord dr : deliveryRecord) {
            if (deliveryRecordToValidate.getTrackingCode().equalsIgnoreCase(dr.getTrackingCode())) {
                return true;
            }
        }
        return false;
    }

    private boolean existVehicleValidationById(List<Vehicle> vehicles, Integer vehicleToValidate) {
        for (Vehicle v : vehicles) {
            if (vehicleToValidate.equals(v.getId())) {
                return true;
            }
        }
        return false;
    }

    private boolean vehicleIsRetired(List<Vehicle> vehicles, Integer vehicleToValidate) {
        for (Vehicle v : vehicles) {
            if (vehicleToValidate.equals(v.getId()) && v.getStatus().equalsIgnoreCase("retired")) {
                return true;
            }
        }
        return false;
    }



    public boolean registerDeliveryRecord(DeliveryRecord deliveryRecord) {
        if (deliveryRecord != null && 1<=deliveryRecord.getPriorityLevel() && deliveryRecord.getPriorityLevel()<=5) {
            List<DeliveryRecord> drTemp = deliveryRecordRepository.findAll();
            if (!existDeliveryRecordValidation(drTemp, deliveryRecord)) {
                if (existVehicleValidationById(vehicleRepository.findAll(), deliveryRecord.getVehicleId()) && !vehicleIsRetired(vehicleRepository.findAll(), deliveryRecord.getVehicleId())) {
                    deliveryRecordRepository.addDeliveryRecord(deliveryRecord);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    // Método de lectura simple (no forma parte de las 7 reglas de negocio): delega directamente
    // en el repositorio. Los Servlets lo usan para listar vehículos sin acceder al repository
    // directamente (siempre pasan por el Service).
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Análogo al anterior, pero para los registros de entrega.
    public List<DeliveryRecord> getAllDeliveryRecords() {
        return deliveryRecordRepository.findAll();
    }
}
