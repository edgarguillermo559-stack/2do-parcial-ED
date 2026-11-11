public class NodoExterno {
    String numeroVuelo;
    String fecha;
    String destino;
    NodoExterno next;
    NodoInterno listaPasajeros;

    public NodoExterno(String numeroVuelo, String fecha, String destino) {
        this.numeroVuelo = numeroVuelo;
        this.fecha = fecha;
        this.destino = destino;
        this.next = null;
        this.listaPasajeros = null;
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public NodoExterno getNext() {
        return next;
    }

    public void setNext(NodoExterno next) {
        this.next = next;
    }

    public NodoInterno getListaPasajeros() {
        return listaPasajeros;
    }

    public void setListaPasajeros(NodoInterno listaPasajeros) {
        this.listaPasajeros = listaPasajeros;
    }
}
