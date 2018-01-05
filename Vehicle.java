
public class Vehicle implements RentalItem{
	//private int RegistrationNumber;
	private String Owner;
	private double RentalCostPerDay;
	private int TotalRentalDays;
	
	
	public Vehicle()
	{
		//Default values set.
		String msg = "Data not Assigned";
		Owner = msg;
		RentalCostPerDay = 0;
		TotalRentalDays = -1;
	}
	
	public void setOwnerName(String name)
	{
		Owner = name;
	}
	public String getOwnerName() {
		return Owner;
	}
	
	public void setRentalCostPerDay(double costPerDay)
	{
		RentalCostPerDay+=costPerDay;
	}
	public double getRentalCostPerDay()
	{
		return RentalCostPerDay;
	}
	
	public int getTotalRentalDays() {
		return TotalRentalDays;
	}
	
	public String VehicleType()
	{
		return "Vehicle";
	}
	
	@Override
	public void RentalItem(int RentalDays) {
		// TODO Auto-generated method stub
		TotalRentalDays += RentalDays;
	}
	
}
