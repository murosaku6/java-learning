import java.util.ArrayList;

public class Book {
    private int id;
    private String title;
    private String author;
    // 最大3つまで登録
    private ArrayList<String> genres;
    private boolean borrowed;
    private String borrower;
    // 貸出日
    private String borrowDate;
    // 返却予定日
    private String returnDate;
    // 貸出履歴
    private ArrayList<String> borrowHistory;


    // コンストラクタ
    public Book(String title, String author) {

        this.title = title;
        this.author = author;

        this.borrowed = false;
        this.borrower = "";

        this.genres = new ArrayList<>();
        this.borrowHistory = new ArrayList<>();

        this.borrowDate = "";
        this.returnDate = "";
    }

    // ジャンル追加
    public void addGenre(String genre) {
        if (genres.size() >= 3) {
            System.out.println("ジャンルは3つまで登録できます。");
            return;
        }

        if (genres.contains(genre)) {
            System.out.println("既に登録されています。");
            return;
        }
        genres.add(genre);
    }

    // ジャンル削除
    public void removeGenre(String genre) {
        if(genres.remove(genre)){
            System.out.println("ジャンルを削除しました。");
        }else{
            System.out.println("登録されていません。");
        }
    }

    // 貸出履歴
    public void addBorrowHistory(String history){
        borrowHistory.add(history);
    }

    // ゲッター・セッター
    // id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    // タイトル
    public String getTitle() {
        return title;
    }
    // 著者
    public String getAuthor() {
        return author;
    }
    // ジャンル一覧
    public ArrayList<String> getGenres() {
        return genres;
    }
    // 貸出状態
    public boolean isBorrowed() {
        return borrowed;
    }
    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
    // 借りた人
    public String getBorrower() {
        return borrower;
    }
    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }
    // 貸出日
    public String getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }
    // 返却予定日
    public String getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    // 貸出履歴
    public ArrayList<String> getBorrowHistory() {
        return borrowHistory;
    }


    @Override
    public String toString() {
        return id + " " + title + " " + author;
    }
}