package Services;

import entities.SeguroVehicular;
import java.time.LocalDate;

public class SeguroVehicularService {

    public void crear(SeguroVehicular seguro) {
        System.out.println("Creando seguro vehicular...");
        System.out.println("Seguro " + seguro.getNroPoliza() + " guardado.");
    }

    public SeguroVehicular buscarPorId(long id) {
        System.out.println("Buscando seguro por ID: " + id);

        // Simulacion: si el ID es 1, devolvemos un seguro de ejemplo
        if (id == 1) {
            SeguroVehicular s = new SeguroVehicular(
                    "La Segunda",
                    "POL123",
                    entities.Cobertura.RC,
                    LocalDate.now().plusYears(1)
            );
            s.setId(1L);
            return s;
        }

        return null; 
    }
}
