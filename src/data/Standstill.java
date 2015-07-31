package data;

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
    Truck getTruck();

    /**
     * @return sometimes null
     */
    @InverseRelationShadowVariable(sourceVariableName = "previousStandstill")
    ContainerSet getNextContainerSet();
    void setNextContainerSet(ContainerSet nextContainerSet);
}
