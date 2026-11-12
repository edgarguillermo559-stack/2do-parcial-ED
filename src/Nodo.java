public class Nodo<T> {
    T info;
    String infoVuelo;
    Nodo<T> next;

    public Nodo(T info, String infoVuelo){
        this.infoVuelo = infoVuelo;
        this.info = info;
        this.next = null;
    }

    public String getInfoVuelo() {
        return infoVuelo;
    }

    public void setInfoVuelo(String infoVuelo) {
        this.infoVuelo = infoVuelo;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public Nodo<T> getNext() {
        return next;
    }

    public void setNext(Nodo<T> next) {
        this.next = next;
    }
}
