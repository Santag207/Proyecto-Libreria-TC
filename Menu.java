import java.util.List;
import java.util.Scanner;

public class Menu {
    private Biblioteca biblioteca;

    public Menu() {
        this.biblioteca = new Biblioteca();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Buscar libro por clasificación");
            System.out.println("3. Buscar libro por Autor");
            System.out.println("4. Alquilar libro");
            System.out.println("5. Agregar libro");
            System.out.println("0. Salir");
            System.out.print("Ingrese la opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título a buscar: ");
                    String titulo = scanner.nextLine();
                    biblioteca.buscarLibroPorTitulo(titulo).forEach(libro -> {
                        System.out.println(libro.getTitulo() + " - " + libro.getAutor() + " - " + libro.getClasificacion());
                    });
                    break;
                case 2:
                    mostrarClasificaciones();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    System.out.print("Ingrese el título del libro a alquilar:");
                    String tituloAlquiler = scanner.nextLine();
                    biblioteca.alquilarLibro(tituloAlquiler);
                    break;
                case 5:
                    agregarLibro();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 0);
    }

    public void agregarLibro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el título del libro:");
        String titulo = scanner.nextLine();

        System.out.println("Ingrese el autor del libro:");
        String autor = scanner.nextLine();

        System.out.println("Ingrese la clasificación del libro:");
        String clasificacion = scanner.nextLine();

        System.out.println("Ingrese la cantidad de libros:");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        Libro libro = new Libro(titulo, autor, clasificacion, cantidad);
        biblioteca.agregarLibro(libro);
        System.out.println("Libro agregado con éxito a la biblioteca.");
    }

    public void mostrarClasificaciones() {
        // Mostrar la lista de las clasificaciones
        System.out.println("Clasificaciones:");
        System.out.println("1. Programación");
        System.out.println("2. Ficción");
        System.out.println("3. Fantasía");
        System.out.println("4. Ciencia ficción");
        System.out.println("5. Misterio");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de la clasificación a buscar: ");
        int numeroClasificacion = scanner.nextInt();
        scanner.nextLine();

        String clasificacionElegida = obtenerClasificacionPorNumero(numeroClasificacion);
        if (clasificacionElegida != null) {
            biblioteca.buscarPorClasificacion(clasificacionElegida).forEach(libro -> {
                System.out.println(libro.getClasificacion() + " - " + libro.getAutor() + " - " + libro.getTitulo());
            });
        } else {
            System.out.println("Opción de clasificación no válida.");
        }
    }

    public String obtenerClasificacionPorNumero(int numeroClasificacion) {
        switch (numeroClasificacion) {
            // Clasificaciones
            case 1:
                return "Programación";
            case 2:
                return "Ficción";
            case 3:
                return "Fantasía";
            case 4:
                return "Ciencia ficción";
            case 5:
                return "Misterio";
            default:
                return null;
        }
    }

    public void mostrarAutores() {
        List<String> autoresUnicos = biblioteca.obtenerAutoresUnicos();

        // Mostrar los autores con un número asociado
        System.out.println("Autores:");
        for (int i = 0; i < autoresUnicos.size(); i++) {
            System.out.println((i + 1) + ". " + autoresUnicos.get(i));
        }

        // Usuario elije el autor
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número del autor a buscar: ");
        int numeroAutor = scanner.nextInt();
        scanner.nextLine();

        if (numeroAutor > 0 && numeroAutor <= autoresUnicos.size()) {
            String autorElegido = autoresUnicos.get(numeroAutor - 1);
            biblioteca.buscarPorAutor(autorElegido).forEach(libro -> {
                System.out.println(libro.getClasificacion() + " - " + libro.getAutor() + " - " + libro.getTitulo());
            });
        } else {
            System.out.println("Opción de autor no válida.");
        }
    }
}