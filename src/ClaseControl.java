public class ClaseControl {
    NodoExterno head;
    NodoCancelacion headPila;
    NodoCola first;
    NodoCola last;

    public ClaseControl() {
        this.head = null;
        this.headPila = null;
        this.first = null;
        this.last = null;
    }


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
                System.out.println("-Vuelo: " + temp.numeroVuelo + " Destino: " + temp.destino);
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

    public boolean isEmptyPila() {
        return headPila == null;
    }

    public void push(NodoInterno pasajero, String infoVuelo) {
        NodoCancelacion nodoNuevo = new NodoCancelacion(pasajero, infoVuelo);
        if (isEmptyPila()) {
            headPila = nodoNuevo;
        } else {
            nodoNuevo.setNext(headPila);
            headPila = nodoNuevo;
        }
    }

    public NodoCancelacion top() {
        if (isEmptyPila()) {
            return null;
        } else {
            return headPila;
        }
    }

    public NodoCancelacion pop() {
        if (isEmptyPila()) {
            return null;
        } else {
            NodoCancelacion temp;
            temp = headPila;
            headPila = headPila.getNext();
            return temp;
        }
    }

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
                        push(tempInt, tempExt.getNumeroVuelo());
                        System.out.println("El pasajero " + tempInt.getNombre() + " ha sido cancelado de la lista de pasajeros.");
                        tempExt.listaPasajeros = tempInt.getNext();
                        return;
                    }
                    while (tempInt != null) {
                        if (tempInt.getNext() != null && tempInt.getNext().getNombre().equalsIgnoreCase(nombrePasajero)) {
                            NodoInterno tempInt2 = tempInt.getNext();
                            System.out.println("El pasajero " + tempInt2.getNombre() + " ha sido cancelado de la lista de pasajeros.");
                            tempInt.setNext(tempInt2.getNext());
                            push(tempInt2, tempExt.getNumeroVuelo());
                            return;
                        }
                        tempInt = tempInt.getNext();
                    }
                }
                tempExt = tempExt.getNext();
            }
            System.out.println("Error: No se encontro al Pasajero en los Vuelos registrados.");
        }
    }

    public void revertirCancelacion() {
        if (isEmptyPila()) {
            System.out.println("Error: No se encontraron pasajeros en la lista de cancelacion");
            return;
        }
        NodoCancelacion nodoNuevo = pop();
        NodoInterno X = nodoNuevo.getInfo();
        agregarPasajero(nodoNuevo.getInfoVuelo(), X.getNumeroPasaporte(), X.getNombre(), X.getPais(), X.getEdad(), X.getSexo());
        System.out.println("Cancelación revertida: " + X.getNombre() + ", Vuelve al vuelo: " + nodoNuevo.getInfoVuelo());
    }

    public void mostrarPasajerosCancelados() {
        NodoCancelacion temp = headPila;
        if (isEmptyPila()) {
            System.out.println("Error: No hay pasajeros cancelados");
            return;
        }
        System.out.println("Pasajeros Cancelados: ");
        while (temp != null) {
            System.out.println("." + temp.getInfo().getNombre());
            temp = temp.getNext();
        }
    }

    //3.Cola
    public boolean isEmpetyCola(){
        return first == null;
    }

    public void add(NodoInterno elem){
        NodoCola nodoNuevo = new NodoCola(elem);
        if(isEmpetyCola()){
            first = nodoNuevo;
            last = nodoNuevo;
        }else{
            last.setNext(nodoNuevo);
            last = nodoNuevo;
        }
    }

    public NodoInterno peek(){
        if(isEmpetyCola()){
            return null;
        }else{
            return first.getInfo();
        }
    }

    public NodoInterno poll(){
        if(isEmpetyCola()){
            return null;
        }else {
            NodoInterno nodo = first.getInfo();
            first = first.getNext();
            return nodo;
        }
    }

    //4.Metodos Recursivos


    //Ejemplo de como Seria el Metodo Sin Usar recursividad
    public void metodoNoRecursivo() {
        int contador;
        if (head == null) {
            System.out.println("Error: No hay Vuelos programados.");
            return;
        } else {
            NodoExterno tempExt = head;
            while (tempExt != null) {
                contador = 0;
                if (tempExt.listaPasajeros != null) {
                    NodoInterno tempInt = tempExt.listaPasajeros;
                    while (tempInt != null) {
                        if (tempInt.getEdad() < 16) {
                            contador++;
                        }
                        tempInt = tempInt.getNext();
                    }
                    if (contador >= 10) {
                        System.out.println("El vuelo es de riesgo");
                    }
                }
                tempExt = tempExt.getNext();
            }
            System.out.println("Error: No se encontro al Pasajero en los Vuelos registrados.");
        }
    }

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
