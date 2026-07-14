import java.util.ArrayList;

public class LibraryManager {

    private ArrayList<Book> books;
    
    public LibraryManager(){
        books = new ArrayList<>();
    }

    public void addBook(Book book){
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
}
