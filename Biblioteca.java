import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Biblioteca {
    private List<Libro> inventario;

    public Biblioteca() {
        this.inventario = new ArrayList<>();
        // Inicializar con libros manualmente
        inventario.add(new Libro("Clean Code", "Robert C. Martin", "Programación", 2));
        inventario.add(new Libro("Cien años de soledad", "Gabriel García Márquez", "Ficción", 5));
        inventario.add(new Libro("Clean Code", "Robert C. Martin", "Programación", 2));
        inventario.add(new Libro("Cien años de soledad", "Gabriel García Márquez", "Ficción", 5));
        inventario.add(new Libro("The Catcher in the Rye", "J.D. Salinger", "Ficción", 3));
        inventario.add(new Libro("The Great Gatsby", "F. Scott Fitzgerald", "Ficción", 4));
        inventario.add(new Libro("To Kill a Mockingbird", "Harper Lee", "Ficción", 2));
        inventario.add(new Libro("1984", "George Orwell", "Ficción", 6));
        inventario.add(new Libro("The Hobbit", "J.R.R. Tolkien", "Fantasía", 3));
        inventario.add(new Libro("The Lord of the Rings", "J.R.R. Tolkien", "Fantasia", 4));
        inventario.add(new Libro("Brave New World", "Aldous Huxley", "Ciencia ficción", 7));
        inventario.add(new Libro("Foundation", "Isaac Asimov", "Ciencia ficción", 2));
        inventario.add(new Libro("The Martian", "Andy Weir", "Ciencia ficción", 8));
        inventario.add(new Libro("The Da Vinci Code", "Dan Brown", "Misterio", 5));
        inventario.add(new Libro("The Girl with the Dragon Tattoo", "Stieg Larsson", "Misterio", 3));
        inventario.add(new Libro("Gone Girl", "Gillian Flynn", "Misterio", 6));
        inventario.add(new Libro("The Silent Patient", "Alex Michaelides", "Misterio", 4));
        // Libros a añadir manualmente.......
    }

    public void agregarLibro(Libro libro) {
        inventario.add(libro);
    }

    public List<Libro> buscarPorClasificacion(String clasificacion) {
        return inventario.stream()
                .filter(libro -> libro.getClasificacion().equalsIgnoreCase(clasificacion))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarPorAutor(String autor) {
        return inventario.stream()
                .filter(libro -> libro.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarLibroPorTitulo(String titulo) {
        return inventario.stream()
                .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
    }

    public void alquilarLibro(String titulo) {
        List<Libro> libroBuscado = buscarLibroPorTitulo(titulo);
        if (!libroBuscado.isEmpty()) {
            Libro libro = libroBuscado.get(0);
            if (libro.estaDisponible()) {
                int existencias = libro.getNlibros();
                if (existencias > 0) {
                    libro.setNlibros(existencias - 1); // Disminuir la cantidad de libros
                    System.out.println("Libro alquilado: " + titulo);
                } else {
                    System.out.println("El libro no está disponible para alquiler.");
                }
            } else {
                System.out.println("El libro ya está alquilado.");
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    public List<String> obtenerAutoresUnicos() {
        Set<String> autoresSet = new HashSet<>(); // Utilizamos un conjunto para asegurar autores únicos

        // Obtener autores únicos a partir del inventario de libros
        for (Libro libro : inventario) {
            autoresSet.add(libro.getAutor());
        }

        // Convertir el conjunto de autores únicos a una lista y devolverla
        return new ArrayList<>(autoresSet);
    }

    public List<Libro> obtenerTodosLosLibros() {
        return new ArrayList<>(inventario);
    }
}
