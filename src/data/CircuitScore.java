package data;

/** @file
 *
 * Evaluate score of a solution
 */

import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

public class CircuitScore implements EasyScoreCalculator<CircuitSolution>{

	@Override
	public HardSoftLongScore calculateScore(CircuitSolution solution) {
		long softScore = 0;
		
		for (Truck truck: solution.getTruckList()) {
			softScore += truck.getScore();
		}
		
		return HardSoftLongScore.valueOf(0, softScore);
	}

}
