package data;

import java.util.LinkedList;
import java.util.List;
import data.Ilotdepassage;

public class Itineraire {
	private int id;
	private int Camion_id;
	private int Planification_id;
	private List<Ilotdepassage> ilotsdepassage;

	public Itineraire(){
		ilotsdepassage = new LinkedList<Ilotdepassage>();
	}
	public int get_id() {
		return this.id;
	}
	public int get_Camion_id() {
		return this.Camion_id;
	}
	public int get_Planification_id() {
		return this.Planification_id;
	}
	public List<Ilotdepassage> get_ilotsdepassage() {
		return this.ilotsdepassage;
	}
	public void set_id(int a) {
		this.id=a;
	}
	public void set_Camion_id(int a) {
		this.Camion_id=a;
	}
	public void set_Planification_id(int a) {
		this.Planification_id=a;
	}
	public void set_ilotsdepassage(List<Ilotdepassage> li) {
		this.ilotsdepassage = li;
	}
	public void ajouterilot(Ilotdepassage i) {
		this.ilotsdepassage.add(i);
	}
}
