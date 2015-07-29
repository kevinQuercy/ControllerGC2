package data;

import java.util.LinkedList;
import java.util.List;

public class Ilotsavider {
	private List<Ilot> ilots;
	private List<Camion> camions;
	private int typedechets;

	
	public Ilotsavider(int typedechets) {
		this.ilots = new LinkedList<Ilot>();
		this.camions = new LinkedList<Camion>();
		this.typedechets = typedechets;
	}
	public void ajoutercamion(Camion c) {
		this.camions.add(c);
	}
	public void ajouterilot(Ilot i) {
		this.ilots.add(i);
	}
	public List<Ilot> get_ilots() {
		return this.ilots;
	}
	public int get_typedechets() {
		return this.typedechets;
	}
	public List<Camion> get_camions() {
		return this.camions;
	}
}
