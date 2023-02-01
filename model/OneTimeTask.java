import java.time.LocalDateTime;

public class OneTimeTask extends Task {

     public OneTimeTask(String title, String description, LocalDateTime time, TaskType type) throws IncorrectArgumentException {
        super(title, description, time, type);
    }

    @Override
    public LocalDateTime getTaskNextTime(LocalDateTime dateTime) {
        return null;
    }
}
