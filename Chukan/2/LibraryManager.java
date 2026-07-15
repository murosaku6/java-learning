import java.util.ArrayList;

public class LibraryManager {

    private ArrayList<Book> books;
    private int nextBookNumber;
    
    public LibraryManager(){
        books = new ArrayList<>();
        nextBookNumber = 1;
    }

    // 本登録
    public void addBook(Book book){
        String bookId = String.format("A%04d", nextBookNumber);
        book.setBookId(bookId);
        nextBookNumber++;
        books.add(book);
        System.out.println("本を登録しました。");
    }

    // 本一覧
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

    // 貸出
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

    // 検索
    public void searchBook(String keyword){
        if(books.isEmpty()){
            System.out.println("登録されている本はありません。");
            return;
        }
        boolean found = false;

        System.out.println("=== 検索結果 ===");
        for(Book book : books){
            if(book.getBookId().contains(keyword) || book.getTitle().contains(keyword)){
                System.out.println(book);
                found = true;
            }
        }
        if(!found){
            System.out.println("該当する本はありません。");
        }
    }

    // 削除
    public void deleteBook(String bookId){
        if(books.isEmpty()){
            System.out.println("登録されている本はありません。");
            return;
        }
        for(int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if(book.getBookId()).equals(bookId){
                books.remove(i);
                System.out.println("削除しました。");
                return;
            }
        }
        System.out.println("該当する本がありません。");
    }
}
