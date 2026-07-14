public class Book {

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


    @Override
    public String toString(){

        if(borrowed){

            return "タイトル：" + title +
                   " / 著者：" + author +
                   " / 貸出者：" + borrower;

        }else{

            return "タイトル：" + title +
                   " / 著者：" + author +
                   " / 貸出可能";

        }
    }
}