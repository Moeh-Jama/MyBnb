
public class Car extends Vehicle{
	private int PassengersNumber;
	
	public Car() {
		PassengersNumber = 0;
	}
	
	public void setPassengersNumber(int pNum)
	{
		PassengersNumber = pNum;
	}
	public int getPassengersNumber()
	{
		return PassengersNumber;
	}
	
	public String VehicleType()
	{
		return "Car";
	}
}
