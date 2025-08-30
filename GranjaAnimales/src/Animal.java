
public class Animal {
	protected int patas;
	private int ojos;
	
	public Animal (int patas, int ojos) {
		this.patas = patas;
		this.ojos = ojos;
	}
	
	public void setPatas (int patas) {
		this.patas = patas;
	}
	public int getPatas() {
		return patas;
	}
	
	public void setOjos (int ojos) {
		this.ojos = ojos;
	}
	public int getOjos () {
		return ojos;
	}
	public String habla() {
		return "Soy un animal"; 
	}

}
