package gps;

public class SimulatedAnnealing extends ConstraintBasedLocalSearch {
/**
 *  1 - Begin at start point P (either user-selected or randomly-generated)
 *  2 - Set the temperature, T, to it's starting value: Tmax
 *  3 - Evaluate P with an objective function, F. This yields the value F(P)
 *  4 - If F(P) >= Ftarget then return P as the solution, else continue
 *  5 - Generate n neighbors of P in the search space: (P1, P2, .. , Pn)
 *  6 - Evaluate each neighbor, yielding (F(P1), F(P2), .. , F(Pn))
 *  7 - Let Pmax be the neighbor with the highest evaluation
 *  8 - Let q = (F(Pmax)-F(P)) / F(P)
 *  9 - Let p = min [1, e^(-q / T)]
 * 10 - Generate x, a random real number in the closed range [0, 1]
 * 11 - If x > p then P <- Pmax ;; (Exploiting)
 * 12 - Else P <- a random choice among the n neighbors ;; (Exploring)
 * 13 - T <- T -dT
 * 14 - GOTO step 4
 * */
} 
