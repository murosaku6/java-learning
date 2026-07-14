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
            System.out.println("3. 本を借りる");
            System.out.println("4. 本を返却する");
            System.out.println("5. 終了");
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

                // 貸出
                case 3:
                    manager.showBooks();

                    System.out.print("借りる本の番号を入力してください：");
                    int index = scanner.nextInt();
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
                case 4:
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
                case 5:
                    System.out.println("システムを終了します。");
                    scanner.close();
                    return;

                default:
                    System.out.println("1～4を入力してください。");
            }
        }
    }
}