package gaussianElimination;
import java.util.Scanner;

public class GaussianElimination
{
	
	protected int sizeY;
	protected int sizeX;
	protected float anArray[][];
	protected float resultsArray[];
	
	
	public static void main(String[] args) 
	{ 	
		GaussianElimination run = new GaussianElimination();
		
		run.userInput();
		run.printOriginal();
		run.process();
		run.printProcessed();
		run.backSubstitution();
		
	}
	
	

	
	public void userInput()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to my gaussian elimination calculator!\n");
		System.out.println("Enter the number of columns for your matrix,");
		System.out.println("giving one extra column for the solution of each equation");
		System.out.println("i.e.\n3x + 5y = 5 \n6x + 2y = 6 \nwould have 2 rows and 3 columns");
		sizeX = in.nextInt();
		System.out.println("Enter the numbers of rows for your matrix");

		sizeY = in.nextInt();	
			
		anArray = new float[sizeY][sizeX];	//Declares the array
		
		System.out.println("Now input the values for your matrix, input all the rows in one string with a comma inbetween\n"
				+ "e.g. [4 5 6; 3 5 4] would be input as 4,5,6,3,5,4");
		
		String input = in.next();
		in.close();
		String tmpArray[] = new String[sizeX * sizeY];
		
		tmpArray = input.split(",");							//Splits input string by the commas input by the user
		float tmpArray1[] = new float[sizeX * sizeY];
		
		for (int k = 0; k < (sizeX * sizeY); k++)				//Parses the string values of input into floats
		{
			tmpArray1[k] = Float.parseFloat(tmpArray[k]);
		}

		
		
		int i = 0;			//Used to keep track of which element of array to be processed
		int j = 0;

		for (int counter = 0; counter < sizeX * sizeY; counter++)	
		{		
			anArray[i][j] = tmpArray1[counter];
			j = j + 1;
			
			if (j == sizeX && i < sizeY - 1) 		//If j is the same value as the furthest right column, and i (row placekeeper) is not in the last row of the array
			{
				i = i + 1;
				j = 0;
			}
		}
	}

	
	public void printOriginal()
	{
		System.out.println("\nOriginal matrix"); //Prints original matrix to console
		
		for (int i = 0; i < sizeY; i++ ) 				//For each row, perform the for loop below to iterate through every value in matrix
		{
	   		for (int j = 0; j < sizeX; j++) 
	   		{
	   			if (j == (sizeX - 1))					//Not really necessary, but makes it obvious in the printed matrix which column is the results column
	   			{
	   				System.out.print("| ");
	   			}
	   			
	   			System.out.print(anArray[i][j] + " ");
	       	}
	   		System.out.println("");
		}
	}


	public void process()			//I have no idea how this works
	{
		float q;					//Declares variables that need to be accessable throughout the method
		float t;
		float z;
	
		for (int i = 0; i < sizeY; i++)
		{		
			for (i = 0; i < sizeY - 1; i++)
			{	
				t = anArray[i][i];
				z = (1 / t);
				
				for (int j = 0; j < sizeX; j++)
				{
					anArray[i][j] = anArray[i][j] * z;
				}
				
				for (int h = (sizeY - i -1); h>0;h--)
				{
					q = anArray[i+h][i];
					
					for (int j = 0; j < sizeX; j++)
					{
						anArray[i+h][j] = (anArray[i+h][j] - (q * anArray[i][j]));
					}
				}
			}
		}
	}	


	public void printProcessed()
	{
		System.out.println(""); System.out.println("Processed matrix"); //Prints processed matrix to console
		for (int i = 0; i < sizeY; i++) 
		{
	   		for (int j = 0;j < sizeX; j++) 
	   		{
	   			if (j == (sizeX - 1))
	   			{
	   				System.out.print("| ");
	   			}
	   			System.out.print((Math.ceil(anArray[i][j] * 100)/100) + " ");		//Rounds to 2dp
	       	}
	   		System.out.println("");
		}
		System.out.println("");
	}


	public void backSubstitution()		//I have no idea how this works

	{

		//Calculating results based on scenarios
		if (anArray[(sizeY-1)][(sizeX-1)] == 0 && anArray[(sizeY-1)][(sizeX-2)] == 0)		//If 0s in bottom row
		{
			System.out.println("There are infinite solutions.");
		}
		
		else if (anArray[(sizeY-1)][(sizeX-1)] != 0 && anArray[(sizeY-1)][(sizeX-2)] == 0)
		{
			System.out.println("There are no solutions.");
		}
		
		else if (anArray[(sizeY-1)][(sizeX-1)] != 0 && anArray[(sizeY-1)][(sizeX-2)] != 0)
		{
			int accessY = sizeY - 1;		//Makes reading code easier
			int accessX = sizeX - 1;
			resultsArray = new float[sizeY];
			resultsArray[accessY] = (anArray[(accessY)][(accessX)]/anArray[(accessY)][(accessX-1)]);
			
			for (int i = (accessY - 1); i >= 0; i--)
			{
				int t = accessY;
				int j = accessX - 1;
				
				while (anArray[i+1][j] != 0)
				{
					if (resultsArray[i] == 0)
					{
						resultsArray[i] = anArray[i][accessX] - resultsArray[t]*anArray[i][j]; 
						t = t - 1;
						j = j - 1;
					}
					
					else
					{
						while (anArray[i+1][j] != 0)
						{
							resultsArray[i] = resultsArray[i] - resultsArray[t]*anArray[i][j];
							t = t - 1;
							j = j - 1;
						}
					}
				}
			}
			
			for (int x = 0; x <= (sizeY - 1); x++)
			{
				System.out.println("x" + (x+1) + " = " + (Math.ceil(resultsArray[x]*100))/100);		//Rounds to 2dp
			}
		}
	}
}