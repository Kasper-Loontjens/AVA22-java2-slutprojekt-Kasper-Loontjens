package utilitiesPack;

public class Utilities {
	
	private static Utilities utilities= null;
	
	public static Utilities getUtilities() {
		if (utilities == null) {
			utilities = new Utilities();
		}
		return utilities;
	}
	
	public int getRandumInt(int min, int max) {
	      // Print the min and max  
	      // System.out.println("Random value in int from "+ min + " to " + max + ":");
	      // Generate random int value from min to max
	      int randomInt = (int)Math.floor(Math.random() * (max - min + 1) + min);
	      // Printing the generated random numbers
	      // System.out.println(randomInt);
	      return randomInt;
	}

}
