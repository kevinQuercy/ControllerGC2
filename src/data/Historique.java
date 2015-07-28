package data;
import java.util.Date;

public class Historique {
	private int id;
	private int Conteneur_id;
	private Date date = new java.util.Date(); ;
	private int poids;
	private int volume;

	public Historique(int contid,Date d,int p,int v) {
		this.Conteneur_id=contid;
		this.date=d;
		this.poids=p;
		this.volume=v;
	}
	public Historique(){
	}
	public int get_id() {
		return this.id;
	}
	public int get_Conteneur_id() {
		return this.Conteneur_id;
	}
	public Date get_date() {
		return this.date;
	}
	public int get_poids() {
		return this.poids;
	}
	public int get_volume() {
		return this.volume;
	}
	public void set_id(int a) {
		this.id=a;
	}
	public void set_Conteneur_id(int a) {
		this.Conteneur_id=a;
	}
	public void set_date(Date a) {
		this.date = a; 
	}
	public void set_poids(int a) {
		this.poids=a;
	}
	public void set_volume(int a) {
		this.volume=a;
	}
}
