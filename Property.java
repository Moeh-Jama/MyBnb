
public class Property implements RentalItem{
	private int uniqueRegNumber;
	private String ownerName;
	private String postalAddress;
	private double rentalCostPerDay;
	private int totalRentDays;
	public Property()
	{
		//Default values set.
		String msg = "Data not Assigned";
		ownerName = msg;
		postalAddress = msg;
		rentalCostPerDay = 0;
		totalRentDays = 0;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String newName) {
		ownerName = newName;
	}
	
	public void setUniqueRegNumber(int newRegNumber) {
		uniqueRegNumber = newRegNumber;
	}
	public int getUniqueRegNumber() {
		return uniqueRegNumber;
	}
	
	
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String newAddress) {
		postalAddress = newAddress;
	}
	public double getRentalCostPerDay() {
		return rentalCostPerDay;
	}
	public void setRentalCostPerDay(double newCostPerDay) {
		rentalCostPerDay = newCostPerDay;
	}
	public int getTotalRentalDays() {
		return totalRentDays;
	}	
	public void RentProperty(int RentDays)
	{
		totalRentDays += RentDays;
	}

	public String PropertyType()
	{
		return "Property";
	}

	@Override
	public void RentalItem(int RentalDays) {
		// TODO Auto-generated method stub
		totalRentDays += RentalDays;
	}
	
}
