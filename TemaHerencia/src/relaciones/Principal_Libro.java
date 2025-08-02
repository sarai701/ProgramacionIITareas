package relaciones;


 //Clase principal que prueba la funcionalidad de la clase Libro y su relación  con la clase Pagina.
public class Principal_Libro {

    public static void main(String[] args) {
        // Creación del objeto 'Libro'
        Libro elQuijote = new Libro("Don Quijote de la Mancha", 0, null, 0);

        // Registrar contenido en páginas específicas
        elQuijote.registrarPagina("En un lugar de la Mancha", 0);
        elQuijote.registrarPagina("... no ha mucho tiempo que vivir", 1);

        // Mostrar el número total de páginas del libro
        System.out.println("Número total de páginas: " + elQuijote.getNumeroPaginas());
        System.out.println("Contenido del libro:");

        // Mostrar solo las páginas que tienen contenido, indicando su número
        for (int i = 0; i < elQuijote.getNumeroPaginas(); i++) {
            String contenido = elQuijote.getPaginaNumero(i);
            if (!contenido.isEmpty()) {
                System.out.println("Página " + i + ": " + contenido);
            }
        }
    }
}


