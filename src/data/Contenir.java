package data;

public class Contenir {
	private int Camion_id;
	private int Typebenne_id;
	
	public Contenir(int c,int t) {
		this.Camion_id=c;
		this.Typebenne_id=t;
	}
	public int get_Camion_id(){
		return this.Camion_id;
	}
	public int get_Typebenne_id(){
		return this.Typebenne_id;
	}
}
