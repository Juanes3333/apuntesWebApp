package com.example.demo.repos;

import com.example.demo.model.DeliveryRecord;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

// @Repository: igual que en VehicleRepository, convierte esta clase en un bean detectado por
// component scan, para que FleetService pueda recibirlo vía @Autowired en lugar de hacer "new".
@Repository
public class DeliveryRecordRepository {
    private List<DeliveryRecord> deliveryRecords;

    public DeliveryRecordRepository() {
        this.deliveryRecords = new ArrayList<>();
        this.deliveryRecords.add(new DeliveryRecord(1,"TRK-001","Caja con repuestos electrónicos","2026-09-05","Calle 5 # 34-12",1,1));
        this.deliveryRecords.add(new DeliveryRecord(2,"TRK-002","Documentos de oficina","2026-09-06","Avenida 6N # 20-50",2,1));
        this.deliveryRecords.add(new DeliveryRecord(3,"TRK-003","Lote de insumos médicos","2026-09-07","Carrera 100 # 16-20",3,1));
    }

    public void addDeliveryRecord(DeliveryRecord deliveryRecord){
        deliveryRecords.add(deliveryRecord);
    }

    public List<DeliveryRecord> findAll (){
        return deliveryRecords;
    }

    public DeliveryRecord findById(Integer id){
        for (DeliveryRecord deliveryRecord : deliveryRecords){
            if (deliveryRecord.getId().equals(id)){
                return deliveryRecord;
            }
        }
        return null;
    }
}
