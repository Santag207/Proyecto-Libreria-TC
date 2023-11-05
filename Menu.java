import java.util.Scanner;

class Menu {
    private Biblioteca biblioteca;

    public Menu() {
        this.biblioteca = new Biblioteca();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Buscar por título");
            System.out.println("2. Buscar por clasificación");
            System.out.println("3. Alquilar libro");
            System.out.println("4. Agregar libro");
            System.out.println("0. Salir");
            System.out.print("Ingrese la opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título a buscar: ");
                    String titulo = scanner.nextLine();
                    biblioteca.buscarLibroPorTitulo(titulo).forEach(libro -> {
                        System.out.println(libro.getTitulo() + " - " + libro.getAutor() + " - "+libro.getClasificacion());
                    });
                    break;
                case 2:
                    System.out.print("Ingrese la clasificación a buscar: ");
                    String clasificacion = scanner.nextLine();
                    biblioteca.buscarPorClasificacion(clasificacion).forEach(libro -> {
                        System.out.println(libro.getClasificacion() + " - " + libro.getAutor() + " - " + libro.getTitulo());
                    });
                    break;
                case 3:
                    System.out.print("Ingrese el título del libro a alquilar: ");
                    String tituloAlquiler = scanner.nextLine();
                    biblioteca.alquilarLibro(tituloAlquiler);
                    break;

                case 4:
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

        System.out.println("Ingrese el tema del libro:");
        String tema = scanner.nextLine();

        System.out.println("Ingrese el número de libros:");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        Libro libro = new Libro(titulo, autor, tema, cantidad);
        biblioteca.agregarLibro(libro);
        System.out.println("Libro agregado con éxito a la biblioteca.");
    }
}