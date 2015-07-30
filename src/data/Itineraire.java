package data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import data.Ilotdepassage;

public class Itineraire {
	private int id;
	private Date Planification_date;
	private int Camion_id;
	private int longueur;
	private int volumetotal;
	private int poidstotal;
	private int Typedechets_id;
	private List<Ilotdepassage> ilotsdepassage;

	public Itineraire() {
		ilotsdepassage = new LinkedList<Ilotdepassage>();
	}
	public Itineraire(int t,int c) {
		this.Typedechets_id = t;
		this.Camion_id = c;
		ilotsdepassage = new LinkedList<Ilotdepassage>();
	}
	public int get_id() {
		return this.id;
	}
	public Date get_Planification_date() {
		return this.Planification_date;
	}
	public int get_Camion_id() {
		return this.Camion_id;
	}
	public int get_longueur() {
		return this.longueur;
	}
	public int get_volumetotal() {
		return this.volumetotal;
	}
	public int get_poidstotal() {
		return this.poidstotal;
	}
	public int get_Typedechets_id() {
		return this.Typedechets_id;
	}
	public List<Ilotdepassage> get_ilotsdepassage() {
		return this.ilotsdepassage;
	}
	public void set_id(int a) {
		this.id=a;
	}
	public void set_Planification_date(Date a) {
		this.Planification_date=a;
	}
	public void set_Camion_id(int a) {
		this.Camion_id=a;
	}
	public void set_longueur(int a) {
		this.longueur=a;
	}
	public void set_volumetotal(int a) {
		this.volumetotal=a;
	}
	public void set_poidstotal(int a) {
		this.poidstotal=a;
	}
	public void set_Typedechets_id(int a) {
		this.Typedechets_id=a;
	}
	public void set_ilotsdepassage(List<Ilotdepassage> li) {
		this.ilotsdepassage = li;
	}
	public void ajouterilot(Ilot ilot,int ordre) {
		this.ilotsdepassage.add(new Ilotdepassage(ilot,ordre));
	}
}
