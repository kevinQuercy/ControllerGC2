package data;
import java.util.Date;

public class Conteneuravider {
	private int Conteneur_id;
	private int Ilot_id;
	private int Itineraire_id;

	public Conteneuravider() {
	}
	public Conteneuravider(int a) {
		this.Conteneur_id=a;
	}
	public void set_Conteneur_id(int a) {
		this.Conteneur_id=a;
	}
	public void set_Ilot_id(int a) {
		this.Ilot_id=a;
	}
	public void set_Itineraire_id(int a) {
		this.Itineraire_id=a;
	}
	public int Conteneur_id() {
		return this.Conteneur_id;
	}
	public int get_Ilot_id() {
		return this.Ilot_id;
	}
	public int get_Itineraire_id() {
		return this.Itineraire_id;
	}
}