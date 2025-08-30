
public class Perro extends Animal {

	private boolean pulgas;
	
	public Perro (int patas, int ojos, boolean pulgas) {
		super (patas, ojos);
		this.pulgas = pulgas;
	}
	
	public void setPulgas(boolean pulgas) {
		this.pulgas = pulgas;
	}
	
	public boolean getPulgas () {
		return pulgas;
	}
	
	public void cambiaPadre () {
		this.patas = 3;
		setOjos(5);
		setPatas(4);
		setOjos(2);
	}
}
