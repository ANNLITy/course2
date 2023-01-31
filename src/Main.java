import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final TaskService taskService = new TaskService();
    private static final Pattern DATA_TIME_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}\\:\\d{2}");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static final Pattern DATA_PATTERNN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy" );
    public static void main(String[] args)  {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.println("Выберите пункт меню:  ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            showAllByDate(scanner);
                            break;
                        case 0:
                            break;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт из списка");
                }
            }
        }catch (IncorrectArgumentException e){
            e.printStackTrace();
        }
    }

    private static void inputTask(Scanner scanner) throws IncorrectArgumentException {
        scanner.useDelimiter("\n");
        System.out.println("Введите название задачи: ");
        String title = scanner.next();

        System.out.println("Введите описание задачи: ");
        String description = scanner.next();

        System.out.println("Введите тип задачи(1-личная, 2-рабочая): ");
        TaskType type = null;
        int taskTypeChoise = scanner.nextInt();
        if (taskTypeChoise == 1) {
            type = TaskType.PERSONAL;
        } else if (taskTypeChoise == 2) {
            type = TaskType.WORK;
        } else {
            System.out.println("Неправильный ввод данных");
            scanner.close();
        }
        switch (taskTypeChoise){
            case 1:
                type = TaskType.PERSONAL;
                break;
            case 2:
                type=TaskType.WORK;
                break;
            default:
                System.out.println("Тип задачи введён некорректно");
        }
        System.out.println("Введите дату и время(число.месяц.год время) :");
        LocalDateTime taskTime = null;
        if (scanner.hasNext(DATA_TIME_PATTERN)) {
            String dataTime = scanner.next(DATA_TIME_PATTERN);
            taskTime = LocalDateTime.parse(dataTime, DATE_TIME_FORMATTER);
        }
        if (taskTime == null) {
            System.out.println("Введите дату и время(Год.месяц.число Время) ");
            scanner.close();
        }
        System.out.println("Укажите повторяемость(1-однократно,2-каждый день,3-каждую неделю, 4-каждый месяц,5-каждый год):");
        Task task = null;
        if (scanner.hasNextInt()){
            int repeatability = scanner.nextInt();
            switch (repeatability){
                case 1:
                    task = new OneTimeTask(title,description,taskTime,type);
                    break;
                case 2:
                    task = new DailyTask(title,description,taskTime,type);
                    break;
                case 3:
                    task = new MonthyTask(title,description,taskTime,type);
                    break;
                case 4:
                    task = new WeeklyTask(title,description,taskTime,type);
                    break;
                case 5:
                    task = new YearlyTask(title,description,taskTime,type);
                    break;
                default:
                    System.out.println("Укажите правильную повторяемость");
                    scanner.close();
            }
        }
        taskService.add(task);
        System.out.println("Задача добавлена");
    }
    private static void removeTask (Scanner scanner){
        System.out.println("Введите идентификатор задачи");
        int id = scanner.nextInt();
        try {
            taskService.remove(id);
        }catch (TaskNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    private static void showAllByDate(Scanner scanner){
        System.out.println("Введите дату(Год.месяц.число) :");
        if (scanner.hasNext(DATA_PATTERNN)){
            String dateTime = scanner.next(DATA_PATTERNN);
            LocalDate inputeDate = LocalDate.parse(dateTime, DATE_FORMATTER);
            Collection<Task> tasksByDay = taskService.getAllByDate(inputeDate);
            for(Task task: tasksByDay) {
                System.out.println(task);
            }
        }else {
            System.out.println("Введите дату(Год.месяц.число) :");
            scanner.close();
        }
    }


        private static void printMenu() {
            System.out.println("1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n4. Получить список удалённых задач\n0. Выход");
        }
    }
