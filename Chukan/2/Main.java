import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LibraryManager manager = new LibraryManager();

        while (true) {
            System.out.println();
            System.out.println("=== 図書館管理システム ===");
            System.out.println("1. 本を登録");
            System.out.println("2. 本一覧表示");
            System.out.println("3. 本検索");
            System.out.println("4. 本削除");
            System.out.println("5. 本を借りる");
            System.out.println("6. 本を返却する");
            System.out.println("7. タイトル順表示");
            System.out.println("8. 終了");
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
                    System.out.print("タイトルを入力してください：");
                    String title = scanner.nextLine();
                    System.out.print("著者を入力してください：");
                    String author = scanner.nextLine();

                    Book book = new Book(title, author);
                    manager.addBook(book);
                    break;

                // 一覧表示
                case 2:
                    manager.showBooks();
                    break;

                // 検索
                case 3:
                    System.out.print("検索キーワード：");
                    String keyword = scanner.nextLine();
                    manager.searchBook(keyword);
                    break;

                // 削除
                case 4:
                    System.out.print("削除する本ID：");
                    String daleteBookId = scanner.nextLine();
                    manager.deleteBook(daleteBookId);
                    break;

                // 貸出
                case 5:
                    manager.showBooks();

                    System.out.print("借りる本の番号を入力してください：");
                    int index;
                    try {
                        index = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("数字を入力してください。");
                        continue;
                    }
                    scanner.nextLine();

                    System.out.print("借りる人の名前を入力してください：");
                    String borrower = scanner.nextLine();
                    manager.borrowBook(index, borrower);
                    break;

                // 返却
                case 6:
                    manager.showBooks();
                    System.out.print("返却する本の番号を入力してください：");
                    int returnIndex;

                    try {
                        returnIndex = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("数字を入力してください。");
                        break;
                    }
                    manager.returnBook(returnIndex);
                    break;

                // 終了
                case 8:
                    System.out.println("システムを終了します。");
                    scanner.close();
                    return;

                default:
                    System.out.println("1～5を入力してください。");
            }
        }
    }
}