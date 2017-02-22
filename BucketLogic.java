/*
 * Hans Marasigan February 2017
 */

import java.util.Scanner;
import java.math.*;

public class BucketLogic {

	private int userFirstBucket;
	private int userSecondBucket;
	private int userRequiredBucket;
	
	private static String prompt1="How many gallons are in your first bucket?";
	private static String prompt2="How many gallons are in your second bucket?";
	private static String prompt3="How many gallons do you want to have?";
	//The state tells the bucket prompts which variable to fill in for.
	private int state=1;
	
	BucketApp bucketApp=new BucketApp();
	
	public BucketLogic(){
		setUserFirstBucket(0);
		setUserSecondBucket(0);
		do{//start again
		promptStart();
		do{//prompt buckets
		promptBucket(prompt1,1);
		promptBucket(prompt2,2);
		promptBucket(prompt3,3);
		}while(infCheck());
		do{//bucket check
		BucketCheck();
		}while(infCheck());
		bucketAppSetter();
		promptBucketFunction();
		}while(startAgain());
	}
	
	//Setters and getters
	public int getUserFirstBucket() {
		return userFirstBucket;
	}
	public void setUserFirstBucket(int userfirstBucket) {
		this.userFirstBucket = userfirstBucket;
	}

	public int getUserSecondBucket() {
		return userSecondBucket;
	}
	public void setUserSecondBucket(int usersecondBucket) {
		this.userSecondBucket = usersecondBucket;
	}

	public int getUserRequiredBucket() {
		return userRequiredBucket;
	}
	public void setUserRequiredBucket(int userRequiredBucket) {
		this.userRequiredBucket = userRequiredBucket;
	}
	
	//sets the buckets up
	public void bucketAppSetter(){
		bucketApp.setLargeG(this.getUserFirstBucket());
		bucketApp.setSmallG(this.getUserSecondBucket());
		bucketApp.setReqG(this.getUserRequiredBucket());
	}
	//prompts for the user
	//beginning prompt 
	public void promptStart(){
		System.out.println("Welcome to the bucket counter");
		System.out.println("I will be figuring out what and how many steps are needed to solve the bucket problem");
		System.out.println("I will now be prompting you for your bucket sizes.");
	}
	
	//prompting for required inputs (large,small, required bucket size)
	public void promptBucket(String prompts, int state){
		int userInteger= 0;
		this.state=state;
		do{
		System.out.println(prompts);
		
			Scanner scan= new Scanner(System.in);
			try{
				userInteger= scan.nextInt();
			}
			catch(Exception e ){
				System.out.println("Error I only take integers I cannot read text yet." );
				System.out.println("Please insert an integer and also");
			}
		}while(inputCheck(userInteger, state));
		bucketSetter(state,userInteger);
	}
	
	//sets sets the variables that will be passed to the bucketapp class
	public void bucketSetter(int state, int userInt){
		switch(state){
		case 1:state=1;
			this.setUserFirstBucket(userInt);
			break;
		case 2:state=2;
			this.setUserSecondBucket(userInt);
			break;
		case 3:state=3;
			this.setUserRequiredBucket(userInt);
			break;
		default:
			break;				
		}
	}
	
	
	//checking user input for the bucket size questions and will be giving back a response.
	public boolean inputCheck(int userInput, int state){			
		if (userInput==0){
			System.out.println("Zero is not an acceptable input");
			System.out.println("Please insert a bucket size");
			return true;
		}
		else if (this.state==3){
			if (userInput>this.getUserFirstBucket()&&userInput>this.getUserSecondBucket())
			{
				System.out.println("The amount of gallons you want cannot be greater than either of the bucket you have!");
				System.out.println("Please enter a desired amount less than either of the buckets you have.");
				return true;
			}
			else 
				return false;
		}
		
		else if (userInput<0){
			System.out.println("Don't be silly, Bucket Sizes cannot be negative.");
			System.out.println("Please insert a positive bucket size");
			return true;
			}
		else
			return false;
	}
	
	//prompts the user if the thing will be an inf loop.
	public boolean infCheck(){
		int subChoices=Math.abs(this.getUserFirstBucket()-this.getUserSecondBucket());
		int smallerNumber;
		
		//sets the smaller number
		
		
		if (this.getUserFirstBucket()<this.getUserSecondBucket())
			smallerNumber=this.getUserFirstBucket();
		else
			smallerNumber=this.getUserSecondBucket();
		
		if (smallerNumber==subChoices){
			System.out.println("These numbers do not work because the difference is equal to the smaller bucket.");
			System.out.println("This means the values of the buckets will never change.");
			System.out.println("I will ask you again about your bucket sizes.");
			return true;
		}
		else if ((this.getUserFirstBucket()%2==0) && (this.getUserSecondBucket()%2==0) && (this.getUserRequiredBucket()%2==1))
		{
			System.out.println("You have two buckets that can hold even numbers while the desired amount is odd.");
			System.out.println("There is no way to get an odd number from only even numbers.");
			System.out.println("I will ask you again about your bucket sizes.");
			return true;
		}
		else if(subChoices%smallerNumber==0){
			System.out.println("Since the smaller bucket is a multiple of the difference of the buckets. ");
			System.out.println("This will result in an infinite loop because it will repeat the amounts. ");
			System.out.println("I will ask you again about your bucket sizes.");
			return true;
		}
		else
			return false;
	}
	
	
	
	//prompts the user to confirm that their Bucket numbers are correct.
	public void BucketCheck(){
		int userCheck=0;
		do{
		System.out.println("I would like to confirm your bucket request.");
		System.out.println("1) Your first bucket was " + this.getUserFirstBucket() + " gallon(s)");
		System.out.println("2) Your second bucket was " + this.getUserSecondBucket() + " gallon(s) ");
		System.out.println("3) You want " + this.getUserRequiredBucket() + " gallon(s)");
		System.out.println("If you are satisfied with these answers please enter 4.");
		System.out.println("However if you would like to change any of these values please enter 1, 2, or 3.");
		
		
			Scanner scan= new Scanner(System.in);
			try{
				userCheck= scan.nextInt();
			}
			catch(Exception e ){
				System.out.println("Error I only take integers becasue I cannot read text yet.\n" );
				
			}
		}while(inputCheck2(userCheck));
		
	}
	
	//checks the user's second response to see whether or not it is one of the three choices and if it is 
	public boolean inputCheck2(int userCheck){
		switch(userCheck){
		case 1:userCheck = 1;
			promptBucket(this.prompt1,1);
			return true;
		case 2: userCheck = 2;
			promptBucket(this.prompt2,2);
			return true;
		case 3: userCheck = 3;
			promptBucket(this.prompt3,3);
			return true;
		case 4: userCheck = 4;
			System.out.println("Confirmed! \n");
			return false;
		default:
			System.out.println("That is not one of the choices. \n");
			return true;
		}
	}
	
	//prompts the user about what 
	public void promptBucketFunction(){
		int userReq=0;
		do{
		System.out.println("I can solve and tell you all the steps in three ways:");
		System.out.println("1: Filling the larger bucket, then transferring the water to the smaller bucket until the required amount is acquired");
		System.out.println("2: Filling the smaller bucket, then transferring the water to the larger bucket until the required amount is acquired");
		System.out.println("3: I can do both then tell you which method is faster and then tell you the steps");
		System.out.println("Which method would you like me to do?");
		System.out.println("Please type: 1, 2, or 3");
		
			Scanner scan= new Scanner(System.in);
			try{
				userReq= scan.nextInt();
			}
			catch(Exception e ){
				System.out.println("Error I only take integers I cannot read text yet." );
				System.out.println("Please insert an integer and also");
			}
		}while(inputCheck3(userReq));
	}
	//checks the user input for the bucket function
	public boolean inputCheck3(int userReq){
		switch(userReq){
		case 1:userReq = 1;
			bucketApp.bucketSolvingLS(false);
			return false;
		case 2: userReq = 2;
			bucketApp.bucketSolvingSL(false);
			return false;
		case 3: userReq = 3;
			bucketApp.solutionSpeed();
			return false;
		default:
			System.out.println("That is not one of the choices. \n");
			return true;
		}
		
	}

	//prompts the user if they would like to start over.
	public boolean startAgain(){
		String userString="";
		do{
		System.out.println("Would you like to do another bucket problem?");
		System.out.println("Y or N");
		
			Scanner scan= new Scanner(System.in);
			userString= scan.next();
		}while(inputCheck4(userString));
		if (((userString.equals("y")) || ((userString.equals("Y")))))
			return true;
		else 
			System.out.println("Thanks for using the app!");
			return false;
			

	}
	
	public boolean inputCheck4(String userString){
		if ((userString.equals("y")) || ((userString.equals("Y"))))
			return false;
		else if ((userString.equals("n")) || ((userString.equals("N"))))
			return false;
		else
			System.out.println("I can only understand Y or N.");
			return true;

	}
	
}
