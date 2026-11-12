public class ClaseControl {
    NodoExterno head;

    public ClaseControl() {
        this.head = null;
    }

    LinkedStack<NodoInterno> pila = new LinkedStack<>();
    LinkedQueue<NodoInterno> cola = new LinkedQueue<>();


    //1.Multilista

    public void agregarVuelo(String numeroVuelo, String fecha, String destino) {
        NodoExterno nodoNuevo = new NodoExterno(numeroVuelo, fecha, destino);
        if (head == null) {
            head = nodoNuevo;
        } else {
            NodoExterno temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(nodoNuevo);
        }
    }

    public void agregarPasajero(String numeroVuelo, String numeroPasaporte, String nombre, String pais, int edad, String sexo) {
        if (head == null) {
            System.out.println("Error: No hay Vuelos Registrados");
        } else {
            NodoExterno temp = head;
            while (temp != null) {
                if (temp.numeroVuelo.equals(numeroVuelo)) {
                    NodoInterno nodoInterno = new NodoInterno(numeroPasaporte, nombre, pais, edad, sexo);
                    if (temp.listaPasajeros == null) {
                        temp.listaPasajeros = nodoInterno;
                        return;
                    } else {
                        NodoInterno tempInterno = temp.listaPasajeros;
                        while (tempInterno.getNext() != null) {
                            tempInterno = tempInterno.getNext();
                        }
                        tempInterno.setNext(nodoInterno);
                        return;
                    }
                }
                temp = temp.getNext();
            }
            System.out.println("Error: Vuelo no encontrado");
        }
    }

    public void mostrarDatosVuelos() {
        if (head == null) {
            System.out.println("Error: No existen vuelos registrados");
        } else {
            NodoExterno temp = head;
            while (temp != null) {
                System.out.println();
                System.out.println("-Vuelo: " + temp.numeroVuelo +
                        " | Fecha: " + temp.fecha +
                        " | Destino: " + temp.destino);
                if (temp.listaPasajeros == null) {
                    System.out.println("No hay Pasajeros agendados para este viaje");
                } else {
                    NodoInterno tempInterno = temp.listaPasajeros;
                    while (tempInterno != null) {
                        System.out.println("." + tempInterno.getNombre());
                        tempInterno = tempInterno.getNext();
                    }
                }
                temp = temp.getNext();

            }
        }
    }

    //2.Pila
    public void procesoCancelacion(String nombrePasajero) {
        if (head == null) {
            System.out.println("Error: No hay Vuelos programados.");
            return;
        } else {
            NodoExterno tempExt = head;
            while (tempExt != null) {
                if (tempExt.listaPasajeros != null) {
                    NodoInterno tempInt = tempExt.listaPasajeros;
                    if (tempInt.getNombre().equalsIgnoreCase(nombrePasajero)) {
                        pila.push(tempInt, tempExt.getNumeroVuelo());
                        System.out.println("El pasajero " + tempInt.getNombre() + " ha sido cancelado de la lista de pasajeros.");
                        tempExt.listaPasajeros = tempInt.getNext();
                        return;
                    }
                    while (tempInt != null) {
                        if (tempInt.getNext() != null && tempInt.getNext().getNombre().equalsIgnoreCase(nombrePasajero)) {
                            NodoInterno tempInt2 = tempInt.getNext();
                            System.out.println("El pasajero " + tempInt2.getNombre() + " ha sido cancelado de la lista de pasajeros.");
                            tempInt.setNext(tempInt2.getNext());
                            pila.push(tempInt2, tempExt.getNumeroVuelo());
                            return;
                        }
                        tempInt = tempInt.getNext();
                    }
                }
                tempExt = tempExt.getNext();
            }
            System.out.println("Error: No se encontró al pasajero en los vuelos registrados.");
        }
    }

    public void revertirCancelacion() {
        if (pila.isEmpty()) {
            System.out.println("Error: No se encontraron pasajeros en la lista de cancelacion");
            return;
        }
        Nodo<NodoInterno> nodoNuevo = pila.pop();
        NodoInterno X = nodoNuevo.getInfo();
        agregarPasajero(nodoNuevo.getInfoVuelo(), X.getNumeroPasaporte(), X.getNombre(), X.getPais(), X.getEdad(), X.getSexo());
        System.out.println("Cancelación revertida: " + X.getNombre() + ", Vuelve al vuelo: " + nodoNuevo.getInfoVuelo());
    }

    public void mostrarPasajerosCancelados() {
        if (pila.isEmpty()) {
            System.out.println("Error: No hay pasajeros cancelados");
            return;
        }
        Nodo<NodoInterno> temp = pila.getHead();
        System.out.println("Pasajeros Cancelados: ");
        while (temp != null) {
            System.out.println("." + temp.getInfo().getNombre());
            temp = temp.getNext();
        }
    }

    //3.Cola
    public void metodoPrioridad(String sexo) {
        if (head == null) {
            return;
        } else {
            NodoExterno temp = head;
            while (temp != null) {
                if (temp.listaPasajeros != null) {
                    NodoInterno tempInt = temp.listaPasajeros;
                    while (tempInt != null) {
                        if (tempInt.getSexo().equalsIgnoreCase(sexo)) {
                            cola.add(tempInt, temp.getNumeroVuelo());
                        }
                        tempInt = tempInt.getNext();
                    }
                }
                temp = temp.getNext();
            }
        }
    }

    public void agregarCola() {
        cola.clear();
        metodoPrioridad("F");
        metodoPrioridad("M");
    }

    public void mostrarCola(String numeroVuelo) {
        if (cola.isEmpty()) {
            System.out.println("No hay elementos en la cola");
            return;
        } else {
            Nodo<NodoInterno> nodo = cola.getFirst();
            while (nodo != null) {
                if (nodo.getInfoVuelo().equalsIgnoreCase(numeroVuelo)) {
                    System.out.println("." + nodo.getInfo().getNombre());
                }
                nodo = nodo.getNext();
            }
        }
    }

    //4.Metodos Recursivos

    public int metodoContadorMenores(NodoInterno pasajero) {
        if (pasajero == null) { //Caso Base: Nodo Externo == null
            return 0;
        } else { //Caso Recursivo
            if (pasajero.getEdad() < 16) {
                return 1 + metodoContadorMenores(pasajero.getNext());
            } else {
                return metodoContadorMenores(pasajero.getNext());
            }
        }
    }

    public int metodoContadorMenoresVuelos(NodoExterno vuelos) {
        if (vuelos == null) {
            return 0;
        } else {
            int contador = metodoContadorMenores(vuelos.getListaPasajeros());
            if (contador > 10) {
                return 1 + metodoContadorMenoresVuelos(vuelos.getNext());
            } else {
                return metodoContadorMenoresVuelos(vuelos.getNext());
            }
        }
    }

    public int metodoRecursivo() {
        return metodoContadorMenoresVuelos(head);
    }

}
