package relaciones;
//Esta clase se utiliza coo parte de la clase Libro
public class Pagina {
	//Contenido (texto) de la página
	private String contenido;
	private int numero;
	//Atributos de instancia private String contenido
	//Constructor que inicializa una página con su contenido y número
	public Pagina (String contenido, int numero) {
		this.contenido = new String (contenido);
		this.numero = numero;
	}
	//Metodos de instancia
	public String getContenido() {
		return this.contenido;
	}
	public void setContenido (String nuevo_contenido) {
		this.contenido = nuevo_contenido;
	}
	public int getNumero () {
		return this.numero;
	}
	public void setNumero (int nuevo_numero) {
		this.numero = nuevo_numero;
	}
	

}
