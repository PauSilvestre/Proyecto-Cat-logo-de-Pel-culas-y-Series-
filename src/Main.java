import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        GestorCatalogo.loadPeliculas();

        Programa:
        while (true) {

            System.out.println("""
                    ========================================
                     CATÁLOGO DE PELÍCULAS Y SERIES
                    ========================================
                    1. Añadir película/serie
                    2. Ver catálogo completo
                    3. Buscar por título
                    4. Filtrar por género
                    5. Filtrar por director
                    6. Eliminar película/serie
                    7. Modificar película/serie
                    8. Ver mejor valoradas
                    0. Salir (guarda automáticamente)
                    ========================================
                    """);

            System.out.print("\nOpcion: ");
            int opcion;
            while (true) {

                while (!sc.hasNextInt()) {

                    System.out.println("Introduce un numero valido");
                    System.out.print("Opcion: ");
                    sc.nextLine();
                }
                opcion = sc.nextInt();

                if (opcion < 0 || opcion > 8) {

                    System.out.println("Introduce un numero valido");
                    System.out.print("Opcion: ");

                    continue;
                }
                break;
            }

            switch (opcion) {

                case 1 -> GestorCatalogo.addPelicula();
                case 2 -> GestorCatalogo.showPeliculas();
                case 3 -> GestorCatalogo.searchPeliculasTitulo();
                case 4 -> GestorCatalogo.searchPeliculasGenero();
                case 5 -> GestorCatalogo.searchPeliculasDirector();
                case 6 -> GestorCatalogo.removePelicula();
                case 7 -> GestorCatalogo.modifyPelicula();
                case 8 -> GestorCatalogo.showPeliculasRating();
                case 0 -> {
                    GestorCatalogo.savePeliculas();
                    break Programa;
                }
            }
        }
    }
}