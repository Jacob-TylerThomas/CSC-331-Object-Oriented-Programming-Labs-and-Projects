
public class Fraction
{

   private int numerator;
   private int denominator;
   private int fraction;
   private double a;
   private double b;
   private int nNumb;
   private int nNumb1;
   private int nDenom;
   private int greatestCMD;
   public Fraction(int a, int b)
    {
     numerator=a;
     denominator=b;
     greatestCMD= gcd(numerator, denominator);
     if (greatestCMD !=0)
     {
         this.numerator=numerator/greatestCMD;
         this.denominator=denominator/greatestCMD;
     }
   }

    public Fraction(double b)
    {
        numerator=(int) (b*10);
        denominator=1*10;
    }
    
  public double toDecimal()
    {
        a=(double)numerator;
        b=(double)denominator;
        return (a/b);
        
    }
    
  public String toString()
  {
      if (denominator%numerator==0) 
      {
          greatestCMD=gcd(numerator, denominator);
        }
      if (greatestCMD !=0)
      {
          return (numerator/greatestCMD) + "/" + (denominator/greatestCMD);
        }
      return numerator + "/" + denominator;
    }
        
   public int gcd(int numerator, int denominator)
   {
       if ((numerator%denominator)==0)
       {
           return denominator;
        }
        else
        {
            return gcd(denominator, numerator%denominator);
        }
    }
   
  
   public Fraction add(Fraction a)
    {
        if (denominator==a.denominator)
        {
            nNumb=numerator + a.numerator;
            return new Fraction (nNumb, denominator);
        }
        else if(denominator != a.denominator) 
        {
            nDenom=denominator*a.denominator;
            nNumb=(numerator*a.denominator) + (a.numerator*denominator);
            return new Fraction(nNumb, nDenom);
        }
        
        return new Fraction (numerator,denominator);
    }
    
   public Fraction mult(Fraction a)
    {
     nNumb=numerator*a.numerator;
     nDenom=denominator*a.denominator;
     return new Fraction(nNumb, nDenom);
    }
    
    public Fraction sub(Fraction a)
    {
        if (denominator==a.denominator)
        {
            nNumb=numerator*a.denominator;
            return new Fraction(nNumb, denominator);
        }
        else if (denominator != a.denominator)
        {
            nDenom=denominator*a.denominator;
            nNumb=(numerator*a.denominator) - (a.numerator*denominator);
            return new Fraction(nNumb, nDenom);
        }
        return new Fraction(nNumb, nDenom);
    }
    public Fraction div(Fraction a)
    {
        nNumb=numerator*a.denominator;
        nDenom=denominator*a.numerator;
        return new Fraction(nNumb, nDenom);
    }
    
    public int compareTo(Fraction a)
    {
        nDenom=denominator*a.denominator;
        nNumb=numerator*a.denominator;
        nNumb1=a.numerator*denominator;
        if(nNumb==nNumb1)
        {
            return 0;
        }
        else if (nNumb > nNumb1)
        {
            return 1;
        }
        else if (nNumb < nNumb1)
        {
            return -1;
        }
        return 3;
    }
    
        
}
