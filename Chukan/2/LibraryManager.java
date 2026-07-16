import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class LibraryManager {

    private ArrayList<Book> books;
    private Random random;
    
    public LibraryManager(){
        books = new ArrayList<>();
        random = new Random();
    }

    // ISBN生成
    private String generateIsbn() {
        String isbn;
        do {
            StringBuilder sb = new StringBuilder();
            // ISBNらしく978固定
            sb.append("978");
            // 残り10桁
            for (int i = 0; i < 10; i++) {
                sb.append(random.nextInt(10));
            }
            isbn = sb.toString();
        } while (existsIsbn(isbn));
        return isbn;
    }

    // 重複チェック
    private boolean existsIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    // 本登録
    public void addBook(Book book){
        book.setIsbn(generateIsbn());
        books.add(book);
        System.out.println("本を登録しました。");
        System.out.println("ISBN：" + book.getIsbn());
    }

    // printBook
    private void printBook(Book book) {
            String status = book.isBorrowed()
                ? "貸出中(" + book.getBorrower() + ")"
                : "貸出可能";

        System.out.printf(
                "%-15s %-20s %-15s %-20s %-15s%n",
                book.getIsbn(), book.getTitle(), book.getAuthor(),
                String.join(", ", book.getGenres()), status
        );
    }

    // 本一覧
    public static final int ORDER_REGISTER = 1;
    public static final int ORDER_TITLE_ASC = 2;
    public static final int ORDER_TITLE_DESC = 3;
    public static final int ORDER_BORROW = 4;
    public void showBooks(int orderType){
        if(books.isEmpty()){
            System.out.println("登録されている本はありません。");
            return;
        }

        ArrayList<Book> displayBooks = new ArrayList<>(books);

        switch (orderType) {
            case ORDER_REGISTER:
                break;
            
            case ORDER_TITLE_ASC:
                displayBooks.sort(
                    Comparator.comparing(Book::getTitle)
                );
                break;

            case ORDER_TITLE_DESC:
                displayBooks.sort(
                    Comparator.comparing(Book::getTitle).reversed()
                );
                break;

            case ORDER_BORROW:
                displayBooks.sort(
                    Comparator.comparing(Book::isBorrowed).reversed()
                );
                break;
            
            default:
                System.out.println("表示方法が不正です。");
                return;
        }

        System.out.println();
        System.out.println("============================= 本一覧 ============================");
        System.out.printf("%-15s %-20s %-15s %-20s %-15s%n",
                         "ISBN", "タイトル", "著者", "ジャンル", "状態"
                        );
        System.out.println("-----------------------------------------------------------------");

        for(Book book : displayBooks){
            printBook(book);
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("登録冊数：" + displayBooks.size() + "冊");
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

    // 返却
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

    // タイトル検索
    public void searchByTitle(String keyword) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().contains(keyword)) {
                printBook(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("該当する本がありません。");
        }
    }

    // 著者検索
    public void searchByAuthor(String keyword) {
        boolean found = false;
        for (Book book : books) {
            if (book.getAuthor().contains(keyword)) {
                printBook(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("該当する本がありません。");
        }
    }

    // ジャンル検索
    public void searchByGenre(String genre) {
        boolean found = false;
        for (Book book : books) {
            if (book.getGenres().contains(genre)) {
                printBook(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("該当する本がありません。");
        }
    }

    // 貸出中のみ
    public void showBorrowedBooks() {
        boolean found = false;
        for (Book book : books) {
            if (book.isBorrowed()) {
                printBook(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("貸出中の本はありません。");
        }
    }

    // 貸出可能のみ
    public void showAvailableBooks() {
        boolean found = false;
        for (Book book : books) {
            if (!book.isBorrowed()) {
                printBook(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("貸出可能な本はありません。");
        }
    }

    // ISBN検索
    public void searchByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                printBook(book);
                return;
            }
        }
        System.out.println("該当する本がありません。");
    }

    // 削除
    public void deleteBook(String bookId){
        if(books.isEmpty()){
            System.out.println("登録されている本はありません。");
            return;
        }
        for(int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if(book.getIsbn().equals(bookId)){
                books.remove(i);
                System.out.println("削除しました。");
                return;
            }
        }
        System.out.println("該当する本がありません。");
    }
}
