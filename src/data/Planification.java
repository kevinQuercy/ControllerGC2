package data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import data.Itineraire;

public class Planification {
	private int id;
	private Date date;
	private int taux;
	private List<Itineraire> itineraires;

	public Planification(){
		itineraires = new LinkedList<Itineraire>();
	}
	public void set_id(int a) {
		this.id=a;
	}
	public void set_date(Date a) {
		this.date=a;
	}
	public void set_taux(int a) {
		this.taux=a;
	}
	public void set_itineraires(List<Itineraire> li) {
		this.itineraires=li;
	}
	public int get_id() {
		return this.id;
	}
	public Date get_date() {
		return this.date;
	}
	public int get_taux() {
		return this.taux;
	}
	public List<Itineraire> get_itineraires() {
		return this.itineraires;
	}
	public void ajouteritineraire(Itineraire i) {
		i.set_Planification_date(this.get_date());
		this.itineraires.add(i);
	}
}