
/**
 * Write a description of class Customer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer
{
    private static int counter=0;
    private String Id;
    private String Customer;
    private BankAccount account;
    private double balance;
    
    public Customer(String name)
    {
       this.Customer=name;
       counter+=1;
       Id="Customer c" + counter;
       
    }
    
    public String getId()
    {
        return Id;
    }
    
    public String getName()
    {
        return Customer;
    }

    public void addAccount(BankAccount b)
    {
        account=b;
        account.accountOwner(Customer.this);
        
    }
    
    public double getAssetTotal()
    {
        balance=account.getBalance();
        return balance;
    }
    
}
