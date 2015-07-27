package data;

import java.util.LinkedList;
import java.util.List;
import data.Conteneuravider;

public class Ilotdepassage {
	private int Ilot_id;
	private int Itineraire_id;
	private int ordre;
	private List<Conteneuravider> conteneuravider;
	
	public Ilotdepassage() {
		conteneuravider = new LinkedList<Conteneuravider>();
	}
	public Ilotdepassage(int a,int b) {
		conteneuravider = new LinkedList<Conteneuravider>();
		this.Ilot_id=a;
		this.ordre=b;
	}
	public int get_Ilot_id() {
		return this.Ilot_id;
	}
	public int get_Itineraire_id() {
		return this.Itineraire_id;
	}
	public int get_ordre() {
		return this.ordre;
	}
	public List<Conteneuravider> get_conteneuravider() {
		return this.conteneuravider;
	}
	public void set_Ilot_id(int i) {
		this.Ilot_id = i;
	}
	public void set_Itineraire_id(int i) {
		this.Itineraire_id = i;
	}
	public void set_ordre(int i) {
		this.ordre = i;
	}
	public void set_conteneuravider(List<Conteneuravider> l) {
		this.conteneuravider = l;
	}
	public void ajouterconteneur(Conteneuravider c) {
		this.conteneuravider.add(c);
	}
}