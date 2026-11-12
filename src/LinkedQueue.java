public class LinkedQueue<T> {
    private Nodo<T> first;
    private Nodo<T> last;
    private int size;

    public LinkedQueue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public Nodo<T> getFirst() {
        return first;
    }

    public void setFirst(Nodo<T> first) {
        this.first = first;
    }

    public Nodo<T> getLast() {
        return last;
    }

    public void setLast(Nodo<T> last) {
        this.last = last;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void add(T elem, String infoVuelo) {
        Nodo<T> nodoNuevo = new Nodo<>(elem, infoVuelo);
        if (isEmpty()) {
            first = nodoNuevo;
            last = nodoNuevo;
        } else {
            last.setNext(nodoNuevo);
            last = nodoNuevo;
        }
        size++;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return first.getInfo();
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T info = first.getInfo();
        first = first.getNext();
        if (first == null) {
            last = null;
        }
        size--;
        return info;
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public int size() {
        return size;
    }
}