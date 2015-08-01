package data;

/** @file
 * 
 * Interface used by optaplanner solver
 */

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

@PlanningEntity
public interface Standstill {

    /**
     * @return never null
     */
    GeoCoordinate getLocation();

    /**
     * @return sometimes null
     */
    SolveCamion getSolveCamion();

    /**
     * @return sometimes null
     */
    @InverseRelationShadowVariable(sourceVariableName = "previousStandstill")
    SolveIlot getNextSolveIlot();
    void setNextSolveIlot(SolveIlot nextSolveIlot);
}
