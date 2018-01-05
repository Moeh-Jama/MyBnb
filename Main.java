/*
 * @Author: Mohamed Jama
 * Student number: 16354491
 * Date: 15/10/2017
 * Assignment 2. MyBnb program.
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Main {
	//All Five Objects lists are declared. Each ArrayList holds one of the 
	//Specialised property and Vehicle sub-classes.
	//Property ArrayLists
	static ArrayList<Apartment> ApartmentArrayList = new ArrayList<Apartment>();
	static ArrayList<House> HouseArrayList = new ArrayList<House>();
	static ArrayList<LuxuryVilla> VillaArrayList = new ArrayList<LuxuryVilla>();
	//Vehicle ArrayLists
	static ArrayList<Car> CarArrayList = new ArrayList<Car>();
	static ArrayList<Truck> TruckArrayList = new ArrayList<Truck>();

	static 	Scanner input = new Scanner(System.in);
	static int UniqueRegNumber = 1;
	static int propertyInt=0;
	static int[] totalReg = new int[3*3];
	static int totalRegIndex =0;
	
	static JButton btn1, btn2;
	static JTextField PropertyIDJFrame, RentDaysJFrame;
	static JLabel lb1, lb2;
	
	public static void main(String[] args) throws IOException
	{
		/*
		 * In the main function the properties are created and inserted within the ArrayLists,
		 * then print all the properties details and finally calculated the total income from
		 * all the properties together.
		 */
		//MainMenu is the main gui interface of the program.
		ReadInputPropertyFile();
		ReadInputVehicleFile();
		MainMenu();
	}
	public static boolean UniqueRegNumberAuthentication(int regNumber, int type)
	{
		/*
		 * Function tests whether a given Registration number is really Unique within the Properties or not
		 */
		for(Apartment i: ApartmentArrayList)
		{	
			if(i.getUniqueRegNumber() == regNumber)
			{	
				if(type == 0)
				{
					return false;
				}
				else {
					JOptionPane.showMessageDialog(null, "You did not enter a Unique Reg Number.", null, JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}
		}
		for(House i: HouseArrayList)
		{
			if(i.getUniqueRegNumber() == regNumber)
			{
				if(type == 0)
				{
					return false;
				}
				else {
					JOptionPane.showMessageDialog(null, "You did not enter a Unique Reg Number.", null, JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}
		}
		for(LuxuryVilla i: VillaArrayList)
		{
			if(i.getUniqueRegNumber() == regNumber)
			{
				if(type == 0)
				{
					return false;
				}
				else {
					JOptionPane.showMessageDialog(null, "You did not enter a Unique Reg Number.", null, JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}
		}
		
		return true;
	}
	public static void FillInProperties()
	{
		/*
		*	Data is inserted into the ArrayLists via this function.
		*/
		String propertyChoice;
		int pChoice;
		do {
			try {
				propertyChoice = JOptionPane.showInputDialog("Choose a Property Type to Enter:\n1.Apartment\n2.House\n3.LuxuryVilla");
				pChoice = Integer.parseInt(propertyChoice);
			}catch(Exception e) {
				System.out.println("User did not enter appropriate data.");
				pChoice = 5;	//so as the user must re-do it.
			}
		}while(pChoice!=1 && pChoice!=2 && pChoice!=3);
		
		
		switch(pChoice)
		{
		case 1:{
			Apartment n = new Apartment();
			/*FillBasicProperty function directs user enter data that belong
			*to the Property Super-class, 
			*where the user will enter data inherited from the super-class..
			*/
			FillBasicProperty(n);
			String numberOfBeds  = userInput("\nEnter Number of Beds: ", "Integer", n.PropertyType());
			n.setNumberOfBeds(Integer.parseInt(numberOfBeds));
			String storeyNumber  = userInput("\nEnter the number of storeys: ", "Integer", n.PropertyType());
			n.setStoreyNumber(Integer.parseInt(storeyNumber));
			ApartmentArrayList.add(n);
			break;
		}
		case 2:{
			House n = new House();
			FillBasicProperty(n);
			String storeyNumber = userInput("\nEnter the total number of storeys: ", "Integer", n.PropertyType());
			n.setTotalNumberOfStoreys(Integer.parseInt(storeyNumber));
			String clearingFees = userInput("\nEnter clearing fee amount: ", "number", n.PropertyType());
			n.setClearingFees(Double.parseDouble(clearingFees));
			HouseArrayList.add(n);
			break;
		}
		case 3:{
			LuxuryVilla n = new LuxuryVilla();
			FillBasicProperty(n);
			
			String roomServiceCost = userInput("\nEnter Room Service Cost Per day amount: ", "number", n.PropertyType());
			n.setRoomServiceCostPerDay(Double.parseDouble(roomServiceCost));
			String luxuryTax = userInput("\nEnter the Luxury Tax Per Day: ", "number", n.PropertyType());
			n.setLuxuryTaxPerDay(Double.parseDouble(luxuryTax));
			VillaArrayList.add(n);
			break;
		}
		default: System.out.println("Something went wrong.");
		}
	}
	
	public static void FillBasicProperty(Property n)
	{
		/*
		 * Instead of creating the following statements are written 3 times they
		 * are now only called 3 times, making the code readable, and flexible for later changes.
		 */

		/*
		* All variable attributes are of the objects are obtained from the userInput function
		* which will sanitise the result of unauthorised data types typed by the user.
		*/
		String uniqueReg;
		//This do while loop checks whether a users entered Registration number is truly unique.
		do {
			uniqueReg = userInput("Enter the property Registration Number: ", "Integer", n.PropertyType());
			
		}while(UniqueRegNumberAuthentication(Integer.parseInt(uniqueReg), 1) == false);
		
		n.setUniqueRegNumber(Integer.parseInt(uniqueReg));
		
		String name = userInput("Enter owner Name: ", "text", n.PropertyType());
		n.setOwnerName(name);
		
		String postalAddress = userInput("\nEnter postal Address: ", "text", n.PropertyType());
		n.setPostalAddress(postalAddress);
		
		String rentalCostPerDay = userInput("\nEnter Rental Cost per Day: ", "number", n.PropertyType());
		n.setRentalCostPerDay(Double.parseDouble(rentalCostPerDay));
		
		for(int i =0; i<3; i++)
		{
			//As described in the assignment 1 brief the RentProperty will be called 3 times and the RentDays variable 
			//added to the TotalRentDays at each iteration(3).
			String RentDays = userInput("\n(" +(i+1)+ ")Enter the total days the property is being rented: ", "Integer", n.PropertyType());
			n.RentProperty(Integer.parseInt(RentDays));
		}
	}
	
	public static String userInput(String message, String DataType, String Propertytype)
	{
		/*
		 * This function tests the users entered variable and checks whether it is
		 * The acceptable input data type for this program.
		 */
		//The three variables needed for the CheckDataEntry function, and two of them for the CheckInteger function
		String variable;
		boolean Type = false;
		String msg= "";
		//The input variables for the two Error Handling functions are set.
		if(DataType == "text")
		{
			//Type is used as the testing output of the CheckDataEntry, 
			// where if the result is false than user entered a non-string data type.
			Type = false;
			//Message output for failed variable testing is assigned.
			msg = "You must enter letters, no numbers!";
		}
		else if(DataType == "number" || DataType=="Integer")
		{
			Type = true;
			msg = "You must enter numbers, no letters and words.";
		}
		
		if(DataType == "number" || DataType == "text") {
			//This statement is used for testing String and Double data types.
			do {
				//System.out.print(message);
				variable = JOptionPane.showInputDialog(null, message,Propertytype+" "+((propertyInt%3)+1),JOptionPane.PLAIN_MESSAGE);
				//testing begins in the while statement and if false we repeat.
			}while(CheckDataEntry(variable, msg, DataType) == Type);
		}
		else {
			//Here the program tests for Integer data type inputs only!
			do {
				//System.out.print(message);
				variable = JOptionPane.showInputDialog(null, message,Propertytype+" "+((propertyInt%3)+1),JOptionPane.PLAIN_MESSAGE);
			}while(checkIfInteger(variable, msg) == Type);
		}
		return variable;
	}
	
	
 	public static void PrintAllProperties() throws IOException
	{   
 		/*
 		 * Function Stores all data of the properties into
 		 * OutputProperty.txt file.
 		 */
 		//Printing Details onto console.
 		String formatedOutPut = "";
 		formatedOutPut += "-Apartment-\n";
 		for(Apartment i: ApartmentArrayList)
 		{
 			//String text is created and is assigned all the info of each 
 			// Apartment object then formatedOutPut is assigned text.
 			String text = "";
 			text +="Registration Number: "+i.getUniqueRegNumber()+"\n";
 			text += "Owner Name: "+i.getOwnerName()+"\n";
 			text += "Postal Address: "+i.getPostalAddress()+"\n";
 			text += "Total Rental Days: "+i.getTotalRentalDays()+"\n";
 			text += "Rental Cost Per Day: € "+i.getRentalCostPerDay()+"\n";
 			text +="*******************************************************\n";
 			formatedOutPut +=text;
 		}
 		formatedOutPut += "-House-\n";
 		for(House i: HouseArrayList)
 		{
 			String text = "";
 			text +="Registration Number: "+i.getUniqueRegNumber()+"\n";
 			text += "Owner Name: "+i.getOwnerName()+"\n";
 			text += "Postal Address: "+i.getPostalAddress()+"\n";
 			text += "Total Rental Days: "+i.getTotalRentalDays()+"\n";
 			text += "Rental Cost Per Day: € "+i.getRentalCostPerDay()+"\n";
 			text += "Clearing Fee: € "+i.getClearingFees()+"\n";
 			text +="*******************************************************\n";
 			formatedOutPut +=text;
 		}
 		formatedOutPut += "-Luxury Villa-\n";
 		for(LuxuryVilla i: VillaArrayList)
 		{
 			String text = "";
 			text +="Registration Number: "+i.getUniqueRegNumber()+"\n";
 			text += "Owner Name: "+i.getOwnerName()+"\n";
 			text += "Postal Address: "+i.getPostalAddress()+"\n";
 			text += "Total Rental Days: "+i.getTotalRentalDays()+"\n";
 			text += "Rental Cost Per Day: € "+i.getRentalCostPerDay()+"\n";
 			text += "Room Service Cost Per Day: € "+i.getRoomServiceCostPerDay()+"\n";
 			text += "Luxury Tax Per Day: € "+i.getLuxuryTaxPerDay()+"\n";
 			text +="*******************************************************\n";
 			formatedOutPut +=text;
 		}
 		//Print All Property details onto console.
 		System.out.println(formatedOutPut);
 		BufferedWriter writer = new BufferedWriter(new FileWriter("OutputProperty.txt"));
 		writer.write(formatedOutPut);
 		writer.close();
	}
	public static void CalculatePropertyTotalIncome()
	{
		/*
		 * CalculateTotalIncome function calculates all income
		 * from all the properties, including the additional costs and taxes.
		 */
		double totalIncome = 0;
		
		for(Apartment i: ApartmentArrayList)
		{
			// the rental cost is multiplied by the number of rooms and added
			totalIncome += (i.getRentalCostPerDay()* i.getTotalRentalDays());
		}
		for(House i: HouseArrayList)
		{
			// Figure out how to calculate House Profits.
			totalIncome += (i.getClearingFees() +i.getRentalCostPerDay() )* i.getTotalRentalDays();
		}
		for(LuxuryVilla i: VillaArrayList)
		{
			/*
			 * Luxury Villa property cost is calculated and added to the totalIncome variable.
			 */
			totalIncome += (i.getLuxuryTaxPerDay()+i.getRoomServiceCostPerDay() +i.getRentalCostPerDay()) * i.getTotalRentalDays();
		}
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("Total Income: €"+df.format(totalIncome));
		JOptionPane.showMessageDialog(null, "Total Income: €"+df.format(totalIncome));
	}
	
	
	/*
	 * 				Error Handling Methods are implemented below! 
	 */
	
	public static boolean CheckDataEntry(String stringHandling, String msg, String dataType)
	{
		/*
		 * This function tests both String and Double data types
		 * if a data type is a String, the function returns False.
		 * if a data type is a Double, the function returns True.
		 */
		boolean isString = false;
		try {
			@SuppressWarnings("unused")
			double checkDouble = Double.parseDouble(stringHandling);
		}
		catch(NumberFormatException nfe)
		{
			isString = true;
		}
		if(isString == true)
		{
			if(dataType == "number") {
				JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			return true;
		}
		else {
			if(dataType == "text") {
				JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			return false;
		}
	}
	
	public static boolean checkIfInteger(String integerHandling, String msg)
	{
		/*
		 * This functions checks whether a user input is an Integer or not.
		 * for example both 12.56 and 'hello' are both not considered as integers.
		 * but 12 is. Decimals are not permitted.
		 */
		
		boolean isNotInteger= false;
		try {
			@SuppressWarnings("unused")
			int checkDouble = Integer.parseInt(integerHandling);
		}
		catch(NumberFormatException nfe)
		{
			//If an error occurs then we set the boolean variable to false, I.e this tells the program that
			//This String is not an Integer.
			isNotInteger = true;
		}
		if(isNotInteger == true)
		{
			//return true which indicates that the variable type is a non integer type
			JOptionPane.showMessageDialog(null, msg+"(Non-decimal)", "ERROR", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		else {
			//false in this context means that the number is NOT any other data type other than Integer
			return false;
		}
	}
	
	/*
	 * Assignment Two Methods
	 */
	
	
	
	public static void ReadInputPropertyFile() throws FileNotFoundException {
		/*
		 *	This method reads the contents in the text file 'InpuProperty'
		 * and translates it into usable data for MyBnb application.
		 */
		FileReader reader = new FileReader(".//InputProperty.txt");
		Scanner in = new Scanner(reader);
		while(in.hasNextLine()) {
			String line = in.nextLine();
			//System.out.println(line);
			//strArray is an array of string that were split by whitespace,
			//each index is assigned a property attribute.
			String[] strArray =line.split(" ");
			int propertyType = Integer.parseInt(strArray[0]);
			//The property's are distinguished by either 1,2,3 entries of the first 
			// index of strArray.
			switch(propertyType){
				case 1:{
					//An instance of Apartment is created since propertyType is equal 0
					//which is a Apartment property type index.
					Apartment n = new Apartment();
					//The BasicReadProperty function is called and all 
					//attributes of the super-class Property is assigned a value from the passed 
					//String array strArray.
					// 0 is added because Villa is 1 index ahead of the rest of the property's.
					BasicReadProperty(n, 0, strArray);
					ApartmentArrayList.add(n);
					break;
				}
				case 2:{
					House n = new House();
					BasicReadProperty(n, 0, strArray);
					n.setClearingFees(Double.parseDouble(strArray[2]));
					HouseArrayList.add(n);
					break;
				}
				case 3:{
					LuxuryVilla n = new LuxuryVilla();
					BasicReadProperty(n, 1, strArray);
					n.setRoomServiceCostPerDay(Double.parseDouble(strArray[3]));
					n.setLuxuryTaxPerDay(Double.parseDouble(strArray[2]));
					VillaArrayList.add(n);
					break;
				}
				default:{
					System.out.println("Unknown Property Type");
				}
			}
		}
	}
	
	public static void BasicReadProperty(Property n, int type, String[] strArray){
		/*
		 * Function stores data from StrArray into the attributes of the 
		 * Property object.
		 */
		//Checks whether a Unique Reg number is usuable.
		while(UniqueRegNumberAuthentication(UniqueRegNumber, 0) == false) {
			UniqueRegNumber++;
		}
		n.setUniqueRegNumber(UniqueRegNumber);
		n.RentProperty(Integer.parseInt(strArray[1]));
		n.setOwnerName(strArray[5+type]);
		n.RentProperty(Integer.parseInt(strArray[4+type]));
		n.setPostalAddress(strArray[6+type]);
		n.setRentalCostPerDay(Double.parseDouble(strArray[3+type]));
	}
	
	
	public static void findUser(int PropertyID, int RentDays)
	{
		/*Function looks for PropertyID between 1-3 then sends
		 *Appropriate ArrayList to the iterateThroughProeprtyType function.
		 */
		
		for(Apartment i: ApartmentArrayList)
		{
			if(PropertyID == i.getUniqueRegNumber())
			{
				//ADD RENTDAYS.
				i.RentalItem(RentDays);
			}
		}
		for(House i: HouseArrayList)
		{
			if(PropertyID == i.getUniqueRegNumber())
			{
				//ADD RENTDAYS.
				i.RentalItem(RentDays);
			}
		}
		for(LuxuryVilla i: VillaArrayList)
		{
			if(PropertyID == i.getUniqueRegNumber())
			{
				//ADD RENTDAYS.
				i.RentalItem(RentDays);
			}
		}
		
		
		
	}
	
	
	
	public static void GivenRentalProperty(){
		/*
		 * Function creates a JFrame so as the PropertyID and
		 * RentDays data is collected from the user.
		 */
		JFrame frame = new JFrame();
		frame.setSize(400, 500);
		//Sets the Frame at the middle of the screen
		frame.setLocationRelativeTo(null);
		JPanel thePanel = new JPanel();
		
		//Buttons to call the calculation functions and to close the gui
		lb1 = new JLabel("PropertyID");
		PropertyIDJFrame = new JTextField("", 5);
		thePanel.add(lb1);
		thePanel.add(PropertyIDJFrame);
		lb2 = new JLabel("RentDays");
		RentDaysJFrame = new JTextField("", 5);
		thePanel.add(RentDaysJFrame);
		thePanel.add(lb2);
		
		
		btn1 = new JButton("OK");
		ListenForButton lButton = new ListenForButton();
		btn1.addActionListener(lButton);
		thePanel.add(btn1);
		btn2 = new JButton("Close");
		//ListenForButtonTwo lButtonTwo = new ListenForButtonTwo();
		btn2.addActionListener(e -> frame.dispose());
		thePanel.add(btn2);
		
		
		frame.add(thePanel);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	


	/*
	 * Vehicle, Car and Truck Section below!
	 */
	public static void ReadInputVehicleFile() throws FileNotFoundException{
		/*
		 *	This method reads the contents in the text file 'InputVehicle'
		 * and translates it into usable data for MyBnb application.
		 */
		FileReader reader = new FileReader("./InputVehicle.txt");
		Scanner in = new Scanner(reader);
		while(in.hasNextLine()) {
			String line = in.nextLine();
			//strArray is an array of string that were split by whitespace,
			//each index is assigned a property attribute.
			String[] strArray =line.split(" ");
			int propertyType = Integer.parseInt(strArray[0]);
			switch(propertyType){
				case 1:{
					Car n = new Car();
					//TODO Set to correct strArray index
					n.RentalItem(Integer.parseInt(strArray[1]));
					n.setPassengersNumber(Integer.parseInt(strArray[2]));
					n.setRentalCostPerDay(Double.parseDouble(strArray[3]));
					n.setOwnerName(strArray[5]);
					CarArrayList.add(n);
					break;
				}
				case 2:{
					Truck n = new Truck();
					n.RentalItem(Integer.parseInt(strArray[1]));
					n.setCargoWeight(Double.parseDouble(strArray[2]));
					n.setRentalCostPerDay(Double.parseDouble(strArray[3]));
					n.setOwnerName(strArray[5]);
					TruckArrayList.add(n);
					break;
				}
				default:{
					System.out.println("Unknown Vehicle Type");
				}
			}
		}

	}
	


	public static void FillInVehicles(){
		/*
		*	Data is inserted into the ArrayLists via this function.
		*/
		String choice;
		int choiceOfVehicle;
		do {
			try {
				choice = JOptionPane.showInputDialog("Choose Vehicle Type\n(1).Car\n(2).Truck");
				choiceOfVehicle = Integer.parseInt(choice);
			}
			catch(Exception e)
			{
				System.out.println("User enterd incorrect details");
				choiceOfVehicle = 9;
			}
		}while(choiceOfVehicle!=1 && choiceOfVehicle!=2 );
		if(choiceOfVehicle == 1)
		{
			Car newCar = new Car();
			FillBasicVehicle(newCar);
			String PassengerNumber = userInput("\nEnter Passengers Number: ", "Integer", newCar.VehicleType());
			newCar.setPassengersNumber(Integer.parseInt(PassengerNumber));
			CarArrayList.add(newCar);
		}
		else if(choiceOfVehicle == 2)
		{
			Truck newTruck = new Truck();
			String CargoWeight = userInput("\nEnter Cargo Weight: ", "number", newTruck.VehicleType());
			newTruck.setCargoWeight(Double.parseDouble(CargoWeight));
			FillBasicVehicle(newTruck);
			TruckArrayList.add(newTruck);
		}
	}

	public static void FillBasicVehicle(Vehicle n){
		/*
		 * Instead of creating the following statements are written 3 times they
		 * are now only called 3 times, making the code readable, and flexible for later changes.
		 */

		/*
		* All variable attributes are of the objects are obtained from the userInput function
		* which will sanitise the result of unauthorised data types typed by the user.
		*/
		String name = userInput("Enter owner Name: ", "text", n.VehicleType());
		if(name.equals("") || name.equals(" "))
		{
			System.out.println("owner name not assigned");
		}
		else {
			n.setOwnerName(name);
		}
		String rentalDays = userInput("\nEnter total rent days: ", "Integer", n.VehicleType());
		n.RentalItem(Integer.parseInt(rentalDays));
		
		String rentalCostPerDay = userInput("\nEnter Rental Cost per Day: ", "number", n.VehicleType());
		n.setRentalCostPerDay(Double.parseDouble(rentalCostPerDay));
		
	}

	public static void PrintAllVehicles() throws IOException{
		/*
 		 * Function Stores all data of the properties into
 		 * OutputProperty.txt file.
 		 */
 		String formatedOutPut = "";
 		formatedOutPut += "-Car-\n";
 		//loop through each object in the ArrayList then store that details in the  formatedOutpUt String variable.
 		for(Car i: CarArrayList)
 		{
 			String text = "";
 			text += "Owner Name: "+i.getOwnerName()+"\n";
 			text += "Total Rental Days: "+i.getTotalRentalDays()+"\n";	
 			text += "Rental Cost Per Day: € "+i.getRentalCostPerDay()+"\n";
 			text += "Passengers Number: "+i.getPassengersNumber()+"\n";
 			text +="*******************************************************\n";
 			formatedOutPut +=text;
 		}
 		formatedOutPut += "-Truck-\n";
 		for(Truck i: TruckArrayList)
 		{
 			String text = "";

 			text += "Owner Name: "+i.getOwnerName()+"\n";
 			text += "Total Rental Days: "+i.getTotalRentalDays()+"\n";
 			text += "Rental Cost Per Day: € "+i.getRentalCostPerDay()+"\n";
 			text += "Cargo Weight "+i.getCargoWeight()+"\n";
 			text +="*******************************************************\n";
 			formatedOutPut +=text;
 		}
 		
 		System.out.println(formatedOutPut);
 		//formatedOutPut contents is then stored in the "OutPutVehicle" text file.
 		BufferedWriter writer = new BufferedWriter(new FileWriter("OutputVehicle.txt"));
 		writer.write(formatedOutPut);
 		writer.close();
	}
	
	public static void CalculateVehicleTotalIncome()
	{
		/*
		 * This method calculates the total income of MyBnB's Vehicle rent services.
		 */
		double totalCost=0;
		for(Car i: CarArrayList)
		{
			totalCost += i.getRentalCostPerDay() * i.getTotalRentalDays();
		}
		for(Truck i: TruckArrayList)
		{
			totalCost+= (i.getRentalCostPerDay() *i.getTotalRentalDays())+i.getCargoWeight();
		}
		JOptionPane.showMessageDialog(null, "The Total Cost is: "+Double.toString(totalCost));
	}

	/*
	*	SWING IMPLEMENTATIONS BELOW
	*/

	public static void MainMenu()
	{
		/*
		 * This function is the main GUI interface of the program.
		 */
		JFrame frame = new JFrame();
		frame.setTitle("Welcome to MyBnb");
		frame.setSize(600,400);
		JPanel mainPanel = new JPanel();
		String[] choiceList = {"Create New Vehicle","Calculate Vehicle Total Income","Print Vehicles",
				"Create New Property", "Edit Property", "Calculate Property Total Income", "Print Properties"};
		
		JComboBox userChoice = new JComboBox(choiceList);
		ListenForComboBox lComboBox = new ListenForComboBox();
		userChoice.addActionListener(lComboBox);
		
		mainPanel.add(userChoice);
		frame.add(mainPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
	}

	private static class  ListenForButton implements ActionListener{
		//Thhis listens for the OK in the givenrentalProeprty gui.
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			int propertyID = 0;
			int rentDays = 0;
			boolean success = true;
			try {
				
			propertyID = Integer.parseInt(PropertyIDJFrame.getText());
			rentDays = Integer.parseInt(RentDaysJFrame.getText());
			}catch(Exception a){
				System.out.println("|"+PropertyIDJFrame.getText()+"|");
				System.out.println("Error within system.");
				success = false;
			}
			if(success == true)
			{
				findUser(propertyID,rentDays);
				try {
					PrintAllProperties();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//Once the calculation is completed the frame is closed.
			
		}
		
	}
	
	private static class ListenForComboBox implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JComboBox<?> choice = (JComboBox<?>)e.getSource();
	        String choiceName = (String)choice.getSelectedItem();
	        
	        
	        switch(choiceName) {
	        case "Create New Vehicle": FillInVehicles();break;
	        case "Calculate Vehicle Total Income":CalculateVehicleTotalIncome(); break;
	        case "Print Vehicles": try {
					PrintAllVehicles();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} break;
	        case "Create New Property":FillInProperties();break;
	        case "Edit Property":GivenRentalProperty(); break;
	        case "Calculate Property Total Income": CalculatePropertyTotalIncome(); break;
	        case "Print Properties":try {
					PrintAllProperties();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} break;
	        default: System.out.println("Something Went Wrong.");
	        }
		}
		
	}
	
	
	
}
