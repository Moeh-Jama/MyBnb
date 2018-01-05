
public class Apartment extends Property{
	private int storeyNumber;
	private int numberOfBeds;
	
	
	public Apartment() {
		// Defaults of the Apartment class are set.
		storeyNumber = 0;
		numberOfBeds = 0;
	}
	
	public void setStoreyNumber(int totalStoreys) {
		storeyNumber = totalStoreys;
	}
	public int getStoreyNumber() {
		return storeyNumber;
	}
	
	
	public void setNumberOfBeds(int totalBeds) {
		numberOfBeds = totalBeds;
	}
	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public String PropertyType()
	{ //Polymorphism
		return "Apartment Property";
	}
}
