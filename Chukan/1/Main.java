import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {

            System.out.println("\n=== タスク管理システム ===");
            System.out.println("1. タスク追加");
            System.out.println("2. タスク一覧表示");
            System.out.println("3. タスク更新");
            System.out.println("4. タスク削除");
            System.out.println("5. タスク検索");
            System.out.println("6. 優先順位表示");
            System.out.println("7. 完了状態切替");
            System.out.println("8. 終了");

            int choice;

            try {
                System.out.print("選択：");
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch(InputMismatchException e) {
                System.out.println("数字を入力してください。");
                scanner.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    //追加
                    System.out.print("タイトル：");
                    String title = scanner.nextLine();

                    System.out.print("説明：");
                    String description = scanner.nextLine();

                    System.out.print("優先順位：");
                    int priority = scanner.nextInt();
                    scanner.nextLine();

                    Task task = new Task(title, description, priority, false);
                    manager.addTask(task);
                    break;

                case 2:
                    //一覧
                    manager.showTasks();
                    break;

                case 3:
                    //更新
                    manager.showTasks();

                    System.out.print("更新番号：");
                    int updateIndex = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("タイトル：");
                    title = scanner.nextLine();
                    System.out.print("説明：");
                    description = scanner.nextLine();
                    System.out.print("優先順位：");
                    priority = scanner.nextInt();
                    scanner.nextLine();

                    Task newTask = new Task(title, description, priority, false);
                    manager.updateTask(updateIndex, newTask);
                    break;

                case 4:
                    //削除
                    manager.showTasks();
                    System.out.print("削除番号：");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine();
                    manager.deleteTask(deleteIndex);
                    break;

                case 5:
                    System.out.print("検索キーワード：");
                    String keyword = scanner.nextLine();
                    manager.searchTask(keyword);
                    break;

                case 6:
                    manager.sortTasks();
                    break;

                case 7:
                    manager.showTasks();
                    System.out.print("変更する番号：");
                    int completeIndex = scanner.nextInt();
                    scanner.nextLine();
                    manager.changeCompleted(completeIndex);
                    break;

                case 8:
                    System.out.println("終了します。");
                    scanner.close();
                    return;

                default:
                    System.out.println("正しい番号を入力してください。");
            }
        }
    }
}