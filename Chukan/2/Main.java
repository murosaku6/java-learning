/**
 * 起動し、メインメニューを表示するクラス
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LibraryManager manager = new LibraryManager();

        while (true) {

            int choice = LibraryMenu.showMainMenu(scanner);

            switch (choice) {
                // 書籍登録
                case 1:
                    LibraryMenu.registerBook(scanner, manager);
                    break;

                // 一覧表示
                case 2:
                    LibraryMenu.showBooksMenu(scanner, manager);
                    break;

                // 検索
                case 3:
                    LibraryMenu.showSearchMenu(scanner, manager);
                    break;

                // 削除
                case 4:
                    LibraryMenu.deleteBook(scanner, manager);
                    break;

                // 貸出・返却
                case 5:
                    LibraryMenu.showBorrowMenu(scanner, manager);
                    break;

                // ジャンル管理
                case 6:
                    LibraryMenu.showGenreMenu(scanner, manager);
                    break;

                // 終了
                case 0:
                    System.out.println("システムを終了します。");
                    scanner.close();
                    return;

                default:
                    System.out.println("0～6を入力してください。");
            }
        }
    }
}