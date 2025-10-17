package clases;

public class Pelicula {
    private String titulo;
    private String director;
    private int año;
    private String genero;
    private int valoracion;

    public Pelicula(String titulo, String director, int año, String genero, int valoracion) {
        this.titulo = titulo;
        this.director = director;
        this.año = año;
        this.genero = genero;
        this.valoracion = valoracion;
    }
}