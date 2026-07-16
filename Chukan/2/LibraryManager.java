import java.util.ArrayList;
import java.util.Comparator;

public class LibraryManager {
    private ArrayList<Book> books;
    
    public LibraryManager(){
        books = new ArrayList<>();
    }

    // 次の書籍番号
    private int nextId = 1;

    // 書籍登録
    public void addBook(Book book){
        book.setId(nextId);
        nextId++;
        books.add(book);
        System.out.println("書籍を登録しました。");
        System.out.println("管理番号：" + book.getId());
    }

    // printBook
    private void printBook(Book book) {
            String status = book.isBorrowed()
                ? "貸出中(" + book.getBorrower() + ")"
                : "貸出可能";

        System.out.printf(
                "%-5s %-20s %-15s %-20s %-15s%n",
                book.getId(), book.getTitle(), book.getAuthor(),
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
        System.out.printf("%-5s %-20s %-15s %-20s %-15s%n",
                         "Id", "タイトル", "著者", "ジャンル", "状態"
                        );
        System.out.println("-----------------------------------------------------------------");

        for(Book book : displayBooks){
            printBook(book);
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("登録冊数：" + displayBooks.size() + "冊");
    }

    // 貸出
    public void borrowBook(int id, String borrower){
        Book book = findBookById(id);

        if(book == null){
            System.out.println("該当するIdがありません。");
            return;
        }

        if(book.isBorrowed()){
            System.out.println("この本は貸出中です。");
            return;
        }
        book.setBorrowed(true);
        book.setBorrower(borrower);
        System.out.println("貸し出しました。");
    }

    // 返却
    public void returnBook(int id){
        Book book = findBookById(id);

        if(book == null){
            System.out.println("該当するIdがありません。");
            return;
        }

        if(!book.isBorrowed()){
            System.out.println("この本は貸出されていません。");
            return;
        }
        book.setBorrowed(false);
        book.setBorrower("");
        System.out.println("返却しました。");
    }

    // 貸出履歴
    public void showBorrowHistory(int id){
        Book book = findBookById(id);
        if(book == null){
            System.out.println("該当するIdがありません。");
            return;
        }
        System.out.println();
        System.out.println("===== 貸出・返却履歴 =====");
        System.out.println("Id：" + book.getId());
        System.out.println("タイトル：" + book.getTitle());
        if(book.getBorrowHistory().isEmpty()){
            System.out.println("履歴はありません。");
            return;
        }
        for(String history : book.getBorrowHistory()){
            System.out.println(history);
        }
    }

    // タイトル検索
    public void searchByTitle(String keyword){

        if (books.isEmpty()) {
            System.out.println("登録されている本はありません。");
            return;
        }

        boolean found = false;

        System.out.println();
        System.out.println("========== 検索結果 ==========");

        for(Book book : books){
            if(book.getTitle().toLowerCase().contains(keyword.toLowerCase())){

                printBook(book);
                found = true;
            }
        }

        if(!found){
            System.out.println("該当する本はありません。");
        }
    }

    // 著者検索
    public void searchByAuthor(String keyword){
        if (books.isEmpty()) {
            System.out.println("登録されている本はありません。");
            return;
        }

        boolean found = false;

        System.out.println();
        System.out.println("========== 検索結果 ==========");

        for(Book book : books){
            if(book.getAuthor().toLowerCase().contains(keyword.toLowerCase())){
                printBook(book);
                found = true;
            }
        }

        if(!found){
            System.out.println("該当する本はありません。");
        }
    }

    // Id検索
    public void searchById(int id){
        if (books.isEmpty()) {
            System.out.println("登録されている本はありません。");
            return;
        }

        boolean found = false;

        System.out.println();
        System.out.println("========== 検索結果 ==========");

        for(Book book : books){
            if(book.getId() == id){
                printBook(book);
                found = true;
                break;
            }
        }

        if(!found){
            System.out.println("該当する本はありません。");
        }
    }

    // ジャンル検索
    public void searchByGenre(String keyword){
        if (books.isEmpty()) {
            System.out.println("登録されている本はありません。");
            return;
        }

        boolean found = false;

        System.out.println();
        System.out.println("========== 検索結果 ==========");

        for(Book book : books){
            for(String genre : book.getGenres()){
                if(genre.toLowerCase().contains(keyword.toLowerCase())){
                    printBook(book);
                    found = true;
                    break;
                }
            }
        }
        if(!found){
            System.out.println("該当する本はありません。");
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

    // 削除
    public void deleteBook(int id){
        Book book = findBookById(id);
        if(book == null){
            System.out.println("該当するIdがありません。");
            return;
        }
        books.remove(book);
        System.out.println("削除しました。");
    }

    // Idから本を検索
    private Book findBookById(int id){
        for(Book book : books){
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

    // ジャンル追加
    public void addGenre(int id, String genre){
        Book book = findBookById(id);
        if(book == null){
            System.out.println("該当するIdがありません。");
            return;
        }
        book.addGenre(genre);
        System.out.println("ジャンルを追加しました。");
    }

    // ジャンル削除
    public void removeGenre(int id, String genre){
        Book book = findBookById(id);
        if(book == null){
            System.out.println("該当するIdがありません。");
            return;
        }
        book.removeGenre(genre);
    }

    // ジャンル一覧表示
    public void showGenres(int id){
        Book book = findBookById(id);
        if(book == null){
            System.out.println("該当するIdがありません。");
            return;
        }
        System.out.println();
        System.out.println("===== ジャンル一覧 =====");

        if(book.getGenres().isEmpty()){
            System.out.println("ジャンルは登録されていません。");
            return;
        }
        for(int i = 0; i < book.getGenres().size(); i++){
            System.out.println((i + 1) + ". " + book.getGenres().get(i));
        }
    }
}
