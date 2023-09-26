package de.telran.lesson3.schedule_layer;

import de.telran.lesson3.domain_layer.entity.Product;
import de.telran.lesson3.domain_layer.entity.jpa.JpaProduct;
import de.telran.lesson3.domain_layer.entity.jpa.Task;
import de.telran.lesson3.repository_layer.jpa.JpaProductRepository;
import de.telran.lesson3.repository_layer.jpa.JpaTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@EnableScheduling
@EnableAsync
public class ScheduleExecutor {

    @Autowired
    private JpaTaskRepository repository;
    @Autowired
    private JpaProductRepository productRepository;
    private static Logger logger = LoggerFactory.getLogger(ScheduleExecutor.class);
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask (){
//        Task task = new Task("Fixed delay task");
//        logger.info(task.getDescription());
//        repository.save(task);
//    }
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask (){
//        Task task = new Task("Fixed delay task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Scheduled(fixedRate = 5000)
//    @Async
//    public void fixedRateTask (){
//        Task task = new Task("Fixed rate task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Scheduled(fixedRate = 5000, initialDelay = 15000)
//    @Async
//    public void initialDelayTask (){
//        Task task = new Task("Initial delay task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Scheduled(fixedDelayString = "PT7S")
//    public void anotherDelayFormatTask (){
//        Task task = new Task("Another delay format task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Scheduled(fixedDelayString = "${interval}")
//    public void delayInPropertyTask (){
//        Task task = new Task("Delay in property task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Scheduled(cron = "${cron-interval}")
//    public void cronExpressionTask (){
//        Task task = new Task("Cron expression task");
//        logger.info(task.getDescription());
//        repository.save(task);
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static void taskSchedulerTaskWithTrigger(Task task){
        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
        scheduler.schedule(()-> logger.info(task.getDescription()),
                new CronTrigger("0,10,20,30,40,50 * * * * *"));
    }
    public static void taskSchedulerTaskWithInstant(Task task){
        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
        Instant instant = Instant.now().plusSeconds(20);
        scheduler.schedule(()-> logger.info(task.getDescription()),
                instant);
    }

//1.Реализовать вывод в консоль каждые 30 секунд списка последних пяти выполненных задач.
//Время выполнения предыдущей задачи не должно влиять на старт следующей.
//Создавать новую задачу и логировать ничего не нужно.

//    @Scheduled(fixedDelay = 30000)
//    public void displayLastFiveTasks (){
//        List<Task> allTaskList = repository.findAll();
//        List<Task> fiveLastTasks = allTaskList.subList(allTaskList.size() - 5, allTaskList.size());
//        int count = 0;
//        for (Task task : fiveLastTasks){
//            System.out.println(++count + ". " + task.getDescription());
//        }
//
//    }

//2.Реализовать вывод в консоль последнего добавленного в БД товара.
//Вывод должен производиться в 15 и 45 секунд каждой минуты. Задача должна быть сохранена в БД.
//Вывод в консоль должен быть осуществлён через логирование поля description созданной задачи.
//Пример значения поля description - "Последний добавленный в БД продукт - Банан".

//    @Scheduled(cron = "15,45 * * * * *")
//    public void displayLastAddProduct (){
//        String lastAddedProduct = productRepository.getLastAddedProduct();
//        String description = String.format("Последний добавленный в БД продукт - %s", lastAddedProduct);
//        Task lastProductTask = new Task(description);
//        logger.info(lastProductTask.getDescription());
//        repository.save(lastProductTask);
//    }

//3.После запроса конкретного продукта через 15 секунд отправить персональное предложение
//на этот продукт с ценой на 5-10% (рандомно) ниже, чем базовая цена.
//Имитировать отправку в виде вывода в консоль и логирования.
//Данная задача должна выполняться при помощи АОП и сохраняться в БД.
//Вывод в консоль должен быть осуществлён через логирование поля description созданной задачи.
//Поле description задачи должно содержать предложение купить товар по новой цене
//с указанием наименования товара, старой цены и новой цены.
//Указание скидки для данного предложения не должно влиять на базовую цену товара в БД.

    // Class OfferAspect

//4.После того как покупатель очистил корзину, через 20 секунд выбрать из корзины случайный товар,
//который там был, сделать на него скидку 15% и предложить покупателю всё-таки
//приобрести все эти товары, вывести все товары (один с новой ценой), а также старую и новую стоимость корзины.
//Данная задача должна выполняться при помощи АОП и сохраняться в БД.
//Вывод в консоль должен быть осуществлён через логирование поля description созданной задачи.
//Поле description задачи должно содержать предложение приобрести все товары из корзины,
//список товаров из корзины (каждый товар с новой строки), причём товар со скидкой должен быть указан с новой ценой,
//а также с новой строки - старую стоимость корзины и новую стоимость корзины.
//Указание скидки для данного предложения не должно влиять на базовую цену товара в БД.

    // этот метод не получается, нужны подсказки

}
