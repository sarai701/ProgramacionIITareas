package relaciones;
//Contiene la información básica y mantiene un arreglo de objetos Pagina
//que representan el contenido del libro
public class Libro {
    
    // Atributos
    private String titulo;
    private long isbn;
    private String autor;
    private int anyoPublicacion;
    private int numeroPaginas;
    private Pagina[] paginas;
    
    // Constructor, crea un libro con lo básico y reserva el espacio de 999 paginas.
    //Inicializa cada página con contenido y su numero de pagina
    public Libro(String titulo, long isbn, String autor, int anyoPublicacion) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.anyoPublicacion = anyoPublicacion;

        this.paginas = new Pagina[999];

        // Inicializa las páginas vacías )
        for (int i = 0; i < 999; i++) {
            this.paginas[i] = new Pagina("", i);
        }

        this.numeroPaginas = 999; 
    }

    // Métodos getter y setter
    public String getTitulo() { return this.titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public long getisbn() { return this.isbn; }
    public void setisbn(long nuevo_isbn) { this.isbn = nuevo_isbn; }

    public String getAutor() { return this.autor; }
    public void setAutor(String nuevo_autor) { this.autor = nuevo_autor; }

    public int getAnyoPublicacion() { return this.anyoPublicacion; }
    public void setAnyoPublicacion(int nuevo_anyoPublicacion) {
        this.anyoPublicacion = nuevo_anyoPublicacion;
    }

    // Métodos para trabajar con las páginas
    public int getNumeroPaginas() {
        return this.numeroPaginas;
    }

    public void anyadirPagina(Pagina nueva_pagina) {
        if (this.numeroPaginas < 999) {
            this.paginas[this.numeroPaginas] = nueva_pagina;
            this.numeroPaginas++;
        }
    }

    public String getPaginaNumero(int numero_pagina) {
        return this.paginas[numero_pagina].getContenido();
    }

    public void registrarPagina(String contenido, int numero) {
        this.paginas[numero].setContenido(contenido);
    }
}

