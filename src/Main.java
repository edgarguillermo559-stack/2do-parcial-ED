import java.util.Scanner;

public class Main {
    static ClaseControl agencia = new ClaseControl();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n==================================================");
            System.out.println("   SISTEMA DE CONTROL DE VUELOS INTERNACIONALES   ");
            System.out.println("==================================================");
            System.out.println("1. Registrar Vuelo");
            System.out.println("2. Registrar Pasajero");
            System.out.println("3. Mostrar Vuelos y Pasajeros");
            System.out.println("4. Mostrar Pasajeros Cancelados");
            System.out.println("5. Cancelar Pasajero");
            System.out.println("6. Deshacer última cancelación");
            System.out.println("7. Mostrar Lista de Espera Prioritaria (por vuelo)");
            System.out.println("8. Mostrar Vuelos de Riesgo (recursivo)");
            System.out.println("9. Rellenar casos de prueba y probar todo");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = scan.nextInt();
            scan.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1: registrarVuelo(); break;
                case 2: registrarPasajero(); break;
                case 3: mostrarVuelosConPrefijo(); break;
                case 4: mostrarPilaConPrefijo(); break;
                case 5: cancelarPasajero(); break;
                case 6: deshacerCancelacion(); break;
                case 7: mostrarColaPrioritaria(); break;
                case 8: mostrarVuelosDeRiesgo(); break;
                case 9: rellenarCasosDePrueba(); break;
                case 0: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scan.close();
    }

    // ============================================================
    // 1. REGISTRAR VUELO
    // ============================================================
    static void registrarVuelo() {
        System.out.print("Número de vuelo: ");
        String numero = scan.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = scan.nextLine();
        System.out.print("Destino: ");
        String destino = scan.nextLine();
        agencia.agregarVuelo(numero, fecha, destino);
        System.out.println("Vuelo registrado exitosamente.");
    }

    // ============================================================
    // 2. REGISTRAR PASAJERO
    // ============================================================
    static void registrarPasajero() {
        if (agencia.head == null) {
            System.out.println("Error: No hay vuelos registrados. Registre un vuelo primero.");
            return;
        }
        System.out.print("Número de vuelo: ");
        String numeroVuelo = scan.nextLine();
        System.out.print("Número de pasaporte: ");
        String pasaporte = scan.nextLine();
        System.out.print("Nombre: ");
        String nombre = scan.nextLine();
        System.out.print("País: ");
        String pais = scan.nextLine();
        System.out.print("Edad: ");
        int edad = scan.nextInt();
        scan.nextLine();
        System.out.print("Sexo (M/F): ");
        String sexo = scan.nextLine();
        agencia.agregarPasajero(numeroVuelo, pasaporte, nombre, pais, edad, sexo);
    }

    // ============================================================
    // 3. MOSTRAR VUELOS Y PASAJEROS
    // ============================================================
    static void mostrarVuelosConPrefijo() {
        if (agencia.head == null) {
            System.out.println("Error: No existen vuelos registrados");
            return;
        }
        NodoExterno temp = agencia.head;
        while (temp != null) {
            System.out.println();
            System.out.println("-Vuelo: " + temp.numeroVuelo +
                    " | Fecha: " + temp.fecha +
                    " | Destino: " + temp.destino);
            if (temp.listaPasajeros == null) {
                System.out.println("No hay Pasajeros agendados para este viaje");
            } else {
                NodoInterno tempInt = temp.listaPasajeros;
                while (tempInt != null) {
                    System.out.println("." + prefijo(tempInt.getEdad()) + tempInt.getNombre());
                    tempInt = tempInt.getNext();
                }
            }
            temp = temp.getNext();
        }
    }

    // ============================================================
    // 4. MOSTRAR PASAJEROS CANCELADOS
    // ============================================================
    static void mostrarPilaConPrefijo() {
        if (agencia.pila.isEmpty()) {
            System.out.println("Error: No hay pasajeros cancelados");
            return;
        }
        System.out.println("Pasajeros Cancelados: ");
        LinkedStack<NodoInterno> aux = new LinkedStack<>();
        while (!agencia.pila.isEmpty()) {
            Nodo<NodoInterno> nodo = agencia.pila.pop();
            System.out.println("." + prefijo(nodo.getInfo().getEdad()) + nodo.getInfo().getNombre() +
                    " | Vuelo: " + nodo.getInfoVuelo());
            aux.push(nodo.getInfo(), nodo.getInfoVuelo());
        }
        while (!aux.isEmpty()) {
            Nodo<NodoInterno> nodo = aux.pop();
            agencia.pila.push(nodo.getInfo(), nodo.getInfoVuelo());
        }
    }

    // ============================================================
    // 5. CANCELAR PASAJERO
    // ============================================================
    static void cancelarPasajero() {
        System.out.print("Nombre del pasajero a cancelar: ");
        String nombre = scan.nextLine();
        agencia.procesoCancelacion(nombre);
    }

    // ============================================================
    // 6. DESHACER ÚLTIMA CANCELACIÓN
    // ============================================================
    static void deshacerCancelacion() {
        agencia.revertirCancelacion();
    }

    // ============================================================
    // 7. MOSTRAR LISTA DE ESPERA PRIORITARIA
    // ============================================================
    static void mostrarColaPrioritaria() {
        System.out.print("Número de vuelo: ");
        String numeroVuelo = scan.nextLine();
        agencia.agregarCola();
        mostrarColaConPrefijo(agencia, numeroVuelo);
    }

    // ============================================================
    // 8. MOSTRAR VUELOS DE RIESGO (RECURSIVO)
    // ============================================================
    static void mostrarVuelosDeRiesgo() {
        int riesgo = agencia.metodoRecursivo();
        System.out.println("Total de vuelos de riesgo (>10 menores de 16): " + riesgo);
    }

    // ============================================================
    // 9. RELLENAR CASOS DE PRUEBA Y PROBAR TODO
    // ============================================================
    static void rellenarCasosDePrueba() {
        System.out.println("\n--- Rellenando casos de prueba ---");

        // Vuelos
        agencia.agregarVuelo("AR123", "2024-06-15", "Madrid");
        agencia.agregarVuelo("IB456", "2024-06-16", "Londres");
        agencia.agregarVuelo("LA789", "2024-06-17", "Lima");
        agencia.agregarVuelo("AA101", "2024-06-18", "Miami");
        agencia.agregarVuelo("XX000", "2024-06-19", "París");

        // Vuelo AR123: 12 menores (riesgo) + 2 adultos
        agencia.agregarPasajero("AR123", "P001", "Felipe", "Argentina", 10, "M");
        agencia.agregarPasajero("AR123", "P002", "Adrian Hernandez", "Argentina", 12, "M");
        agencia.agregarPasajero("AR123", "P003", "Felicia", "Argentina", 8, "F");
        agencia.agregarPasajero("AR123", "P004", "Sofía", "Argentina", 14, "F");
        agencia.agregarPasajero("AR123", "P005", "Mateo", "Argentina", 11, "M");
        agencia.agregarPasajero("AR123", "P006", "Valentina", "Argentina", 9, "F");
        agencia.agregarPasajero("AR123", "P007", "Santiago", "Argentina", 13, "M");
        agencia.agregarPasajero("AR123", "P008", "Camila", "Argentina", 7, "F");
        agencia.agregarPasajero("AR123", "P009", "Nicolás", "Argentina", 15, "M");
        agencia.agregarPasajero("AR123", "P010", "Isabella", "Argentina", 6, "F");
        agencia.agregarPasajero("AR123", "P011", "Sebastián", "Argentina", 10, "M");
        agencia.agregarPasajero("AR123", "P012", "Renata", "Argentina", 12, "F");
        agencia.agregarPasajero("AR123", "P013", "Adrian Enrique Reinoso", "Argentina", 35, "M");
        agencia.agregarPasajero("AR123", "P014", "Katherine Alvarez", "Argentina", 30, "F");

        // Vuelo IB456: 3 menores + 2 adultos
        agencia.agregarPasajero("IB456", "Q001", "Edgar Guillermo", "Chile", 12, "M");
        agencia.agregarPasajero("IB456", "Q002", "Alejandro Javier", "Chile", 10, "M");
        agencia.agregarPasajero("IB456", "Q003", "María José", "Chile", 14, "F");
        agencia.agregarPasajero("IB456", "Q004", "Carlos Andrés", "Chile", 40, "M");
        agencia.agregarPasajero("IB456", "Q005", "Lucía Fernanda", "Chile", 38, "F");

        // Vuelo LA789: 15 menores (riesgo)
        agencia.agregarPasajero("LA789", "R001", "Diego", "Perú", 8, "M");
        agencia.agregarPasajero("LA789", "R002", "Valeria", "Perú", 10, "F");
        agencia.agregarPasajero("LA789", "R003", "Gabriel", "Perú", 12, "M");
        agencia.agregarPasajero("LA789", "R004", "Antonella", "Perú", 9, "F");
        agencia.agregarPasajero("LA789", "R005", "Emilio", "Perú", 11, "M");
        agencia.agregarPasajero("LA789", "R006", "Florencia", "Perú", 7, "F");
        agencia.agregarPasajero("LA789", "R007", "Tomás", "Perú", 13, "M");
        agencia.agregarPasajero("LA789", "R008", "Micaela", "Perú", 6, "F");
        agencia.agregarPasajero("LA789", "R009", "Joaquín", "Perú", 15, "M");
        agencia.agregarPasajero("LA789", "R010", "Julieta", "Perú", 10, "F");
        agencia.agregarPasajero("LA789", "R011", "Facundo", "Perú", 12, "M");
        agencia.agregarPasajero("LA789", "R012", "Delfina", "Perú", 9, "F");
        agencia.agregarPasajero("LA789", "R013", "Bautista", "Perú", 14, "M");
        agencia.agregarPasajero("LA789", "R014", "Catalina", "Perú", 8, "F");
        agencia.agregarPasajero("LA789", "R015", "Benjamín", "Perú", 11, "M");

        // Vuelo AA101: 10 menores (no riesgo)
        agencia.agregarPasajero("AA101", "S001", "Emma", "EEUU", 14, "F");
        agencia.agregarPasajero("AA101", "S002", "Liam", "EEUU", 10, "M");
        agencia.agregarPasajero("AA101", "S003", "Olivia", "EEUU", 12, "F");
        agencia.agregarPasajero("AA101", "S004", "Noah", "EEUU", 9, "M");
        agencia.agregarPasajero("AA101", "S005", "Ava", "EEUU", 13, "F");
        agencia.agregarPasajero("AA101", "S006", "Ethan", "EEUU", 11, "M");
        agencia.agregarPasajero("AA101", "S007", "Sophia", "EEUU", 8, "F");
        agencia.agregarPasajero("AA101", "S008", "Mason", "EEUU", 15, "M");
        agencia.agregarPasajero("AA101", "S009", "Isabella", "EEUU", 7, "F");
        agencia.agregarPasajero("AA101", "S010", "Lucas", "EEUU", 12, "M");

        System.out.println("Casos de prueba cargados exitosamente.");
        System.out.println("Ahora puede usar las opciones del menú para probar.");

        // Prueba automática de todos los métodos
        System.out.println("\n--- PRUEBA AUTOMÁTICA DE TODOS LOS MÉTODOS ---");
        System.out.println("\n1. Mostrar vuelos:");
        mostrarVuelosConPrefijo();

        System.out.println("\n2. Vuelos de riesgo (recursivo):");
        mostrarVuelosDeRiesgo();

        System.out.println("\n3. Cancelar pasajeros (Felipe, Mateo, Renata):");
        agencia.procesoCancelacion("Felipe");
        agencia.procesoCancelacion("Mateo");
        agencia.procesoCancelacion("Renata");

        System.out.println("\n4. Mostrar pasajeros cancelados:");
        mostrarPilaConPrefijo();

        System.out.println("\n5. Deshacer última cancelación (Renata):");
        agencia.revertirCancelacion();

        System.out.println("\n6. Mostrar pasajeros cancelados después de deshacer:");
        mostrarPilaConPrefijo();

        System.out.println("\n7. Mostrar lista de espera prioritaria del vuelo AR123:");
        agencia.agregarCola();
        mostrarColaConPrefijo(agencia, "AR123");

        System.out.println("\n8. Mostrar lista de espera prioritaria del vuelo IB456:");
        mostrarColaConPrefijo(agencia, "IB456");

        System.out.println("\n9. Mostrar lista de espera prioritaria del vuelo LA789:");
        mostrarColaConPrefijo(agencia, "LA789");

        System.out.println("\n10. Mostrar lista de espera prioritaria del vuelo AA101:");
        mostrarColaConPrefijo(agencia, "AA101");

        System.out.println("\n11. Mostrar lista de espera prioritaria del vuelo XX000 (sin pasajeros):");
        mostrarColaConPrefijo(agencia, "XX000");

        System.out.println("\n--- FIN DE LA PRUEBA AUTOMÁTICA ---");
    }

    // ============================================================
    // MÉTODOS AUXILIARES
    // ============================================================

    static String prefijo(int edad) {
        return (edad < 16) ? "mn" : "my";
    }

    static void mostrarColaConPrefijo(ClaseControl agencia, String numeroVuelo) {
        if (agencia.cola.isEmpty()) {
            System.out.println("No hay elementos en la cola");
            return;
        }
        Nodo<NodoInterno> nodo = agencia.cola.getFirst();
        boolean encontrado = false;
        while (nodo != null) {
            if (nodo.getInfoVuelo().equalsIgnoreCase(numeroVuelo)) {
                NodoInterno p = nodo.getInfo();
                System.out.println("." + prefijo(p.getEdad()) + p.getNombre());
                encontrado = true;
            }
            nodo = nodo.getNext();
        }
        if (!encontrado) {
            System.out.println("No hay pasajeros en cola para ese vuelo.");
        }
    }
}