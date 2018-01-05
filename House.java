
public class House extends Property{
	private int totalNumberOfStoreys;
	private double clearingFees;
	
	public House() {
		//Attributes of the House class are assigned default values.
		totalNumberOfStoreys = 0;
		clearingFees = 0;
	}
	
	public void setTotalNumberOfStoreys(int totalStoreys) {
		totalNumberOfStoreys = totalStoreys;
	}
	public int getTotalNumberOfStoreys() {
		return totalNumberOfStoreys;
	}
	
	public void setClearingFees(double clear) {
		clearingFees = clear;
	}
	public double getClearingFees() {
		return clearingFees;
	}

	public String PropertyType()
	{ //Polymorphism
		return "House Property";
	}
	
}
