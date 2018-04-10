import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class LibManager {
    private ArrayList<Book> bookList = new ArrayList<Book>();
    private ArrayList<Patron> patronList = new ArrayList<Patron>();
    private String [] menuOptions;
    private String userInput;
    private String title;
    private String author;
    private String year;
    private static int counter;
    private String name;
    private String id;
    private String test;
    private String stripped;
    private String sId;
    private String sTitle;
    private String sAuthor;
    private String sYear;
    private Book locatedBook;
    private Patron locatedPatron;
    private String revID;
    private String bookIDs;
    private String abookIDs;
    private String patronIDs;
    private String apatronIDs;
    private Book rentBook;
    private Book foundBook;
    private Patron foundPatron;
    
    public static void main(String[] args) {
        LibManager lm = new LibManager();
        lm.execute();
    }

    public LibManager() {
        bookList = loadBooks("books.txt");
        patronList = loadPatrons("patrons.txt");
        recordLoans("loans.txt");

        menuOptions = new String[] { "Add Book", "Add Patron", "List Books",
            "List Patrons", "List By Author", "List By Year", "Lend Book",
            "Show Borrower", "Show Borrowed Books", "Remove Book", "Remove Patron", "Exit" };
    }

    private void execute() {

        while (true) {
            int opt = getMenuOption();
            switch (opt) {
                case 1:
                addBook();
                break;
                case 2:
                addPatron();
                break;
                case 3:
                listBooks();
                break;
                case 4:
                listPatrons();
                break;
                case 5:
                listByAuthor();
                break;
                case 6:
                listByYear();
                break;
                case 7:
                lendBookToPatron();
                break;
                case 8:
                showBorrowers();
                break;
                case 9:
                showBorrowedBooks();
                break;
                case 10:
                removeBook();
                break;
                case 11:
                removePatron();
                break;
                case 12:
                System.out.println("Exiting..");
                System.exit(0);
                break;
                default:
                System.out.println("No such option");
            }
        }

    }
    
    private int getMenuOption() {

        System.out.println("Select one of the following options");
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println("\t" + (i+1) + "." + menuOptions[i]);
        }

        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        return choice;
    }

    /* MAKE NO CHANGES ABOVE THIS LINE */
    /* COMPLETE ALL THE CODE STUBS BELOW */
    
    private ArrayList<Book> loadBooks(String filename) 
    {
        ArrayList<String> lines=new ArrayList<>();
        Scanner s= new Scanner(System.in);
        File infile= new File (filename);
        
        try
        {
            s=new Scanner(infile);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
        while (s.hasNext())
        {
            String line=s.nextLine();
            Scanner lineScanner=new Scanner(line);
            lineScanner.useDelimiter(";");
            
            String id=lineScanner.next();

            String title=lineScanner.next();

            String author=lineScanner.next();

            String year=lineScanner.next();
            
            sId=stripped(id);
            sTitle=stripped(title);
            sAuthor=stripped(author);
            sYear=stripped(year);
           
            Book newBook=new Book (sId, sTitle, sAuthor, sYear);
                        
            bookList.add(newBook);
        }
      
        return bookList;
   }
    
    public String stripped(String b)
    {
        stripped=b.replace("\t", "");
        stripped.trim();
        return stripped;
    }
    
    private ArrayList<Patron> loadPatrons(String filename) {
        ArrayList<String> lines=new ArrayList<>();
        Scanner s=new Scanner(System.in);
        File infile =new File(filename);
        try
        {
            s=new Scanner(infile);
        }
        
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        while (s.hasNext())
        {
            String line=s.nextLine();
            Scanner lineScanner=new Scanner(line);
            lineScanner.useDelimiter("\t");
            
            String id=lineScanner.next();
            String name=lineScanner.next();
            
            Patron newPatron=new Patron(id, name);
            
            patronList.add(newPatron);
        }
        
        return patronList;
    }

    private void recordLoans(String filename) { //prevents program from running
//         Scanner s=new Scanner(System.in);
//         File infile =new File(filename);
//         try
//         {
//             s=new Scanner(infile);
//         }
//         
//         catch(FileNotFoundException e)
//         {
//             e.printStackTrace();
//         }
//         while (s.hasNext())
//         {
//             String line=s.nextLine();
//             Scanner lineScanner=new Scanner(line);
//             lineScanner.useDelimiter("\t");
//             
//             String bookIDs=lineScanner.next();
//             String abookIDs=stripped(bookIDs);
//             String patronIDs=lineScanner.next();
//             String apatronIDs=stripped(bookIDs);
//             
//             Book rentBook=locateBook(abookIDs);
//             Patron usePatron=locatePatron(patronIDs);
//             
//             rentBook.setBorrower(usePatron);
//             //usePatron.borrowBooks(rentBook); //keeps returning null
//         }        
     }

     private Book locateBook(String bookId) {
        for (Book book: bookList)
        {
            if (book.hasID(bookId))
            {
                locatedBook=book;
            }
        }
         return locatedBook;
     }

     private Patron locatePatron(String patronID)
     {
         for(Patron patron: patronList)
         {
             if(patron.hasID(patronID))
             {
                 locatedPatron=patron;
             }
         }
         return locatedPatron;
     }

      private void showBorrowedBooks() //unsure how to finish
      {
        Scanner s=new Scanner(System.in);
        System.out.print("Enter a patron ID: ");
        String userInput=s.nextLine();
        
      }

     private void showBorrowers() {
        Scanner s=new Scanner(System.in);
        System.out.print("Enter borrowed book ID: ");
        String userInput=s.nextLine();
        foundBook=locateBook(userInput);
        Patron patronRent=foundBook.getBorrower();
        System.out.println(patronRent);
     }

     private void lendBookToPatron() {
        Scanner s=new Scanner(System.in);
        System.out.print("Enter a patron ID to lend: ");
        String userInput=s.nextLine();
        Patron usePatron=locatePatron(userInput);
        System.out.print("Enter a book ID to lend: ");
        userInput=s.nextLine();
        Book rentBook=locateBook(userInput);
        if(rentBook.hasBorrower()==true)
        {
            System.out.println("This book is already borrowed");
        }
        else
        {
            rentBook.setBorrower(usePatron);
        }
     }

    private void listByYear() {
        Scanner s=new Scanner(System.in);
        System.out.print("Enter a minimum year to search for: ");
        int min=s.nextInt();
        System.out.print("Enter a maximum year to search for: ");
        int max=s.nextInt();
        for (Book book: bookList)
        {
            if ((book.getYear() <= max) && (book.getYear() >= min))
            {
                System.out.println(book);
            }       
        }
    }

    private void listByAuthor() {
        Scanner s=new Scanner(System.in);
        System.out.print("Enter author to search for: ");
        userInput=s.nextLine();
        for (Book book: bookList)
        {
            if (book.hasAuthor(userInput))
            {
                System.out.println(book);
            }
        }
        
    }

    private void listPatrons() {
        System.out.println(patronList);       
    }

    private void listBooks() {
        System.out.println(bookList);
    }

    private void addPatron() {
        int counter=1001;
        Scanner userInputLastName=new Scanner(System.in);
        System.out.print("Enter patron's last name: ");
        name=userInputLastName.nextLine();
        if (name!=null)
        {
            String id="P" +counter;
            Patron newPatron= new Patron(id, name);
            patronList.add(newPatron);
            counter++;
        }        
    }

    private void addBook() {
        int counter=1002;
        Scanner userInputTitle=new Scanner(System.in);
        Scanner userInputAuthor=new Scanner(System.in);
        Scanner userInputYear=new Scanner(System.in);
        System.out.print("Enter the book's title: ");
        title=userInputTitle.nextLine();
        System.out.print("Enter the book's author: ");
        author=userInputAuthor.nextLine();
        System.out.print("Enter the book's publication year: ");
        year=userInputYear.nextLine();
        
        if (title !=null)
        {
            String id="B"+counter;
            Book newBook=new Book(id, title, author, year);
            bookList.add(newBook);
            counter++;
        }
    }

    private void removePatron() 
    {
        Scanner s= new Scanner(System.in);
        System.out.print("Enter a patorn ID: ");
        userInput=s.nextLine();       
        Iterator<Patron> iter = patronList.iterator();

        while (iter.hasNext()) 
        {
            Patron str = iter.next();

            if (str.hasID(userInput))
            {
                iter.remove();
            }
   
        }
    }

    private void removeBook() 
    {
        Scanner s= new Scanner(System.in);
        System.out.print("Enter a book ID: ");
        userInput=s.nextLine();
        Iterator<Book> iter = bookList.iterator();
        
        while (iter.hasNext()) 
        {
            Book str = iter.next();

            if (str.hasID(userInput))
            {
                iter.remove();
            }
        }     
      }
}