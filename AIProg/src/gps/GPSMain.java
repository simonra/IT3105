package gps;

public class GPSMain {
	public static void main(String[] args) {
		SAStateManager manager = new SAKQStateManager(8);
		SimulatedAnnealing sa = new SimulatedAnnealing();

		manager = sa.saSearch(manager, 100, 1);
		System.out.println(manager.toString());
	}
}
