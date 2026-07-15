import java.util.ArrayList;

public class LibraryManager {

    private ArrayList<Book> books;
    private int nextBookNumber;
    
    public LibraryManager(){
        books = new ArrayList<>();
        nextBookNumber = 1;
    }

    public void addBook(Book book){
        String bookId = String.format("A%04d", nextBookNumber);
        book.setBookId(bookId);
        nextBookNumber++;
        books.add(book);
        System.out.println("本を登録しました。");
    }

    public void showBooks(){
        if(books.isEmpty()){
            System.out.println("登録されている本はありません。");
            return;
        }

        System.out.println("=== 本一覧 ===");

        for(int i = 0; i < books.size(); i++){
            System.out.println((i + 1) + ", " + books.get(i));
        }
    }

    public void borrowBook(int index, String borrower){
        if(index < 1 || index > books.size()){
            System.out.println("存在しない番号です。");
            return;
        }

        Book book = books.get(index - 1);

        if (book.isBorrowed()) {
            System.out.println("この本は貸出中です。");
            return;
        }

        book.setBorrowed(true);
        book.setBorrower(borrower);

        System.out.println("貸し出しました。");
    }

    public void returnBook(int index){
        if(index < 1 || index > books.size()){
            System.out.println("存在しない番号です。");
            return;
        }

        Book book = books.get(index - 1);

        if(!book.isBorrowed()){
            System.out.println("この本は貸出されていません。");
            return;
        }
        
        book.setBorrowed(false);
        book.setBorrower("");

        System.out.println("返却しました。");
    }
}
