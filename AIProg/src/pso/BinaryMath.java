package pso;

public class BinaryMath {

	//For å finne int subtraction bruk:
	//Arrays.equals(array1, array2)
	
	public static void addBinary(boolean[] binaryBoolean, int numberToAdd){
		//Add 1 binary numberToAdd times
		for (int i = 0; i < numberToAdd; i++) {
			addBinaryOne(binaryBoolean);
		}
	}
	
	public static void subtractBinary(boolean[] binaryBoolean, int numberToSubtract){
		//Subtract 1 binary numberToSubtract times
		for (int i = 0; i < numberToSubtract; i++) {
			subtractBinaryOne(binaryBoolean);
		}
	}
	
	static void addBinaryOne(boolean[] binaryBoolean){
		for (int i = Constants.KNAPSACKSIZE; i >= 0; i++) {
			if(!binaryBoolean[i]){
				binaryBoolean[i] = true;
				return;
			}
			if(binaryBoolean[i]){
				binaryBoolean[i] = false;
			}
		}
	}
	
	static void subtractBinaryOne(boolean[] binaryBoolean){
		for (int i = Constants.KNAPSACKSIZE; i >= 0; i++) {
			if(!binaryBoolean[i]){
				binaryBoolean[i] = true;
			}
			if(binaryBoolean[i]){
				binaryBoolean[i] = false;
				return;
			}
		}
	}

}
