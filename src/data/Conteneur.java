package data;

import java.util.Date;

public class Conteneur {
	private int id;
	private int volumemax;
	private int lastvolume;
	private int lastpoids;
	private Date lastupdate;
	private int Ilot_id;
	private int TypeDechets_id;

	public Conteneur() {}
	public Conteneur(int a) {
		this.id=a;
	}
	public int get_id() {
		return this.id;
	}
	public int get_volumemax() {
		return this.volumemax;
	}
	public int get_lastvolume() {
		return this.lastvolume;
	}
	public int get_lastpoids() {
		return this.lastpoids;
	}
	public Date get_lastupdate() {
		return this.lastupdate;
	}
	public int get_Ilot_id() {
		return this.Ilot_id;
	}
	public int get_TypeDechets_id() {
		return this.TypeDechets_id;
	}
	public void set_id(int a) {
		this.id=a;
	}
	public void set_volumemax(int a) {
		this.volumemax=a;
	}
	public void set_lastvolume(int a) {
		this.lastvolume=a;
	}
	public void set_lastpoids(int a) {
		this.lastpoids=a;
	}
	public void set_lastupdate(Date a) {
		this.lastupdate=a;
	}
	public void set_Ilot_id(int a) {
		this.Ilot_id=a;
	}
	public void set_TypeDechets_id(int a) {
		this.TypeDechets_id=a;
	}
	public void majetat(int volume,int poids) {
		this.lastvolume = volume;
		this.lastpoids = poids;
		this.lastupdate = new Date();
	}
}
