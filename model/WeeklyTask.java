import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    public WeeklyTask(String title, String description, LocalDateTime time, TaskType type) throws IncorrectArgumentException {
        super(title, description, time, type);
    }

    @Override
    public LocalDateTime getTaskNextTime(LocalDateTime dateTime) {
        return dateTime.plusWeeks(1);
    }
}
