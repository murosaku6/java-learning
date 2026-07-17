/**
 * 図書館業務の管理を行うクラス
 */
import java.util.ArrayList;
import java.util.Comparator;

public class LibraryManager {
    private ArrayList<Book> books;
    /**
     * LibraryManagerを生成する
     */
    public LibraryManager(){
        books = new ArrayList<>();
    }

    // 次の書籍番号
    private int nextId = 1;

    /**
     * 書籍を登録する
     * 
     * @param book　登録する書籍
     */
    public void addBook(Book book){
        book.setId(nextId);
        nextId++;
        books.add(book);
        System.out.println("書籍を登録しました。");
        System.out.println("管理番号：" + book.getId());
    }

    /**
     *  書籍情報を１行で表示する
     */
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

    // 一覧表示の並び順
    public static final int ORDER_REGISTER = 1;
    public static final int ORDER_TITLE_ASC = 2;
    public static final int ORDER_TITLE_DESC = 3;
    public static final int ORDER_BORROW = 4;
    /**
     * 書籍一覧を表示する
     * 
     * @param orderType　表示順
     */
    public void showBooks(int orderType){
        if(books.isEmpty()){
            System.out.println("登録されている本はありません。");
            return;
        }

        // 表示用のリストを作成する
        ArrayList<Book> displayBooks = new ArrayList<>(books);

        // 表示順を変更する
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
        
        // 一覧を表示する
        for(Book book : displayBooks){
            printBook(book);
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("登録冊数：" + displayBooks.size() + "冊");
    }

    /**
     * 書籍を貸し出す。
     *
     * @param id 管理番号
     * @param borrower 利用者名
     */
    public void borrowBook(int id, String borrower){
        // 管理番号から書籍を検索
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
        // 貸出状態を更新
        book.setBorrower(borrower);
        book.addBorrowHistory(borrower + " が貸出しました。");
    }

    /**
     * 書籍を返却する。
     *
     * @param id 管理番号
     */
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
        String brrower = book.getBorrower();
        book.setBorrowed(false);
        book.addBorrowHistory(brrower + " が返却しました。");
        book.setBorrower("");
    }

    /**
     * 全ての貸出・返却履歴を表示する。
     */
    public void showBorrowHistory(){
        for(Book book : books){
            System.out.println("Id：" + book.getId());
            System.out.println("タイトル：" + book.getTitle());
            if(book.getBorrowHistory().isEmpty()){
                System.out.println("履歴はありません。");
                return;
            }
            for(String history : book.getBorrowHistory()){
                System.out.println("・" + history);
            }
        }
    }

    /**
     * タイトルで書籍を検索する。
     *
     * @param keyword 検索キーワード
     */
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

    /**
     * 著者名で書籍を検索する。
     *
     * @param keyword 検索キーワード
     */
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

    /**
     * 管理番号で書籍を検索する。
     *
     * @param id 管理番号
     */
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

    /**
     * ジャンルで書籍を検索する。
     *
     * @param keyword 検索キーワード
     */
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

    /**
     * 貸出中の書籍のみ表示する。
     */
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

    /**
     * 貸出可能な書籍のみ表示する。
     */
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

    /**
     * 書籍を削除する。
     *
     * @param id 管理番号
     */
    public void deleteBook(int id){
        Book book = findBookById(id);
        if(book == null){
            System.out.println("該当するIdがありません。");
            return;
        }
        books.remove(book);
        System.out.println("削除しました。");
    }

    /**
     * 管理番号から書籍を検索する。
     *
     * @param id 管理番号
     * @return 見つかった書籍
     */
    private Book findBookById(int id){
        for(Book book : books){
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

    /**
     * 書籍にジャンルを追加する。
     *
     * @param id 管理番号
     * @param genre ジャンル名
     */
    public void addGenre(int id, String genre){
        Book book = findBookById(id);
        if(book == null){
            System.out.println("該当するIdがありません。");
            return;
        }
        book.addGenre(genre);
        System.out.println("ジャンルを追加しました。");
    }

    /**
     * 書籍からジャンルを削除する。
     *
     * @param id 管理番号
     * @param genre 削除するジャンル
     */
    public void removeGenre(int id, String genre){
        Book book = findBookById(id);
        if(book == null){
            System.out.println("該当するIdがありません。");
            return;
        }
        book.removeGenre(genre);
    }

    /**
     * 書籍に登録されているジャンルを表示する。
     *
     * @param id 管理番号
     */
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
