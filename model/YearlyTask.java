import java.time.LocalDateTime;

public class YearlyTask extends Task{
    public YearlyTask(String title, String description, LocalDateTime time, TaskType type) throws IncorrectArgumentException {
        super(title, description, time, type);
    }

    @Override
    public LocalDateTime getTaskNextTime(LocalDateTime dateTime) {
        return dateTime.plusYears(1);
    }
}
