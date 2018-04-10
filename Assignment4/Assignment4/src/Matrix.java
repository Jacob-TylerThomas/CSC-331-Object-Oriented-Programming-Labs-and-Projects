import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Matrix class method for Matrix assignment .
 *
 * Purpose: To allow the implementation of various types of matrix algebra
 */
public class Matrix {
	private int rows=0;
	private int cols=0;
	private double[][] data;
	private int rowLines=0;
	private int colLines=0;
	private double[][] b;
	
	/**
	 * Initializes a matrix 
	 *
	 * @param b creates a double to store the matrix in
	 */
	public Matrix (double [][] b)
	{
		data=b;
		rows=data.length;
		cols=data[0].length;
	}
	
	/**
	 * Overrides the first Matrix method so that it will take a file name
	 *
	 * @param filename takes a file name
	 */
	public Matrix (String filename)
	{   
		rows=getRows(filename);
		cols=getCols(filename);
		data=new double[rows][cols];
		File file=new File (filename);
		try {		        
		        Scanner sc = new Scanner(file);
	           
	            while (sc.hasNextLine()) 
	            {
	                for (int i = 0; i < rows; i++) 
	                {
	                    for (int j = 0; j < cols; j++) 
	                    {
	                        data[i][j] = sc.nextDouble();
	                    }
	                }
	            }
	            sc.close();
			}
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}	
	}	
	/**
	 * Gets the row size of a file
	 *
	 * @param Filename
	 * 
	 * Returns the number of rows
	 * 
	 * @return rowLines
	 */
	public int getRows(String Filename)
	{
		File file=new File(Filename);
		try 
		{
			Scanner scanner=new Scanner(file);
			while (scanner.hasNextLine())
			{
				rowLines++;
				scanner.nextLine();
			}
			scanner.close();
		}
		
		catch (FileNotFoundException e)
		{
			e.fillInStackTrace();
		}

		return rowLines;
	}
	
	/**
	 * Gets the column size of a file
	 *
	 * @param filename
	 * 
	 * Returns the number of columns
	 * 
	 * @return colLines
	 */
	public int getCols(String filename)
	{
		File file=new File (filename);
		try
		{
			Scanner sc = new Scanner(file);
			while (sc.hasNext())
			{
				String line=sc.nextLine();
				
				if (line.contains(" "))
				{
				colLines++;
				}
			}
			sc.close();
		}
		catch (FileNotFoundException e)
		{
			e.fillInStackTrace();
		}
		colLines++;
		return colLines;
	}

	/**
	 * Overrides the built in toString method and displays the matrix properly
	 * 
	 * Returns a formatted matrix
	 * 
	 * @return matToString
	 */
	@Override
	public String toString()
	{	
	System.out.print( rows + " "+  "x" + " "  + cols + " " + "matrix" + "\n");
	
	String matToString = "";

    for (int i = 0 ; i<this.rows ; i ++ ){
        for (int j = 0 ; j < this.cols ; j++){
        	matToString += data[i][j] + " ";
        }
        matToString += "\n";
    }
	
	return matToString;
	}
	/**
	 * Overrides the equals method so that arrays can be properly compared
	 *
	 * @param s
	 * 
	 * Returns true if the arrays are equal but otherwise returns false by default
	 * 
	 * @return false
	 */
	public boolean equals(Matrix s) 
	{
		int x=rows;
		int z=cols;
		double[][] a = new double[x][z];
        for (int i = 0; i < x; i++)
            for (int j = 0; j < z; j++)
                a[i][j] = data[i][j];
		
		Matrix testing=new Matrix (a);
		
		List<Matrix> test=Arrays.asList(testing);
		List<Matrix> test1=Arrays.asList(s);
		boolean isTrue=false;
		Set<Matrix> set = new HashSet<Matrix>(test);		
		Set<Matrix> set2 = new HashSet<Matrix>(test1);
		if (set.containsAll(set2))
		{
			isTrue=true;
		}
			     
	    return isTrue;
	}
	
	/**
	 * Transposes a given matrix
	 *
	 * Returns the transposed matrix
	 * 
	 * @return trans
	 */
	public Matrix transpose() //fails due to equals method
	{
		int m = rows;
        int n = cols;
        double[][] b = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                b[j][i] = data[i][j];
        Matrix trans=new Matrix(b);
        
        return trans;
	}
	
	/**
	 *Adds two matrices together 
	 *
	 * @param b
	 * 
	 * Returns the matrices that were added together as a single matrix
	 * 
	 * @return adds
	 */
	public Matrix add(Matrix b) 
	{
	       int addRows = rows;
	       int addColumns = cols;
	       double[][] result = new double[addRows][addColumns];
	       for (int i = 0; i < addRows; i++) {
	           for (int j = 0; j < addColumns; j++) {
	               result[i][j] = data[i][j] + this.b[i][j];
	           }
	       }
	       Matrix adds=new Matrix(result);
	       return adds;
	   }
	
	/**
	 * Multiples a matrix by a double
	 *
	 * @param b
	 * 
	 * Returns the multiplied matrix
	 * 
	 * @return data
	 */
	
	public double[][] mult (double b)
	{
				
		for (int i=0; i<rows; i++) 
		{
			for (int j=0; j<cols; j++)
			{
				  data[i][j] = data[i][j] * b;

			}
		}
		return data;
	}
	/**
	 * Multiples a given matrix by another matrix
	 *
	 * @param b
	 * 
	 * Returns the multiplied matrix
	 * 
	 * @return null
	 */
	public Matrix mult(Matrix b)
	{
		return null;
		
	}
}
