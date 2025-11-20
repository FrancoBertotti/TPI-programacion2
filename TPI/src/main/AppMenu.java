package main;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import entities.Vehiculo;
import entities.SeguroVehicular;
import entities.Cobertura;
import Services.VehiculoService;
import Services.SeguroVehicularService;
import services.TransaccionVehiculoSeguroService;

public class AppMenu {

    private Scanner scanner;
    private VehiculoService vehiculoService;
    private SeguroVehicularService seguroService;
    private final TransaccionVehiculoSeguroService transaccionService;
    


    public AppMenu() {
        this.scanner = new Scanner(System.in);
        this.vehiculoService = new VehiculoService();
        this.seguroService = new SeguroVehicularService();
        this.transaccionService = new TransaccionVehiculoSeguroService();
    }

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Crear vehiculo");
            System.out.println("2. Buscar vehiculo por ID");
            System.out.println("3. Crear seguro vehicular");
            System.out.println("4. Buscar seguro vehicular por ID");
            System.out.println("5. Asignar seguro a vehiculo");
            System.out.println("6. Eliminar vehiculo");
            System.out.println("7. Actualizar vehiculo");
            System.out.println("8. Eliminar seguro");
            System.out.println("9. Actualizar seguro");
            System.out.println("10.Listar todos los vehiculos.");
            System.out.println("11.Crear vehículo + seguro (transacción manual)");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opcion: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // consumir enter

                switch (opcion) {
                    case 1:
                        crearVehiculo();
                        break;
                    case 2:
                        buscarVehiculoPorId();
                        break;
                    case 3:
                        crearSeguroVehicular();
                        break;
                    case 4:
                        buscarSeguroPorId();
                        break;
                    case 5:
                        asignarSeguroAVehiculo();
                        break;
                    case 6:
                        eliminarVehiculo();
                        break;
                    case 7:
                        actualizarVehiculo();
                        break;
                    case 8:
                        eliminarSeguroVehiculo();
                        break;
                    case 9:
                        actualizarSeguroVehiculo();
                        break;
                    case 10:
                        listarVehiculos();
                        break;
                    case 11:
                        crearVehiculoConSeguroTransaccion();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un numero.");
                scanner.nextLine(); // limpiar buffer
                opcion = -1;
            }
        }

        scanner.close();
    }

    // ================= VEHICULO =================
    private void crearVehiculo() {
        System.out.println("\n--- CREAR VEHICULO ---");

        try {
            System.out.print("Dominio (patente): ");
            String dominio = scanner.nextLine().toUpperCase();

            System.out.print("Marca: ");
            String marca = scanner.nextLine();

            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();

            System.out.print("Anio: ");
            int anio = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Numero de chasis: ");
            String nroChasis = scanner.nextLine();

            Vehiculo vehiculo = new Vehiculo(dominio, marca, modelo, anio, nroChasis);
            Vehiculo vehiculoCreado = vehiculoService.crearVehiculo(vehiculo);

            System.out.println(vehiculoCreado);

        } catch (InputMismatchException e) {
            System.out.println("Error: el anio debe ser numerico.");
            scanner.nextLine();
        }
    }

    private void buscarVehiculoPorId() {
        System.out.println("\n--- BUSCAR VEHICULO POR ID ---");
        System.out.print("Ingrese ID: ");

        try {
            long id = scanner.nextLong();
            scanner.nextLine();

            Vehiculo v = vehiculoService.buscarPorId(id);

            if (v != null) {
                System.out.println("Resultado:");
                System.out.println("Datos del vehiculo: "+v);
            } else {
                System.out.println("No se encontro vehiculo con ese ID.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: el ID debe ser numerico.");
            scanner.nextLine();
        }
    }

    private void eliminarVehiculo() {
        System.out.println("\n--- ELIMINAR VEHICULO POR ID ---");
        System.out.print("Ingrese ID: ");

        try {
            long id = scanner.nextLong();
            scanner.nextLine();

            Vehiculo v = vehiculoService.buscarPorId(id);

            if (v == null) {
                System.out.println("No se encontro vehiculo con ese ID.");
                return;
            }

                System.out.println("Vehiculo encontrado:");
                System.out.println(v);

            boolean exito = vehiculoService.eliminarVehiculo(id);

            if (exito) {
                System.out.println("Se elimino el vehiculo correctamente.");
            } else {
                System.out.println("No se pudo eliminar el vehiculo.");
        }

    }       catch (InputMismatchException e) {
                System.out.println("Error: el ID debe ser numerico.");
                scanner.nextLine();
            }
        }

    private void actualizarVehiculo() {
    System.out.println("\n--- ACTUALIZAR VEHICULO ---");
    System.out.print("Ingrese ID del vehiculo: ");

    try {
        long id = scanner.nextLong();
        scanner.nextLine();

        Vehiculo existente = vehiculoService.buscarPorId(id);

        if (existente == null) {
            System.out.println("No se encontro vehiculo con ese ID.");
            return;
        }

        System.out.println("Vehiculo actual:");
        System.out.println(existente);

        System.out.print("Nuevo dominio: ");
        existente.setDominio(scanner.nextLine());

        System.out.print("Nueva marca: ");
        existente.setMarca(scanner.nextLine());

        System.out.print("Nuevo modelo: ");
        existente.setModelo(scanner.nextLine());

        System.out.print("Nuevo anio: ");
        existente.setAnio(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Nuevo numero de chasis: ");
        existente.setNroChasis(scanner.nextLine());

        boolean exito = vehiculoService.actualizarVehiculo(existente);

        if (exito) {
            System.out.println("Vehiculo actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el vehiculo.");
        }

    } catch (InputMismatchException e) {
        System.out.println("Error: el anio e ID deben ser numericos.");
        scanner.nextLine();
    }
}

    private void listarVehiculos() {
        vehiculoService.listarTodos();
    }

    // ================= SEGURO VEHICULAR =================
    private void crearSeguroVehicular() {
        System.out.println("\n--- CREAR SEGURO VEHICULAR ---");

        try {
            System.out.print("Aseguradora: ");
            String aseguradora = scanner.nextLine();

            System.out.print("Numero de poliza: ");
            String nroPoliza = scanner.nextLine();

            System.out.println("Tipo de cobertura:");
            System.out.println("1. RC");
            System.out.println("2. TERCEROS");
            System.out.println("3. TODO_RIESGO");
            System.out.print("Seleccione una opcion: ");
            int opcCob = scanner.nextInt();
            scanner.nextLine();

            Cobertura cobertura;
            switch (opcCob) {
                case 1:
                    cobertura = Cobertura.RC;
                    break;
                case 2:
                    cobertura = Cobertura.TERCEROS;
                    break;
                case 3:
                    cobertura = Cobertura.TODO_RIESGO;
                    break;
                default:
                    System.out.println("Opcion invalida. Se asigna RC por defecto.");
                    cobertura = Cobertura.RC;
                    break;
            }

            System.out.print("Vencimiento (formato AAAA-MM-DD): ");
            String fechaStr = scanner.nextLine();
            LocalDate vencimiento = LocalDate.parse(fechaStr);

            SeguroVehicular s = new SeguroVehicular(
                    aseguradora,
                    nroPoliza,
                    cobertura,
                    vencimiento
            );

            seguroService.crear(s);
            System.out.println(s);

        } catch (Exception e) {
            System.out.println("Error al crear seguro: " + e.getMessage());
        }
    }

    private void buscarSeguroPorId() {
        System.out.println("\n--- BUSCAR SEGURO POR ID ---");
        System.out.print("Ingrese ID: ");

        try {
            long id = scanner.nextLong();
            scanner.nextLine();

            SeguroVehicular s = seguroService.buscarPorId(id);

            if (s != null) {
                System.out.println("Resultado:");
                System.out.println("Datos del seguro vehicular: "+s);
            } else {
                System.out.println("No se encontro seguro con ese ID.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: el ID debe ser numerico.");
            scanner.nextLine();
        }
    }

    private void eliminarSeguroVehiculo() {
        System.out.println("\n--- ELIMINAR SEGURO VEHICULAR POR ID ---");
        System.out.print("Ingrese ID: ");

        try {
            long id = scanner.nextLong();
            scanner.nextLine();

            SeguroVehicular s = seguroService.buscarPorId(id);

            if (s == null) {
                System.out.println("No se encontro seguro con ese ID.");
                return;
            }

            System.out.println("Seguro encontrado:");
            System.out.println(s);

            boolean exito = seguroService.eliminarSeguro(id);

            if (exito) {
                System.out.println("Seguro eliminado correctamente.");
                System.out.println("Si habia un vehiculo asociado este quedo NULL");
            } else {
                System.out.println("No se pudo eliminar el seguro.");
            }

        }   catch (InputMismatchException e) {
                System.out.println("Error: el ID debe ser numerico.");
                scanner.nextLine();
        }
    }

    private void actualizarSeguroVehiculo() {

        System.out.println("\n--- ACTUALIZAR SEGURO VEHICULAR ---");
        System.out.print("Ingrese ID del seguro: ");

        try {
            long id = scanner.nextLong();
            scanner.nextLine();

        // buscar seguro existente
            SeguroVehicular existente = seguroService.buscarPorId(id);

            if (existente == null) {
                System.out.println("No se encontro seguro con ese ID.");
                return;
            }

            System.out.println("Seguro actual:");
            System.out.println(existente);

            System.out.print("Nueva aseguradora: ");
            String nuevaAseguradora = scanner.nextLine();

            System.out.print("Nuevo numero de poliza: ");
            String nuevaPoliza = scanner.nextLine();

            System.out.println("Nueva cobertura:");
            System.out.println("1. RC");
            System.out.println("2. TERCEROS");
            System.out.println("3. TODO_RIESGO");
            System.out.print("Seleccione una opcion: ");
            int opcCob = scanner.nextInt();
            scanner.nextLine();

            Cobertura nuevaCobertura;
            switch (opcCob) {
                case 1:
                    nuevaCobertura = Cobertura.RC;
                    break;
                case 2:
                    nuevaCobertura = Cobertura.TERCEROS;
                    break;
                    case 3:
                    nuevaCobertura = Cobertura.TODO_RIESGO;
                    break;
                default:
                    System.out.println("Opcion invalida. No se actualiza el seguro.");
                    return;
            }

            System.out.print("Nuevo vencimiento (AAAA-MM-DD): ");
            String fechaStr = scanner.nextLine();
            LocalDate nuevoVencimiento = LocalDate.parse(fechaStr);

            existente.setAseguradora(nuevaAseguradora);
            existente.setNroPoliza(nuevaPoliza);
            existente.setCobertura(nuevaCobertura);
            existente.setVencimiento(nuevoVencimiento);

            boolean exito = seguroService.actualizarSeguro(existente);

            if (exito) {
                System.out.println("Seguro actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el seguro.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: los datos numericos son invalidos.");
            scanner.nextLine();
        }   catch (Exception e) {
            System.out.println("Error al actualizar seguro: " + e.getMessage());
        }
    }
               
    // =========== ASIGNAR SEGURO A VEHICULO ===========
    private void asignarSeguroAVehiculo() {
        System.out.println("\n--- ASIGNAR SEGURO A VEHICULO ---");

        try {
            System.out.print("Ingrese ID de vehiculo: ");
            long idVehiculo = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Ingrese ID de seguro: ");
            long idSeguro = scanner.nextLong();
            scanner.nextLine();
            vehiculoService.asignarSeguro(idVehiculo, idSeguro);
        } catch (InputMismatchException e) {
            System.out.println("Error: los IDs deben ser numericos.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error al asignar seguro: " + e.getMessage());
        }
    }
    
    
    // =========== CREAR VEHICULO + SEGURO (TRANSACCIÓN) ===========
    
    private void crearVehiculoConSeguroTransaccion() {
    System.out.println("\n--- CREAR VEHICULO + SEGURO EN UNA ÚNICA TRANSACCIÓN (MANUAL)---");

    try {
        
        System.out.print("Dominio: ");
        String dominio = scanner.nextLine().toUpperCase();

        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Número de chasis: ");
        String nroChasis = scanner.nextLine();

        Vehiculo vehiculo = new Vehiculo(dominio, marca, modelo, anio, nroChasis);

        
        System.out.print("Aseguradora: ");
        String aseguradora = scanner.nextLine();

        System.out.print("Número de póliza: ");
        String nroPoliza = scanner.nextLine();

        System.out.println("Tipo de cobertura:");
        System.out.println("1) RC");
        System.out.println("2) TERCEROS");
        System.out.println("3) TODO_RIESGO");
        int opc = scanner.nextInt();
        scanner.nextLine();

        Cobertura cobertura = (opc == 2) ? Cobertura.TERCEROS :
                              (opc == 3) ? Cobertura.TODO_RIESGO :
                              Cobertura.RC;

        System.out.print("Vencimiento (AAAA-MM-DD): ");
        String fechaStr = scanner.nextLine();
        LocalDate vencimiento = LocalDate.parse(fechaStr);

        SeguroVehicular seguro = new SeguroVehicular(
            aseguradora, nroPoliza, cobertura, vencimiento
        );

        
        TransaccionVehiculoSeguroService txService = new TransaccionVehiculoSeguroService();
        txService.crearVehiculoConSeguro(vehiculo, seguro);

    } catch (Exception e) {
        System.out.println("Error en transacción: " + e.getMessage());
    }
}
    

    // ================= MAIN =================
    public static void main(String[] args) {
        AppMenu app = new AppMenu();
        app.mostrarMenu();
    }
}
