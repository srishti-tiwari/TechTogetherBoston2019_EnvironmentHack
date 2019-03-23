package techtogether.io.myapplication;

public class ToDoTask {
    public ToDoTask() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    String taskName;

    public ToDoTask(String taskName) {
        this.taskName = taskName;
    }
}
