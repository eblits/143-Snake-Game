import java.util.Scanner;

public class Book {

    //Create our general characteristics that each book will have
    public String bookName;
    public String bookAuthor;
    public int bookPageCount;
    public String bookGenre;
    public int bookProgress;
    public double bookRating;
    Scanner input;

    // Default constructor
    public Book() {
    }
    // Constructor with parameters so we can interpret our text file.
    public Book(String[] data) {
        // Interpret our data array for each book and set their characteristics
        this.bookName = data[0];
        this.bookAuthor = data[1];
        this.bookPageCount = Integer.parseInt(data[2]);
        this.bookProgress = Integer.parseInt(data[3]);
        this.bookGenre = data[4];
        this.bookRating = Double.parseDouble(data[5]);
    }

    //Method to add a book to the Bookcase
    public void addBook() {

        //Get user input and set each characteristic to that input.
        this.input = new Scanner(System.in);
        System.out.println("Please enter the Book Name:");
        this.bookName = input.nextLine();

        // For each book in array 'books'
        for(Book book: Main.books) {
            //Search if the input term matches a book we have stored in our array and then return that book;
            if(book.bookName.equalsIgnoreCase(this.bookName)) {
                System.out.println("This Book Already Exists");
                System.out.println("Press 1 to retry");
                if(input.nextInt() == 1){
                    Main.menu();
                }
            }
        }
        System.out.println("Now enter the Author Name:");
        this.bookAuthor = input.nextLine();
        System.out.println("Now the Page Count:");
        this.bookPageCount = input.nextInt();
        input.nextLine();
        System.out.println("Enter your Page Progress:");
        this.bookProgress = this.input.nextInt();
        input.nextLine();
        System.out.println("Enter the Genre (Fiction/Non-Fiction):");
        this.bookGenre = input.nextLine();
        System.out.println("Enter your Rating Out of 10:");
        this.bookRating = this.input.nextDouble();

        System.out.println("Thank you, Successfully added your book.");
        System.out.println("---------------------------------");
    }
    // End addBook() method

    // Method to view a book
    // Call this with the prefix book after searching to display the searched books characteristics in a formatted view.
    public void viewBook(){
        float x = this.bookProgress;
        float y = this.bookPageCount;
        System.out.println(this.bookName + " By: "+ this.bookAuthor);
        System.out.println(this.bookGenre);
        System.out.println("Your Progress is: " + ((x / y) * 100 ) + "% " + "or " + this.bookProgress + " out of " + this.bookPageCount);
        System.out.println("You rated this book: " + this.bookRating);
        System.out.println("---------------------------------");
    }
    // Method to remove a book
    public void removeBook(){
        System.out.println("Please Enter the title of the Book you wish to Delete.");
        String term = Main.keyboard.nextLine();
        Book book = Main.searchTitle(term);
        System.out.println("Are you sure you want to remove : " + book.bookName + " from your virtual Bookcase?");
        System.out.println("1 : yes");
        System.out.println("2 : no");
        int input = Main.keyboard.nextInt();

        if(input == 1) {
            Main.books.remove(book);
            System.out.println("Successfully Removed");
            System.out.println("---------------------------------");
        }
    } // End of removeBook method

    // updateBook method
    public void updateBook(){

        // Display the book
        viewBook();

        this.input = new Scanner(System.in);
        System.out.println("Please update the Book Name:");
        this.bookName = input.nextLine();

        System.out.println("Now update the Author Name:");
        this.bookAuthor = input.nextLine();

        System.out.println("Now update Page Count:");
        this.bookPageCount = input.nextInt();
        input.nextLine();

        System.out.println("Update your Page Progress:");
        this.bookProgress = this.input.nextInt();
        input.nextLine();

        System.out.println("Enter updated Genre (Fiction/Non-Fiction):");
        this.bookGenre = input.nextLine();


        System.out.println("Enter your updated Rating Out of 10:");
        this.bookRating = this.input.nextDouble();

    }


}