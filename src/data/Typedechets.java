package data;

public class Typedechets {
	private int id;
	private String libelle;

	public Typedechets(int i,String l){
		this.id=i;
		this.libelle=l;
	}
	public int get_id(){
		return this.id;
	}
	public String get_libelle(){
		return this.libelle;
	}
}
