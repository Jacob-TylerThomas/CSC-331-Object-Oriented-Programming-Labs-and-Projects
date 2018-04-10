
/**
 * Write a description of class Book here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Book
{   
    private String bookID;
    private String bookAuthor;
    private String bookTitle;
    private String bookYear;
    private Patron renter;
    private boolean borrower;
    private int bYear;
       
    public Book (String id, String title, String author, String year)
    {
        bookID=id;
        bookTitle=title;
        bookAuthor=author;
        bookYear=year;
    }
    
    public String toString()
    {
        return "ID number: " + bookID +" "+ "Title: " + bookTitle +" " + "Author: " + bookAuthor +" " + "Year: " + bookYear+"\n";
    }
    
    public String getauthor()
    {
        return bookAuthor;
    }
    
    public String getyear()
    {
        return bookYear;
    }
    
    public String getTitle()
    {
        return bookTitle;
    }
        
    public boolean hasID(String ID)
    {
        return this.bookID.equals(ID);
    }
    
    public boolean hasAuthor(String author)
    {
        return this.bookAuthor.equals(author);
    }
    
    public boolean hasTitle(String title)
    {
        return this.bookTitle.equals(title);
    }
    
    public boolean hasYear(String year)
    {
        return this.bookYear.equals(year);
    }
    
    public void setBorrower(Patron patronID)
    {
        renter=patronID;
        borrower=true;
    }
    
    public Patron getBorrower()
    {
        return renter;
    }
    
    public int getYear()
    {
        bYear=Integer.parseInt(bookYear);
        return bYear;
    }
    
    public boolean hasBorrower()
    {
        if(borrower==true)
        {
            return true;
        }
        return false;
    }   
}
    
//     public Arraylist<Book> rentedBooks() //unsure how to finish
//     {
//         