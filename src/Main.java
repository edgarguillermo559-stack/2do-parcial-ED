//public class Main {
//    public static void main(String[] args) {
//        ClaseControl agencia = new ClaseControl();
//
//        // 1. Agregar vuelos
//        System.out.println("=== AGREGANDO VUELOS ===");
//        agencia.agregarVuelo("AR123", "2024-06-15", "Madrid");
//        agencia.agregarVuelo("IB456", "2024-06-16", "Londres");
//
//        // 2. Agregar pasajeros
//        System.out.println("\n=== AGREGANDO PASAJEROS ===");
//        agencia.agregarPasajero("AR123", "P111", "Juan Perez", "Argentina", 35, "M");
//        agencia.agregarPasajero("AR123", "P222", "Maria Lopez", "Chile", 28, "F");
//        agencia.agregarPasajero("IB456", "P333", "Ana Gomez", "España", 30, "F");
//        agencia.agregarPasajero("IB456", "P444", "Pedro Sosa", "Uruguay", 40, "M");
//
//        // 3. Mostrar estado inicial
//        System.out.println("\n=== ESTADO INICIAL ===");
//        agencia.mostrarDatosVuelos();
//
//        // 4. Cancelar pasajeros (probar los 3 casos)
//        System.out.println("\n=== CANCELANDO PASAJEROS ===");
//        agencia.procesoCancelacion("Juan Perez");   // Primero de AR123
//        agencia.procesoCancelacion("Ana Gomez");    // Primero de IB456
//        agencia.procesoCancelacion("Pedro Sosa");   // Último de IB456
//        agencia.procesoCancelacion("NoExiste");     // No encontrado
//
//        // 5. Mostrar estado después de cancelaciones
//        System.out.println("\n=== ESTADO DESPUÉS DE CANCELACIONES ===");
//        agencia.mostrarDatosVuelos();
//
//        // 6. Mostrar pila de cancelados
//        System.out.println("\n=== PILA DE CANCELADOS ===");
//        agencia.mostrarPasajerosCancelados();
//
//        // 7. Ver la cima de la pila
//        System.out.println("\n=== CIMA DE LA PILA ===");
//        NodoCancelacion cima = agencia.top();
//        if (cima != null) {
//            System.out.println("Último cancelado: " + cima.getInfo().getNombre() +
//                    " | Vuelo: " + cima.getInfoVuelo());
//        } else {
//            System.out.println("Pila vacía.");
//        }
//
//        // 8. Revertir la última cancelación
//        System.out.println("\n=== REVIRTIENDO ÚLTIMA CANCELACIÓN ===");
//        agencia.revertirCancelacion();
//
//        // 9. Mostrar estado después de revertir
//        System.out.println("\n=== ESTADO DESPUÉS DE REVERTIR ===");
//        agencia.mostrarDatosVuelos();
//
//        // 10. Revertir otra cancelación
//        System.out.println("\n=== REVIRTIENDO OTRA CANCELACIÓN ===");
//        agencia.revertirCancelacion();
//
//        // 11. Intentar revertir cuando la pila está vacía (probar error)
//        System.out.println("\n=== INTENTANDO REVERTIR CON PILA VACÍA ===");
//        agencia.revertirCancelacion();
//
//        // 12. Mostrar estado después de revertir
//        System.out.println("\n=== ESTADO DESPUÉS DE REVERTIR ===");
//        agencia.mostrarDatosVuelos();
//
//        // 13. Intentar revertir cuando la pila está vacía (probar error)
//        System.out.println("\n=== INTENTANDO REVERTIR CON PILA VACÍA ===");
//        agencia.revertirCancelacion();
//    }
//}

public class Main {
    public static void main(String[] args) {
        ClaseControl agencia = new ClaseControl();

        System.out.println("==============================================");
        System.out.println("   SISTEMA DE GESTIÓN DE VUELOS Y PASAJEROS   ");
        System.out.println("==============================================");

        // ==================================================
        // CASO 1: Operar con la agencia vacía
        // ==================================================
        System.out.println("\n--- CASO 1: Agencia vacía ---");
        agencia.mostrarDatosVuelos();
        agencia.procesoCancelacion("Juan");
        agencia.revertirCancelacion();
        agencia.mostrarPasajerosCancelados();
        System.out.println("Vuelos de riesgo: " + agencia.metodoRecursivo());
        System.out.println("Vuelos de riesgo (no recursivo): ");
        agencia.metodoNoRecursivo();

        // ==================================================
        // CASO 2: Agregar vuelos y pasajeros
        // ==================================================
        System.out.println("\n--- CASO 2: Agregando vuelos y pasajeros ---");

        // Vuelo 1: AR123 (Madrid) - 12 menores (RIESGO)
        agencia.agregarVuelo("AR123", "2024-06-15", "Madrid");
        for (int i = 1; i <= 12; i++) {
            agencia.agregarPasajero("AR123", "P" + i, "Menor" + i, "Argentina", 10, "M");
        }
        // Agregamos un adulto para variar
        agencia.agregarPasajero("AR123", "P999", "Adulto1", "Argentina", 30, "M");

        // Vuelo 2: IB456 (Londres) - 3 menores (NO riesgo)
        agencia.agregarVuelo("IB456", "2024-06-16", "Londres");
        for (int i = 1; i <= 3; i++) {
            agencia.agregarPasajero("IB456", "Q" + i, "Menor" + i, "Chile", 12, "F");
        }
        agencia.agregarPasajero("IB456", "Q999", "Adulto2", "Chile", 40, "F");

        // Vuelo 3: LA789 (Lima) - 15 menores (RIESGO)
        agencia.agregarVuelo("LA789", "2024-06-17", "Lima");
        for (int i = 1; i <= 15; i++) {
            agencia.agregarPasajero("LA789", "R" + i, "Menor" + i, "Perú", 8, "M");
        }

        // Vuelo 4: AA101 (Miami) - 10 menores (NO riesgo, porque no es MÁS de 10)
        agencia.agregarVuelo("AA101", "2024-06-18", "Miami");
        for (int i = 1; i <= 10; i++) {
            agencia.agregarPasajero("AA101", "S" + i, "Menor" + i, "EEUU", 14, "F");
        }

        // Vuelo 5: Sin pasajeros
        agencia.agregarVuelo("XX000", "2024-06-19", "París");

        // ==================================================
        // CASO 3: Mostrar todos los vuelos
        // ==================================================
        System.out.println("\n--- CASO 3: Datos de todos los vuelos ---");
        agencia.mostrarDatosVuelos();

        // ==================================================
        // CASO 4: Probar método no recursivo
        // ==================================================
        System.out.println("\n--- CASO 4: Método NO recursivo (vuelos de riesgo) ---");
        agencia.metodoNoRecursivo();

        // ==================================================
        // CASO 5: Probar método recursivo
        // ==================================================
        System.out.println("\n--- CASO 5: Método RECURSIVO (vuelos de riesgo) ---");
        int riesgo = agencia.metodoRecursivo();
        System.out.println("Total de vuelos de riesgo: " + riesgo);
        System.out.println("(Esperado: 2 -> AR123 y LA789)");

        // ==================================================
        // CASO 6: Cancelar pasajeros (los 3 casos)
        // ==================================================
        System.out.println("\n--- CASO 6: Cancelando pasajeros ---");

        // Caso 6.1: Cancelar el PRIMERO de un vuelo
        System.out.println("\n6.1 Cancelando al PRIMERO (Menor1 de AR123)");
        agencia.procesoCancelacion("Menor1");

        // Caso 6.2: Cancelar uno del MEDIO
        System.out.println("\n6.2 Cancelando uno del MEDIO (Menor5 de AR123)");
        agencia.procesoCancelacion("Menor5");

        // Caso 6.3: Cancelar el ÚLTIMO
        System.out.println("\n6.3 Cancelando al ÚLTIMO (Menor12 de AR123)");
        agencia.procesoCancelacion("Menor12");

        // Caso 6.4: Cancelar un pasajero de otro vuelo
        System.out.println("\n6.4 Cancelando a Menor1 de IB456");
        agencia.procesoCancelacion("Menor1");

        // Caso 6.5: Cancelar un pasajero que no existe
        System.out.println("\n6.5 Cancelando a un pasajero inexistente");
        agencia.procesoCancelacion("NoExiste");

        // ==================================================
        // CASO 7: Mostrar estado después de cancelaciones
        // ==================================================
        System.out.println("\n--- CASO 7: Estado después de cancelaciones ---");
        agencia.mostrarDatosVuelos();

        // ==================================================
        // CASO 8: Mostrar pila de cancelados
        // ==================================================
        System.out.println("\n--- CASO 8: Pila de pasajeros cancelados ---");
        agencia.mostrarPasajerosCancelados();

        // ==================================================
        // CASO 9: Ver la cima de la pila
        // ==================================================
        System.out.println("\n--- CASO 9: Cima de la pila ---");
        NodoCancelacion cima = agencia.top();
        if (cima != null) {
            System.out.println("Último cancelado: " + cima.getInfo().getNombre() +
                    " | Vuelo: " + cima.getInfoVuelo());
        } else {
            System.out.println("Pila vacía.");
        }

        // ==================================================
        // CASO 10: Revertir cancelaciones (LIFO)
        // ==================================================
        System.out.println("\n--- CASO 10: Revirtiendo cancelaciones ---");
        System.out.println("Revirtiendo la última (Menor1 de IB456)...");
        agencia.revertirCancelacion();

        System.out.println("\nRevirtiendo la siguiente (Menor12 de AR123)...");
        agencia.revertirCancelacion();

        // ==================================================
        // CASO 11: Estado después de revertir
        // ==================================================
        System.out.println("\n--- CASO 11: Estado después de revertir ---");
        agencia.mostrarDatosVuelos();

        // ==================================================
        // CASO 12: Pila después de revertir
        // ==================================================
        System.out.println("\n--- CASO 12: Pila de cancelados restante ---");
        agencia.mostrarPasajerosCancelados();

        // ==================================================
        // CASO 13: Revertir hasta vaciar la pila
        // ==================================================
        System.out.println("\n--- CASO 13: Revirtiendo todas las cancelaciones ---");
        while (!agencia.isEmptyPila()) {
            agencia.revertirCancelacion();
        }

        System.out.println("\n--- CASO 14: Intentar revertir con pila vacía ---");
        agencia.revertirCancelacion();

        // ==================================================
        // CASO 15: Estado final
        // ==================================================
        System.out.println("\n--- CASO 15: Estado final ---");
        agencia.mostrarDatosVuelos();

        // ==================================================
        // CASO 16: Vuelos de riesgo después de revertir
        // ==================================================
        System.out.println("\n--- CASO 16: Vuelos de riesgo después de revertir ---");
        System.out.println("Total de vuelos de riesgo: " + agencia.metodoRecursivo());
        System.out.println("(Esperado: 2 -> AR123 y LA789)");

        // ==================================================
        // CASO 17: Cancelar a todos los menores de un vuelo
        // ==================================================
        System.out.println("\n--- CASO 17: Cancelar todos los menores de LA789 ---");
        // Cancelamos 15 menores (uno por uno)
        for (int i = 1; i <= 15; i++) {
            agencia.procesoCancelacion("Menor" + i);
        }
        System.out.println("\nDespués de cancelar a todos:");
        agencia.mostrarDatosVuelos();
        System.out.println("Vuelos de riesgo ahora: " + agencia.metodoRecursivo());

        System.out.println("\n==============================================");
        System.out.println("   FIN DE LAS PRUEBAS");
        System.out.println("==============================================");
    }
}