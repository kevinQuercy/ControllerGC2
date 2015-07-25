package data;

/** @file
 * 
 * Single container object
 */

public class Container {
	private int containerId;
	private int weight; // kg
	private int volume; // L
	private int volumemax; // L
	private int fillRatio; // %

	public Container(int containerId) {
		super();
		this.containerId = containerId;
		weight = 0;
		volume = 0;
		volumemax = 0;
		fillRatio = 0;
	}
	
	public int getContainerId() {
		return containerId;
	}

	public int getWeight() {
		return weight;
	}

	public int getVolume() {
		return volume;
	}

	public int getVolumeMax() {
		return volumemax;
	}

	public int getFillRatio() {
		return fillRatio;
	}

	public void setState(int weight, int volume, int volumemax) {
		this.weight = weight;
		this.volume = volume;
		this.volumemax = volumemax;
		this.fillRatio = 100*volume/volumemax;
	}
	
	// whether container should be collected
	public boolean isReadyForCollect() {
		return weight >= 150 || fillRatio >= 75; 
	}
}
