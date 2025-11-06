import java.io.Serializable;

public class Pelicula implements Serializable {

    private static int contadorId = 0;
    private int id;
    private String titulo;
    private String director;
    private int anyo;
    private String genero;
    private double valoracion;

    public Pelicula(String titulo, String director, int anyo, String genero, double valoracion) {
        id = contadorId;
        this.titulo = titulo;
        this.director = director;
        this.anyo = anyo;
        this.genero = genero;
        this.valoracion = valoracion;
        contadorId++;
    }

    public Pelicula(String titulo, String director, int anyo, String genero, double valoracion, int id) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anyo = anyo;
        this.genero = genero;
        this.valoracion = valoracion;
    }

    @Override
    public String toString() {
        return  "======================================\n" +
                " PELICULA\n" +
                " Titulo = " + titulo + "\n" +
                " Director = " + director + "\n" +
                " Año = " + anyo + "\n" +
                " Genero = " + genero + "\n" +
                " Valoracion = " + valoracion + "\n" +
                " ID = " + id + "\n";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public int getID() {
        return id;
    }

    public static void setContadorId(int newId) {contadorId = newId;}
}
