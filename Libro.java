class Libro {
    private String titulo;
    private String autor;
    private String clasificacion;
    private boolean enAlquiler;
    private int Nlibros;

    public Libro(String titulo, String autor, String clasificacion, int Nlibros) {
        this.titulo = titulo;
        this.autor = autor;
        this.clasificacion = clasificacion;
        this.enAlquiler = true; // Se asume que al principio todos están disponibles
        this.Nlibros = Nlibros;

    }

    public boolean estaDisponible() {

        return enAlquiler;
    }

    public void setEstadoAlquiler(boolean estado) {

        this.enAlquiler = estado;
    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {

        return autor;
    }

    public void setAutor(String autor) {

        this.autor = autor;
    }

    public String getClasificacion() {

        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {

        this.clasificacion = clasificacion;
    }
    public int getNlibros(){

        return Nlibros;
    }
    public void setNlibros(int Nlibros){

        this.Nlibros = Nlibros;
        this.enAlquiler = Nlibros > 0;
    }
}
