/*
 * Hans Marasigan February 2017
 */

public class BucketApp {
	  private int largeG;
	  private int smallG;
	  private int reqG;
	  
	  private int largeAmount=0;
	  private int smallAmount=0;
	  
	  private int numOfMoves=0;
	  private int LSnumOfMoves=0;
	  private int SLnumOfMoves=0;

	  
	  private boolean check = false;
	  private boolean fasterMethod=false; //false = small to large, true = large to small
	  
	  
	  //constructor 
	  public BucketApp(int large, int small, int req){
		  if (large>small){
			  this.setLargeG(large);
			  this.setSmallG(small);
		  }
		  else
		  {
			  this.setLargeG(small);
			  this.setSmallG(large);
		  }
		  this.setReqG(req);
		  this.setLargeAmount(0);
		  this.setSmallAmount(0);
		  this.check=false;
		  this.setNumOfMoves(0);
	  }
	  //default constructor with the given requirements large 5, small 3, required 4
	  public BucketApp(){
		  this.setLargeG(5);
		  this.setSmallG(3);
		  this.setReqG(4);
	  }
//setters and getters
	public int getLargeG() {
		return largeG;
	}
	public void setLargeG(int largeG) {
		this.largeG = largeG;
	}
	public int getSmallG() {
		return smallG;
	}
	public void setSmallG(int smallG) {
		this.smallG = smallG;
	}

	public int getReqG() {
		return reqG;
	}
	public void setReqG(int reqG) {
		this.reqG = reqG;
	}

	public int getLargeAmount() {
		return largeAmount;
	}
	public void setLargeAmount(int largeAmount) {
		this.largeAmount = largeAmount;
	}
	public int getSmallAmount() {
		return smallAmount;
	}
	public void setSmallAmount(int smallAmount) {
		this.smallAmount = smallAmount;
	}

	public int getNumOfMoves() {
		return numOfMoves;
	}
	public void setNumOfMoves(int numOfMoves) {
		this.numOfMoves = numOfMoves;
	}
	
	 public int getLSnumOfMoves() {
		return LSnumOfMoves;
	}
	public void setLSnumOfMoves(int lSnumOfMoves) {
		LSnumOfMoves = lSnumOfMoves;
	}
	public int getSLnumOfMoves() {
		return SLnumOfMoves;
	}
	public void setSLnumOfMoves(int sLnumOfMoves) {
		SLnumOfMoves = sLnumOfMoves;
	}
	
	public boolean isFasterMethod() {
		return fasterMethod;
	}
	public void setFasterMethod(boolean fasterMethod) {
		this.fasterMethod = fasterMethod;
	}
	//checking if the required gallon has been reached
	public void checkG(){
	if ((this.getReqG()==this.getLargeAmount()) || (this.getReqG() == this.getSmallAmount()))
			{
				this.check = true;
			}
	else
		this.check = false;
}
//getting the sum of bucket amounts	
	public int sumBucketAmounts(){
		return this.getLargeAmount()+this.getSmallAmount();
	}
//filling, emptying, and transferring methods. 
	
	public void fillLarge(boolean silent){
		this.setLargeAmount(this.getLargeG());
		if(silent==false){
		System.out.println("Filling the large bucket.");
		}
		this.bucketStatus(silent);
	}
	public void emptyLarge(boolean silent){
		this.setLargeAmount(0);
		if(silent==false){
		System.out.println("Emptying the large bucket.");
		}
		this.bucketStatus(silent);
	}
	public void fillSmall(boolean silent){
		this.setSmallAmount(this.getSmallG());
		if(silent==false){
		System.out.println("Filling the small bucket.");
		}
		this.bucketStatus(silent);
	}
	public void emptySmall(boolean silent){
		this.setSmallAmount(0);
		if(silent==false){
		System.out.println("Emptying the small bucket");
		}
		this.bucketStatus(silent);
	}
	
	//check if either of buckets transferring to is full/empty.
	public boolean isLargeFull(){
		if (this.getLargeG()==this.getLargeAmount()){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean isSmallFull(){
		if (this.getSmallG()==this.getSmallAmount()){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean isLargeEmpty(){
		if (this.getLargeAmount()==0){
			return true;
		}
		else 
			return false;
	}
	public boolean isSmallEmpty(){
		if (this.getSmallAmount()==0){
			return true;
		}
		else 
			return false;
	}
	//the methods for transferring between buckets
	public void transferLS(boolean silent){
		//transfer from large to small
		if(this.getSmallG()>=this.sumBucketAmounts()){
			this.setSmallAmount(this.sumBucketAmounts());
			this.setLargeAmount(0);
			if (silent==false){
			System.out.println("Transferring all of the contents in the large bucket into the small bucket.");
			}
		}//if the large amount cannot fit in the small 
		else
		{
			int largeDifference;
			largeDifference=this.sumBucketAmounts()-this.getSmallG();
			this.setLargeAmount(largeDifference);
			this.setSmallAmount(this.getSmallG());
			if (silent==false){
			System.out.println("Transferring as much as possible from the large bucket into the small bucket.");
			}
		}
		bucketStatus(silent);
		
	}
	public void transferSL(boolean silent){
		//transfer from small to large
		if (this.getLargeG()>= this.sumBucketAmounts()){
			this.setLargeAmount(this.sumBucketAmounts());
			this.setSmallAmount(0);
			if(silent==false){
			System.out.println("Transferring all of the contents in the small bucket into the large bucket.");
			}
		}//if the large can fit the small amount set large amount to the sum
		else
		{
			int smallDifference;
			smallDifference=this.sumBucketAmounts()-this.getLargeG();
			this.setSmallAmount(smallDifference);
			this.setLargeAmount(this.getLargeG());
			if (silent==false){
			System.out.println("Transferring as much as possible from the small bucket into the large bucket.");
			}
		}//if large cannot fit the small amount then large becomes full and small becomes the difference
		bucketStatus(silent);
	}
	//resets the move amounts, number of moves and sets check back to false.
	public void reset(){
		this.setSmallAmount(0);
		this.setLargeAmount(0);
		this.setNumOfMoves(0);
		this.check=false;
	}
	//prints the status of the buckets.
	public void bucketStatus(boolean silent){
		if (silent==false){
		System.out.print("Large Bucket status " + this.getLargeAmount() + " / " + this.getLargeG() + " " );
		System.out.print("Small Bucket status " + this.getSmallAmount() + " / " + this.getSmallG() + " ");
		System.out.print("Desired amount is " + this.getReqG() + "\n");
		}
	}
	
	//the bucket method to figure out how many moves and what steps need to be taken.
	public void bucketSolvingSL(boolean silent){

		while(!check){
			if (this.isSmallEmpty()==true){
				this.fillSmall(silent);
				numOfMoves++;
			}
			else if (this.isLargeFull()==true){
				this.emptyLarge(silent);
				numOfMoves++;
			}
			else{
				this.transferSL(silent);
				numOfMoves++;
			}
			this.checkG();
		}
		if (silent==false){
			System.out.println("\n" + "This interaction used " + this.getNumOfMoves() + " number of moves." + "\n");
		}
		this.setSLnumOfMoves(this.getNumOfMoves());
		this.reset();
	
	}
	public void bucketSolvingLS(boolean silent){

		while(!check){
			if (this.isLargeEmpty()==true){
				this.fillLarge(silent);
				numOfMoves++;
			}
			else if (this.isSmallFull()==true){
				this.emptySmall(silent);
				numOfMoves++;
			}
			else{
				this.transferLS(silent);
				numOfMoves++;
			}
			this.checkG();
		}
		if (silent==false){
			System.out.println("\n" + "This interaction used " + this.getNumOfMoves() + " number of moves." + "\n");
		}
	this.setLSnumOfMoves(this.getNumOfMoves());
	this.reset();
	
	}
	
	//the bucket method that figures out which mode was faster.
	public void fasterThan(){
		
		System.out.println("Starting with the smaller bucket has " +this.getSLnumOfMoves() + "steps and starting with the larger bucket has " + this.getLSnumOfMoves() + "steps.");
		if (this.getSLnumOfMoves()>this.getLSnumOfMoves()){
			System.out.println("It is faster transferring from " + this.getLargeG() + " gallon bucket to the " + 
		this.getSmallG() + " gallon bucket than the other way around." + "\n");
			this.setFasterMethod(true);
		}
		else
		{
			System.out.println("It is faster transferring from " + this.getSmallG() + " gallon bucket to the " + 
		this.getLargeG() + " gallon bucket than the other way around." + "\n");
			this.setFasterMethod(false);
		}
	}
	//final function that does both of the methods to figure out which is faster. Then it reveals the faster one.
	public void solutionSpeed(){
		bucketSolvingSL(true);
		bucketSolvingLS(true);
		fasterThan();
		if(this.isFasterMethod()==true){
			bucketSolvingLS(false);			
		}
		else
		{
			bucketSolvingSL(false);
		}
		
	}
}
