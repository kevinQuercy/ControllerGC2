package data;

public class Camion {
	private int id;
	private int poidsmax;
	private boolean disponible;

	public Camion(){
	}
	public Camion(int id,int p,boolean d){
		this.id=id;
		this.poidsmax=p;
		this.disponible=d;
	}
	public int get_id(){
		return this.id;
	}
	public int get_poidsmax(){
		return this.poidsmax;
	}
	public boolean get_disponible(){
		return this.disponible;
	}
	public void set_id(int a){
		this.id=a;
	}
	public void set_poidsmax(int a){
		this.poidsmax=a;
	}
	public void set_disponible(boolean a){
		this.disponible=a;
	}
}
