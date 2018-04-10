public class Patron
{
    private String pID;
    private String pName;
    private Book renter;
    
       
    public Patron (String ID, String name)
    {
        pID=ID;
        pName=name;
    }

    public String toString()
    {
        return "ID:" + pID +"\t"+ "Name:" + pName +"\n";
    }
    
    public String getName()
    {
        return pName;
    }
    
    public String getID()
    {
        return pID;
    }
    
    public boolean hasID(String ID)
    {
        return this.pID.equals(ID);
    }
  
    public void borrowBooks(Book bookId)
    {
        renter=bookId;
    }
}
