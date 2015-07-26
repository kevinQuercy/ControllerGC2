package data;

public class Ilot {
	private int id;
	private String adresse;
	private int codepostal;
	private String ville;
	private int Contact_id;

	public int get_id() {
		return this.id;
	}
	public String get_adresse() {
		return this.adresse;
	}
	public int get_codepostal() {
		return this.codepostal;
	}
	public String get_ville() {
		return this.ville;
	}
	public int get_Contact_id() {
		return this.Contact_id;
	}
	public void set_id(int a) {
		this.id=a;
	}
	public void set_adresse(String a) {
		this.adresse=a;
	}
	public void set_codepostal(int a) {
		this.codepostal = a; 
	}
	public void set_ville(String a) {
		this.ville=a;
	}
	public void set_Contact_id(int a) {
		this.Contact_id=a;
	}
}
