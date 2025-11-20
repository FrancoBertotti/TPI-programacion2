package main;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import entities.Vehiculo;
import entities.SeguroVehicular;
import entities.Cobertura;
import Services.VehiculoService;
import Services.SeguroVehicularService;

public class AppMenu {

    private Scanner scanner;
    private VehiculoService vehiculoService;
    private SeguroVehicularService seguroService;

    public AppMenu() {
        this.scanner = new Scanner(System.in);
        this.vehiculoService = new VehiculoService();
        this.seguroService = new SeguroVehicularService();
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
            System.out.println("10. Listar todos los vehiculos.");
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

    // ================= MAIN =================
    public static void main(String[] args) {
        AppMenu app = new AppMenu();
        app.mostrarMenu();
    }
}
