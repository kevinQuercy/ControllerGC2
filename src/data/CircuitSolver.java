package data;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

public class CircuitSolver {
    public static final String SOLVER_CONFIG = "circuitSolverConfig.xml";

	protected Solver solver;
	
	public CircuitSolver() {
        solver = SolverFactory.createFromXmlResource(SOLVER_CONFIG).buildSolver();
	}
	
	public CircuitSolution solve(CircuitSolution problem) {
		solver.solve(problem);
		return (CircuitSolution)solver.getBestSolution();
	}
}
