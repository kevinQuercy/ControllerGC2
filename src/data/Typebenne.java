package data;

public class Typebenne {
	private int id;
	private int volume;
	private int typedechets_id;

	public Typebenne(int i,int v,int t){
		this.id=i;
		this.volume=v;
		this.typedechets_id=t;
	}
	public int get_id(){
		return this.id;
	}
	public int get_volume(){
		return this.volume;
	}
	public int get_typedechets_id(){
		return this.typedechets_id;
	}
}
