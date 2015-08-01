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
		HardSoftLongScore score = HardSoftLongScore.valueOf(0, 0);
		
		for (SolveCamion solveCamion: solution.getSolveCamionList()) {
			score = score.add(solveCamion.getScore());
		}
		
		return score;
	}
}
