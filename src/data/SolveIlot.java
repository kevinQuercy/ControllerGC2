package data;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

/** @file
 * 
 * Encapsulation de Ilot pour le calcul d'itineraire
 *
 */

@PlanningEntity
public class SolveIlot implements Standstill {
	protected Ilot ilot; // ilot encapsule
	
    // Planning variables: changes during planning, between score calculations.
    protected Standstill previousStandstill;

    // Shadow variables
    protected SolveIlot nextSolveIlot;
    protected SolveCamion solveCamion;
    
    public SolveIlot() {
    }
    
    public SolveIlot(Ilot ilot) {
    	this.ilot = ilot;
    }
    
    public Ilot getIlot() {
    	return ilot;
    }

    @PlanningVariable(valueRangeProviderRefs = {"vehicleRange", "customerRange"},
            graphType = PlanningVariableGraphType.CHAINED)
	public Standstill getPreviousStandstill() {
		return previousStandstill;
	}

	public void setPreviousStandstill(Standstill previousStandstill) {
		this.previousStandstill = previousStandstill;
	}

    @AnchorShadowVariable(sourceVariableName = "previousStandstill")
	public SolveCamion getSolveCamion() {
		return solveCamion;
	}

	public void setSolveCamion(SolveCamion solveCamion) {
		this.solveCamion = solveCamion;
	}

	@Override
	public SolveIlot getNextSolveIlot() {
		return nextSolveIlot;
	}

	@Override
	public void setNextSolveIlot(SolveIlot nextSolveIlot) {
		this.nextSolveIlot = nextSolveIlot;
	}
	
    /**
     * @return a positive number, the distance in metres
     */
    public int getDistanceFromPreviousStandstill() {
        if (previousStandstill == null) {
            return 0;
        }
        return getDistanceFrom(previousStandstill);
    }

    /**
     * @param standstill never null
     * @return a positive number, the distance in metres
     */
    public int getDistanceFrom(Standstill standstill) {
        return (int)standstill.getLocation().distanceTo(getLocation());
    }

    /**
     * @param standstill never null
     * @return a positive number, the distance in metres
     */
    public int getDistanceTo(Standstill standstill) {
        return (int)getLocation().distanceTo(standstill.getLocation());
    }

    public GeoCoordinate getLocation() {
    	return ilot.getLocation();
    }

}
