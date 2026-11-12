public class LinkedStack<T> {
    Nodo<T> head;

    public LinkedStack(){
        this.head = null;
    }

    public Nodo<T> getHead() {
        return head;
    }

    public void setHead(Nodo<T> head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return getHead() == null;
    }

    public void push(T pasajero, String infoVuelo) {
        Nodo<T> nodoNuevo = new Nodo<>(pasajero, infoVuelo);
        if (isEmpty()) {
            head = nodoNuevo;
        } else {
            nodoNuevo.setNext(head);
            setHead(nodoNuevo);
        }
    }

    public Nodo<T> top() {
        if (isEmpty()) {
            return null;
        } else {
            return head;
        }
    }

    public Nodo<T> pop() {
        if (isEmpty()) {
            return null;
        } else {
            Nodo<T> temp = head;
            head = head.getNext();
            return temp;
        }
    }
}
