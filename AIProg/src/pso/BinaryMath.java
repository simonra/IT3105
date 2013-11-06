package pso;

import java.math.BigInteger;

public class BinaryMath {

	//For å finne int subtraction bruk:
	//Arrays.equals(array1, array2)
	
	//Må returne double, da double kan ha verdien double.positive/negative_infinity
	public static double findDifference(boolean[] minuend, boolean[] subtrahend){
		boolean[] difference = new boolean[Constants.KNAPSACKSIZE];
		boolean carry = false;
		//Twos complement:
		//Inverting the subtrahend:
		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			subtrahend[i] = !subtrahend[i];
		}
		//Adding the two numbers:
		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			if(!carry){
				if(minuend[i] || subtrahend[i]){
					if(minuend[i] && subtrahend[i]){
						difference[i] = false;
						carry = true;
					}
					else
						difference[i] = true;
				}else
					difference[i] = false;
			}else{
				if(minuend[i] || subtrahend[i]){
					if(minuend[i] && subtrahend[i]){
						difference[i] = true;
						carry = true;
					}
					else{
						difference[i] = false;
						carry = true;
					}
				}else{
					difference[i] = true;
					carry = false;
				}
			}
		}
		//Adding one to the result:
		addBinaryOne(difference);
		
		//Do twos complement again to get actual result (the difference is on 2's complement form atm)
		//Use the carry to store negativity
		if(difference[Constants.KNAPSACKSIZE - 1])
			carry = true;
		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			difference[i] = !difference[i];
		}
		addBinaryOne(difference);
		
		//get the resulting int:
		double result = 0;
		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
			if(difference[i])
				result += Math.pow(2, i);
		}
		//if the number is negative:
		if(carry)
			result *= -1;
		
		return result;
	}
	
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
		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
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
		for (int i = 0; i < Constants.KNAPSACKSIZE; i++) {
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
