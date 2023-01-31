import java.time.LocalDateTime;

public class DailyTask extends Task {
    public DailyTask(String title, String description, LocalDateTime time, TaskType type) throws IncorrectArgumentException {
        super(title, description, time, type);
    }

    @Override
    public LocalDateTime getTaskNextTime(LocalDateTime dateTime) {
        return dateTime.plusDays(1);
    }
}
