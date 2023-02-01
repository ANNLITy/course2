import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<Integer,Task> taskMap = new HashMap<>();
    public void add(Task task){
        this.taskMap.put(task.getId(), task);
    }
    public void remove(Integer taskId) throws TaskNotFoundException {
         if(this.taskMap.containsKey(taskId)){
             this.taskMap.remove(taskId);
         }else {
             throw new TaskNotFoundException(taskId);
         }
    }
    public Collection<Task> getAllByDate(LocalDate date ){
        Collection<Task> tasksByDay = new ArrayList<>();
        for(Task task: taskMap.values()){
            LocalDateTime taskTime = task.getTime();
            LocalDateTime taskNextTime = task.getTaskNextTime(taskTime);
            if (taskNextTime== null|| taskTime.toLocalDate().equals(date)){
                tasksByDay.add(task);
                continue;
            }
            do{
                if (taskNextTime.toLocalDate().equals(date)){
                    tasksByDay.add(task);
                    break;
                }
                taskNextTime= task.getTaskNextTime(taskNextTime);
            }while (taskNextTime.toLocalDate().isBefore(date));
        }
        return tasksByDay;
    }

        }
