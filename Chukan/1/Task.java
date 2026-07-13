public class Task {
    private String title;
    private String description;
    private int priority;
    private boolean completed;

    public Task(String title, String description, int priority, boolean completed){
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = completed;        
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPriority(){
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public boolean isCompleted(){
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        String status = completed ? "完了" : "未完了";

        return String.format(
            "タイトル：%s, 説明：%s, 優先順位：%d, 状態：%s",
            title,
            description,
            priority ,
            status
        );
    }
}