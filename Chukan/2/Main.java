import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LibraryManager manager = new LibraryManager();

        while (true) {
            System.out.println();
            System.out.println("=== 図書館管理システム ===");
            System.out.println("1. 書籍を登録");
            System.out.println("2. 書籍一覧表示メニュー");
            System.out.println("3. 書籍検索メニュー");
            System.out.println("4. 書籍削除");
            System.out.println("5. 書籍の貸出・返却");
            System.out.println("6. ジャンル管理");
            System.out.println("7. 終了");
            System.out.print("番号を入力してください：");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("数字を入力してください。");
                continue;
            }

            switch (choice) {
                // 本登録
                case 1:
                    System.out.println();
                    System.out.println("===== 書籍登録 =====");

                    System.out.print("タイトル：");
                    String title = scanner.nextLine();

                    System.out.print("著者：");
                    String author = scanner.nextLine();

                    Book book = new Book(title, author);

                    manager.addBook(book);

                    break;

                // 一覧表示
                case 2:
                    showBooksMenu(scanner, manager);
                    break;

                // 検索
                case 3:
                    showSearchMenu(scanner, manager);
                    break;

                // 削除
                case 4:
                    System.out.print("削除する本ID：");
                    int daleteBookId = scanner.nextInt();
                    manager.deleteBook(daleteBookId);
                    break;

                // 貸出・返却
                case 5:
                    showBorrowMenu(scanner, manager);
                    break;

                // ジャンル管理
                case 6:
                    showGenreMenu(scanner, manager);
                    break;

                // 終了
                case 7:
                    System.out.println("システムを終了します。");
                    scanner.close();
                    return;

                default:
                    System.out.println("1～7を入力してください。");
            }
        }
    }

    // 書籍一覧メニュー
    private static void showBooksMenu(
            Scanner scanner,
            LibraryManager manager) {

        while (true) {

            System.out.println();
            System.out.println("===== 書籍一覧 =====");
            System.out.println("1. 登録順");
            System.out.println("2. タイトル順（昇順）");
            System.out.println("3. タイトル順（降順）");
            System.out.println("4. 貸出状況順");
            System.out.println("0. 戻る");
            System.out.print("選択：");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("数字を入力してください。");
                continue;
            }

            switch (choice) {

                case 1:
                    manager.showBooks(LibraryManager.ORDER_REGISTER);
                    break;

                case 2:
                    manager.showBooks(LibraryManager.ORDER_TITLE_ASC);
                    break;

                case 3:
                    manager.showBooks(LibraryManager.ORDER_TITLE_DESC);
                    break;

                case 4:
                    manager.showBooks(LibraryManager.ORDER_BORROW);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("0～4を入力してください。");
            }
        }
    }

    // 検索メニュー
    private static void showSearchMenu(
            Scanner scanner,
            LibraryManager manager) {

        while (true) {

            System.out.println();
            System.out.println("===== 書籍一覧 =====");
            System.out.println("1. タイトル検索");
            System.out.println("2. 著者検索");
            System.out.println("3. ジャンル検索");
            System.out.println("4. Id検索");
            System.out.println("0. 戻る");
            System.out.print("選択：");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("数字を入力してください。");
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("タイトル：");
                    manager.searchByTitle(scanner.nextLine());
                    break;

                case 2:
                    System.out.print("著者：");
                    manager.searchByAuthor(scanner.nextLine());
                    break;

                case 3:
                    System.out.print("ジャンル：");
                    manager.searchByGenre(scanner.nextLine());
                    break;

                case 4:
                    System.out.print("Id：");
                    manager.searchById(choice);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("0～4を入力してください。");
            }
        }
    }

    private static void showBorrowMenu(Scanner scanner, LibraryManager manager){

        while(true){

            System.out.println();
            System.out.println("===== 書籍・返却 =====");
            System.out.println("1. 貸出");
            System.out.println("2. 返却");
            System.out.println("3. 貸出・返却履歴");
            System.out.println("0. 戻る");
            System.out.print("選択：");

            int choice;

            try{
                choice = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("数字を入力してください。");
                continue;
            }

            switch(choice){

                case 1:{

                    manager.showBooks(LibraryManager.ORDER_REGISTER);

                    System.out.print("Id：");
                    int id = scanner.nextInt();

                    System.out.print("利用者名：");
                    String borrower = scanner.nextLine();

                    manager.borrowBook(id, borrower);

                    break;
                }

                case 2:{

                    manager.showBooks(LibraryManager.ORDER_REGISTER);

                    System.out.print("Id：");
                    int id = scanner.nextInt();

                    manager.returnBook(id);

                    break;
                }
                
                case 3:{
                    System.out.println();
                    System.out.println("===== 貸出・返却履歴 =====");

                    System.out.print("Id：");
                    int id = scanner.nextInt();

                    manager.showBorrowHistory(id);

                    break;
                }

                case 0:
                    return;

                default:
                    System.out.println("0～3を入力してください。");
            }
        }
    }

    // ジャンル管理
    private static void showGenreMenu(
            Scanner scanner,
            LibraryManager manager){

        while(true){

            System.out.println();
            System.out.println("===== 書籍・返却 =====");
            System.out.println("1. ジャンル追加");
            System.out.println("2. ジャンル削除");
            System.out.println("3. ジャンル一覧表示");
            System.out.println("0. 戻る");
            System.out.print("選択：");

            int choice;

            try{
                choice = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("数字を入力してください。");
                continue;
            }

            switch(choice){

                case 1:

                    System.out.print("Id：");
                    int id = scanner.nextInt();

                    System.out.print("追加するジャンル：");
                    String genre = scanner.nextLine();

                    manager.addGenre(id, genre);

                    break;

                case 2:

                    System.out.print("Id：");
                    id = scanner.nextInt();

                    System.out.print("削除するジャンル：");
                    genre = scanner.nextLine();

                    manager.removeGenre(id, genre);

                    break;

                case 3:

                    System.out.print("Id：");
                    id = scanner.nextInt();

                    manager.showGenres(id);

                    break;

                case 0:
                    return;

                default:
                    System.out.println("0～3を入力してください。");
            }
        }
    }
}