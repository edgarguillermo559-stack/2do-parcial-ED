public class NodoCola {
    NodoInterno info;
    NodoCola next;

    public NodoCola(NodoInterno info){
        this.info = info;
        this.next = null;
    }

    public NodoInterno getInfo() {
        return info;
    }

    public void setInfo(NodoInterno info) {
        this.info = info;
    }

    public NodoCola getNext() {
        return next;
    }

    public void setNext(NodoCola next) {
        this.next = next;
    }
}
