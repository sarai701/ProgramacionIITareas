
public class Loro extends Animal {
	
	public Loro(int patas, int ojos) {
		super (patas, ojos);
	}
	
	public String habla() {
		return "Soy un Loro y " + super.habla();
	}

}
