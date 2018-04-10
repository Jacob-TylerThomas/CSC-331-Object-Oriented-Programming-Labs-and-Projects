
/**
 * Write a description of class BankAccount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BankAccount
{
    private BankAccount overdraftAccount=null;
    private double balance;
    private String accountNumber;
    private static int counter = 0;
    private BankAccount overdraft;
    private Customer accountOwner;
    
    public BankAccount(double b)
    {
          balance= b;
          counter+=1;
          accountNumber="b"+counter;
    }
    
    public String getAccountNumber()
    {
        return accountNumber;
    }
   
    public double getBalance()
    {
        return balance;
    }
    
    public void deposit(double b)
    {
        balance+=b;
    }
    
    public void withdraw(double amt)
    {
        balance -= amt;
        if (balance < 0)
        {
            Overdraft();
        }
        
    }
    
    public void transfer(double amt, BankAccount other)
    {
        this.withdraw(amt);
        other.deposit(amt);
    }
    
   public void setOverdraftAccount(BankAccount b)
   {
       this.overdraft=b;
   }
   
    public void Overdraft()
    {
        while (balance <0)
        {
            overdraft.withdraw(50);
            balance += 50;
        }
    }
        
   public void accountOwner(Customer own)
    {
        accountOwner=own;
    }
    
}
