package data;

import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;

/** @file
 * 
 * Encapsulation de Camion pour le calcul d'itineraire
 *
 */

public class SolveCamion implements Standstill {
	static public class ItineraireValues {
		public int longueur; // metres
		public int poidsTotal; // kg
		public int volumeTotal; // L
		public int nbIlots;
		
		public ItineraireValues() {
			longueur = 0;
			poidsTotal = 0;
			volumeTotal = 0;
			nbIlots = 0;
		}
	}
	
	protected Camion camion; // camion encapsule
	
	private GeoCoordinate depot;

    // Shadow variables
    protected SolveIlot nextSolveIlot;
    
    public SolveCamion() {
    }

    public SolveCamion(Camion camion, GeoCoordinate depot) {
    	this.camion = camion;
    	this.depot = depot;
    }

    public GeoCoordinate getDepot() {
		return depot;
	}

	public void setDepot(GeoCoordinate depot) {
		this.depot = depot;
	}

	public Camion getCamion() {
		return camion;
	}
	
	@Override
	public GeoCoordinate getLocation() {
		return depot;
	}

	@Override
	public SolveCamion getSolveCamion() {
		return this;
	}

	@Override
	public SolveIlot getNextSolveIlot() {
		return nextSolveIlot;
	}

	@Override
	public void setNextSolveIlot(SolveIlot nextSolveIlot) {
		this.nextSolveIlot = nextSolveIlot;
	}

	public ItineraireValues getItineraireValues() {
		double longueur = 0;
		int poidsTotal = 0;
		int volumeTotal = 0;
		int nbIlots = 0;
		SolveIlot solveIlot = nextSolveIlot;
		GeoCoordinate prev_loc = depot; // on demarre du depot
		while (solveIlot != null) {
			for (Conteneur conteneur: solveIlot.getIlot().get_conteneurs()) {
				if (conteneur.get_TypeDechets_id() == camion.get_Typedechets_id()) {
					poidsTotal += conteneur.get_lastpoids();
					volumeTotal += conteneur.get_lastvolume();
				}
			}
			
			longueur += prev_loc.distanceTo(solveIlot.getLocation());
			prev_loc = solveIlot.getLocation();
			
			solveIlot = solveIlot.getNextSolveIlot();
			nbIlots++;
		}
		longueur += prev_loc.distanceTo(depot); // retour au depot

		ItineraireValues result = new ItineraireValues();
		result.longueur = (int)longueur;
		result.poidsTotal = poidsTotal;
		result.volumeTotal = volumeTotal;
		result.nbIlots = nbIlots;
		return result;
	}

	/* get score
	 * 
	 * Strategy:
	 * - evaluate *time* of the circuit by using:
	 *   - length of the circuit (in metres)
	 *   - penalty time for each ContainerSet to visit
	 * - unit of the evaluation is metres, and so time to visit a ContainerSet should be expressed in
	 *   'equivalent distance that truck can move during the visit of a ContainerSet'
	 *   (example: 3000 => process a ContainerSet is equivalent to drive 3km)
	 * - then score of the truck is squared to search solution that is balancing circuit time between all trucks.
	 *   (see http://docs.jboss.org/optaplanner/release/6.2.0.Final/optaplanner-docs/html_single/index.html#fairnessScoreConstraints)
	 */
	public HardSoftLongScore getScore() {
		ItineraireValues values = getItineraireValues();

		// determine overload, as hard score
		long overload = 0;
		if (values.poidsTotal > camion.get_poidsmax()*1000)
			overload += values.poidsTotal - camion.get_poidsmax()*1000;
		if (values.volumeTotal > camion.get_volumemax())
			overload += values.volumeTotal - camion.get_volumemax();
		
		// determine ~time, as soft score 
		long scoreTime = ((long)values.longueur + values.nbIlots*3000) / 10; // reduce scale to avoid overflow
		
		return HardSoftLongScore.valueOf(-overload, -scoreTime*scoreTime);
	}
}
