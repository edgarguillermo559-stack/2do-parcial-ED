public class NodoCancelacion {
    NodoInterno info;
    String infoVuelo;
    NodoCancelacion next;

    public NodoCancelacion(NodoInterno info, String infoVuelo) {
        this.info = info;
        this.infoVuelo = infoVuelo;
        this.next = null;
    }

    public String getInfoVuelo() {
        return infoVuelo;
    }

    public void setInfoVuelo(String infoVuelo) {
        this.infoVuelo = infoVuelo;
    }

    public NodoInterno getInfo() {
        return info;
    }

    public void setInfo(NodoInterno info) {
        this.info = info;
    }

    public NodoCancelacion getNext() {
        return next;
    }

    public void setNext(NodoCancelacion next) {
        this.next = next;
    }
}
