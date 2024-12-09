public class Main {


    public static void main(String[] args) {

        LibrarySystem library = new LibrarySystem();
        Book book = new Book("The Hobbit", "J.R.R. Tolkien", "1234567890", "?", "Fantasy", 2001);
        library.addBook(book);

        String loanResult = library.loanBook(book);
        System.out.println(loanResult);

        // Försöker låna den igen
        loanResult = library.loanBook(book);
        System.out.println(loanResult);


        String returnResult = library.returnBook(book);
        System.out.println(returnResult);

        returnResult = library.returnBook(book);
        System.out.println(returnResult);




    }
}