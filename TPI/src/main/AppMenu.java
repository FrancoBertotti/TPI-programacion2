package main;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Vehiculo;
import entities.SeguroVehicular;
import entities.Cobertura;
import Services.VehiculoService;
import Services.SeguroVehicularService;
import dao.SeguroVehicularDao; // esta hay que borrarla

public class AppMenu {

    private Scanner scanner;
    
    private SeguroVehicularDao seguroVehicularDao;
    
    private VehiculoService vehiculoService;
    private SeguroVehicularService seguroService;

    public AppMenu() {
        this.scanner = new Scanner(System.in);
        this.vehiculoService = new VehiculoService();
        this.seguroService = new SeguroVehicularService();
      
        this.seguroVehicularDao = new SeguroVehicularDao();
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
                System.out.println("Vehiculo encontrado:");
                System.out.println(v);
            } else {
                System.out.println("No se encontro vehiculo con ese ID.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: el ID debe ser numerico.");
            scanner.nextLine();
        }
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

            System.out.println("Seguro creado (simulacion):");
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
                System.out.println("Seguro encontrado:");
                System.out.println(s);
            } else {
                System.out.println("No se encontro seguro con ese ID.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: el ID debe ser numerico.");
            scanner.nextLine();
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

            Vehiculo v = vehiculoService.buscarPorId(idVehiculo);
            SeguroVehicular s = seguroService.buscarPorId(idSeguro);

            if (v == null) {
                System.out.println("No se encontro vehiculo con ese ID (simulacion).");
                return;
            }

            if (s == null) {
                System.out.println("No se encontro seguro con ese ID (simulacion).");
                return;
            }

            v.setSeguro(s);

            System.out.println("Seguro asignado al vehiculo (simulacion):");
            System.out.println(v);

            // aca iria la logica para actualizar en la base de datos

        } catch (InputMismatchException e) {
            System.out.println("Error: los IDs deben ser numericos.");
            scanner.nextLine();
        }
    }

    // ================= MAIN =================

    public static void main(String[] args) {
        AppMenu app = new AppMenu();
        app.mostrarMenu();
    }
}
