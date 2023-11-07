import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.net.PasswordAuthentication;
import java.net.*;


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
        inventario.add(new Libro("The Lord of the Rings", "J.R.R. Tolkien", "Fantasía", 4));
        inventario.add(new Libro("Brave New World", "Aldous Huxley", "Ciencia ficción", 7));
        inventario.add(new Libro("Foundation", "Isaac Asimov", "Ciencia ficción", 2));
        inventario.add(new Libro("The Martian", "Andy Weir", "Ciencia ficción", 8));
        inventario.add(new Libro("The Da Vinci Code", "Dan Brown", "Misterio", 5));
        inventario.add(new Libro("The Girl with the Dragon Tattoo", "Stieg Larsson", "Misterio", 3));
        inventario.add(new Libro("Gone Girl", "Gillian Flynn", "Misterio", 6));
        inventario.add(new Libro("The Silent Patient", "Alex Michaelides", "Misterio", 4));
        // Añadir más libros...
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
            if (libroBuscado.get(0).estaDisponible()) {
                libroBuscado.get(0).setNlibros(libroBuscado.get(0).getNlibros() - 1); // Disminuir la cantidad de libros
                System.out.println("Libro alquilado: " + titulo);
                // Agregar la notificación por correo electrónico
                enviarNotificacionDevolucion(libroBuscado.get(0), new Usuario("usuario@javeriana.edu.co"));
            } else {
                System.out.println("El libro no está disponible para alquiler.");
            }
        } else {
            System.out.println("Libro no encontrado");
        }

        if (libroBuscado.isEmpty() || !libroBuscado.get(0).estaDisponible()) {
            System.out.println("No hay libros disponibles para alquilar.");
        }
    }

    // Método para enviar notificaciones por correo electrónico al devolver un libro
    public void enviarNotificacionDevolucion(Libro libro, Usuario usuario) {
        if (usuario == null) {
            System.out.println("El usuario no está inicializado");
            return;
        }

        final String username = "usuario@javeriana.edu.co"; // Tu dirección de correo
        final String password = "Contraseña123"; // Tu contraseña

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.javeriana.edu.co"); // Host del proveedor
        props.put("mail.smtp.port", "587"); // Puerto SMTP

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getCorreo()));
            message.setSubject("Notificación de devolución de libro");
            message.setText("El libro " + libro.getTitulo() + " ha sido devuelto. Está disponible para préstamo.");

            Transport.send(message);
            System.out.println("Correo enviado correctamente");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
