import java.time.LocalDateTime;

public class MonthyTask extends Task {
    public MonthyTask(String title, String description, LocalDateTime time, TaskType type) throws IncorrectArgumentException {
        super(title, description, time, type);
    }

    @Override
    public LocalDateTime getTaskNextTime(LocalDateTime dateTime) {
        return dateTime.plusMonths(1) ;
    }
}
