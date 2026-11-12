public class NodoInterno {
    String numeroPasaporte;
    String nombre;
    String pais;
    int edad;
    String sexo;
    NodoInterno next;

    public NodoInterno(String numeroPasaporte, String nombre, String pais, int edad, String sexo){
        this.numeroPasaporte = numeroPasaporte;
        this.nombre = nombre;
        this.pais = pais;
        this.edad = edad;
        this.sexo = sexo;
        this.next = null;
    }

    public String getNumeroPasaporte() {
        return numeroPasaporte;
    }

    public void setNumeroPasaporte(String numeroPasaporte) {
        this.numeroPasaporte = numeroPasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public NodoInterno getNext() {
        return next;
    }

    public void setNext(NodoInterno next) {
        this.next = next;
    }
}
