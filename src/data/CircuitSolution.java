package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;

@PlanningSolution
public class CircuitSolution implements Solution<HardSoftLongScore> {
    protected List<SolveCamion> camionList;
	protected List<SolveIlot> ilotList;
	protected HardSoftLongScore score;

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "vehicleRange")
    public List<SolveCamion> getSolveCamionList() {
        return camionList;
    }

    public void setSolveCamionList(List<SolveCamion> camionList) {
        this.camionList = camionList;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "customerRange")
    public List<SolveIlot> getSolveIlotList() {
        return ilotList;
    }

    public void setSolveIlotList(List<SolveIlot> ilotList) {
        this.ilotList = ilotList;
    }

    public HardSoftLongScore getScore() {
        return score;
    }

    public void setScore(HardSoftLongScore score) {
        this.score = score;
    }

	@Override
	public Collection<? extends Object> getProblemFacts() {
        List<Object> facts = new ArrayList<Object>();
        facts.addAll(ilotList);
        // Do not add the planning entities (vehicleList, customerList) because that will be done automatically
        return facts;
	}
}
