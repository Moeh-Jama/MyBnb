
public class Truck extends Vehicle{
	private double CargoWeight;
	
	public Truck() {
		CargoWeight = 0;
	}
	
	public void setCargoWeight(double cWeight)
	{
		CargoWeight = cWeight;
	}
	public double getCargoWeight()
	{
		return CargoWeight;
	}
	
	public String VehicleType()
	{
		return "Truck";
	}
}
