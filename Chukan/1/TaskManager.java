import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void showTasks(){
        if (tasks.isEmpty()) {
            System.out.println("タスクがありません。");
            return;
        }

        System.out.println("=== タスク一覧 ===");

        for (int i = 0; i < tasks.size(); i++){
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task);
        }
    }

    // 削除
    public void deleteTask(int index) {
        if (index < 1 || index > tasks.size()){
            System.out.println("存在しない番号です。");
            return;
        }
        tasks.remove(index - 1);
        System.out.println("削除しました。");
    }

    // 更新
    public void updateTask(int index, Task newTask){
        if(index < 1 || index > tasks.size()){
            System.out.println("存在しない番号です。");
            return;
        }

        tasks.set(index - 1, newTask);
        
        System.out.println("更新しました");
    }
}