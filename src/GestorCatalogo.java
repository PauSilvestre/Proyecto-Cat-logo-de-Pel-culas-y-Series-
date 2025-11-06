import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import static java.lang.Thread.sleep;

public class GestorCatalogo implements Serializable{
    private static Scanner sc = new Scanner(System.in);

    private static ArrayList<Pelicula> peliculas = new ArrayList<>();

    static void addPelicula() {

        String titulo;
        String director;
        int anyo;
        String genero;
        double valoracion;

        while (true) {

            System.out.print("Titulo: ");
            titulo = sc.nextLine();

            System.out.print("Director: ");
            director = sc.nextLine();

            System.out.print("Año: ");
            while (!sc.hasNextInt()) {

                System.out.println("Introduce un numero valido");
                System.out.print("Año: ");
                sc.nextLine();
            }
            anyo = sc.nextInt();
            sc.nextLine();

            System.out.print("Genero: ");
            genero = sc.nextLine();

            System.out.print("Valoracion: ");
            while (!sc.hasNextDouble()) {

                System.out.println("Introduce un numero valido");
                System.out.print("Valoracion: ");
                sc.nextLine();
            }
            valoracion = sc.nextDouble();
            sc.nextLine();

            peliculas.add(new Pelicula(titulo, director, anyo, genero, valoracion));
            System.out.println("Guardado correctamente\n");
            break;
        }
    }

    static void showPeliculas() throws InterruptedException {

        for (Pelicula pelicula : peliculas) {

            System.out.println(pelicula);
        }
        sleep(300);
    }

    static void searchPeliculasTitulo() throws InterruptedException {

        System.out.print("Titulo: ");
        String filtro = sc.nextLine().toLowerCase();

        for (Pelicula pelicula : peliculas) {

            if (pelicula.getTitulo()
                    .toLowerCase()
                    .contains(filtro)) {

                System.out.println(pelicula);
            }
        }
        System.out.println();
    }

    static void searchPeliculasGenero() throws InterruptedException {

        System.out.print("Genero: ");
        String filtro = sc.nextLine().toLowerCase();

        for (Pelicula pelicula : peliculas) {

            if (pelicula.getGenero()
                    .toLowerCase()
                    .contains(filtro)) {

                System.out.println(pelicula);
            }
        }
        System.out.println();
    }

    static void searchPeliculasDirector() throws InterruptedException {

        System.out.print("Director: ");
        String filtro = sc.nextLine().toLowerCase();

        for (Pelicula pelicula : peliculas) {

            if (pelicula.getDirector()
                    .toLowerCase()
                    .contains(filtro)) {

                System.out.println(pelicula);
            }
        }
        System.out.println();
    }

    static void removePelicula() {

        System.out.print("ID (-1 Para Cancelar): ");
        int id;
        while (!sc.hasNextInt()) {

            System.out.print("ID (-1 Para Cancelar): ");
            sc.nextLine();
        }
        id = sc.nextInt();
        sc.nextLine();

        if (id == -1) {return;}


        boolean removed = peliculas.removeIf(p -> p.getID() == id);

        if (removed) {
            System.out.println("Película eliminada correctamente.");
        } else {
            System.out.println("No se encontró ninguna película con el ID: " + id);
        }
    }

    static void modifyPelicula() {

        System.out.print("ID (-1 Para Cancelar): ");
        int id;
        while (!sc.hasNextInt()) {

            System.out.print("ID (-1 Para Cancelar): ");
            sc.nextLine();
        }
        id = sc.nextInt();
        sc.nextLine();

        if (id == -1) {return;}

        for (Pelicula pelicula : peliculas) {

            if (pelicula.getID() == id) {

                String titulo;
                String director;
                int anyo;
                String genero;
                double valoracion;

                while (true) {

                    System.out.print("Titulo: ");
                    titulo = sc.nextLine();

                    System.out.print("Director: ");
                    director = sc.nextLine();

                    System.out.print("Año: ");
                    while (!sc.hasNextInt()) {

                        System.out.println("Introduce un numero valido");
                        System.out.print("Año: ");
                        sc.nextLine();
                    }
                    anyo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Genero: ");
                    genero = sc.nextLine();

                    System.out.print("Valoracion: ");
                    while (!sc.hasNextDouble()) {

                        System.out.println("Introduce un numero valido");
                        System.out.print("Valoracion: ");
                        sc.nextLine();
                    }
                    valoracion = sc.nextInt();
                    sc.nextLine();

                    peliculas.add(new Pelicula(titulo, director, anyo, genero, valoracion, id));
                    System.out.println("Guardado correctamente\n");
                    break;
                }

                peliculas.remove(pelicula);

                sortByID();
                return;
            }
        }

        System.out.println("No se ha encontrado ninguna pelicula con el id: " + id);
    }

    static void showPeliculasRating() throws InterruptedException {

        Collections.sort(peliculas, new Comparator<Pelicula>() {
            @Override
            public int compare(Pelicula o1, Pelicula o2) {
                return Double.compare(o2.getValoracion(), o1.getValoracion());
            }
        });

        showPeliculas();
        sortByID();
    }

    static void savePeliculas() {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("datos/peliculas.bin");
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)
        )
        {

            if (!new File("peliculas.bin").isFile()) {

                new File("peliculas.bin").createNewFile();
            }

            out.writeObject(peliculas);
            System.out.println("Películas guardadas correctamente en 'datos/peliculas.bin'");
        }
        catch (IOException e) {
            System.err.println("Error al guardar las películas: " + e.getMessage());
        }
    }

    static void loadPeliculas() throws FileNotFoundException {

        if (!new File("peliculas.bin").isFile()) {

            return;
        }

        try (
                FileInputStream fileInputStream = new FileInputStream("datos/peliculas.bin");
                ObjectInputStream in = new ObjectInputStream(fileInputStream)
        )
        {
            peliculas = (ArrayList<Pelicula>) in.readObject();
            System.out.println("Películas cargadas correctamente.");
        }
        catch (IOException e) {
            System.err.println("Error al guardar las películas: " + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.err.println("Error al guardar las películas: " + e.getMessage());
            throw new RuntimeException(e);
        }

        Pelicula.setContadorId(peliculas.getLast().getID() + 1);
        sortByID();
    }

    static void sortByID() {

        Collections.sort(peliculas, new Comparator<Pelicula>() {
            @Override
            public int compare(Pelicula o1, Pelicula o2) {
                return Integer.compare(o1.getID(), o2.getID());
            }
        });
    }
}
