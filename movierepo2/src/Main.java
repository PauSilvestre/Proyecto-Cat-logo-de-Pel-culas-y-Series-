import clases.Pelicula;
import clases.GestorCatalogo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorCatalogo gestor = new GestorCatalogo();

        int opcion;
        do {
            System.out.println("========================================");
            System.out.println("   CATÁLOGO DE PELÍCULAS Y SERIES");
            System.out.println("========================================");
            System.out.println("1. Añadir película/serie");
            System.out.println("2. Ver catálogo completo");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch(opcion) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Director: ");
                    String director = scanner.nextLine();
                    System.out.print("Año: ");
                    int año = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Género: ");
                    String genero = scanner.nextLine();
                    System.out.print("Valoración (1-5): ");
                    int valoracion = scanner.nextInt();
                    scanner.nextLine();

                    gestor.añadirPelicula(new Pelicula(titulo, director, año, genero, valoracion));
                    System.out.println("Añadido correctamente.");
                    break;

                case 2:
                    System.out.println("Catálogo:");
                    gestor.verCatalogo().forEach(System.out::println);
                    break;

                case 0:
                    System.out.println("Saliendo");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while(opcion != 0);

        scanner.close();
    }
}
