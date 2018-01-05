
public class LuxuryVilla extends Property{
	private double roomServiceCostPerDay;
	private double luxuryTaxPerDay;
	
	public LuxuryVilla()
	{
		// Default values of the LuxuryVilla attributes are set.
		roomServiceCostPerDay = 0;
		luxuryTaxPerDay = 0;
	}
	
	public void setRoomServiceCostPerDay(double RoomServiceCost) {
		roomServiceCostPerDay = RoomServiceCost;
	}
	public double getRoomServiceCostPerDay() {
		return roomServiceCostPerDay;
	}
	
	public void setLuxuryTaxPerDay(double taxServices) {
		luxuryTaxPerDay = taxServices;
	}
	public double getLuxuryTaxPerDay() {
		return luxuryTaxPerDay;
	}

	public String PropertyType()
	{ //Polymorphism
		return "Luxury Villa Property";
	}
	
}
