import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task implements Repeatable {
    private int id;
    private String title;
    private String description;
    private LocalDateTime time;
    private TaskType type;
    private static int gen =1;

    public Task(String title, String description, LocalDateTime time, TaskType type) throws IncorrectArgumentException  {
        this.id = gen++;
        setTitle(title);
        setDescription(description);
        setType(type);
        setTime(time);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IncorrectArgumentException {
        if (title !=null && !title.isEmpty()) {
            this.title = title;
        }else {
            throw new IncorrectArgumentException("название ");
        }

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IncorrectArgumentException {
        if (description !=null && !description.isEmpty()) {
            this.description = description;
        }else {
            throw new IncorrectArgumentException("описание ");
        }

    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) throws IncorrectArgumentException {
        if (time !=null ) {
            this.time = time;
        }else {
            throw new IncorrectArgumentException("время ");
        }
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) throws IncorrectArgumentException {
        if (type !=null ) {
            this.type = type;
        }else {
            throw new IncorrectArgumentException("тип ");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(time, task.time) && type == task.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, time, type);
    }

    @Override
    public String toString() {
        return "Задача{" +
                "id=" + id +
                ", Название='" + title + '\'' +
                ", Описание='" + description + '\'' +
                ", Время=" + time +
                ", Тип=" + type +
                '}';
    }
}
