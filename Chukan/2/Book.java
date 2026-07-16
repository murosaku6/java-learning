public class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean borrowed;
    private String borrower;

    // コンストラクタ
    public Book(String title, String author){
        this.title = title;
        this.author = author;
        this.borrowed = false;
        this.borrower = "";
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public boolean isBorrowed(){
        return borrowed;
    }

    public void setBorrowed(boolean borrowed){
        this.borrowed = borrowed;
    }

    public String getBorrower(){
        return borrower;
    }

    public void setBorrower(String borrower){
        this.borrower = borrower;
    }

    public String getBookId(){
        return bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", borrowed=" + borrowed +
                '}';
    }
}