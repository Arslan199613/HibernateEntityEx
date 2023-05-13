import javax.persistence.*;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        //Создаём экземпляр EntityManagerFactory, указывая persistence unit
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("myPersistenceUnit");

        // Создаём экземпляр EntityManager из EntityManagerFactory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Начинаем транзакцию
        entityManager.getTransaction().begin();


        //Создаём JPQL- запрос для выборки студентов с возрастом больше 18
        String jpqlQuery = "SELECT s FROM Student s WHERE s.age > :minAge";

        //Создаём объект запроса с указанием типа возвращаемого результата
        TypedQuery<Student> query = entityManager.createQuery(jpqlQuery, Student.class);

        //подставляем значение
        query.setParameter("minAge", 18);

        //Выполняем запрос и получаем результат в виде списка студентов
        List<Student> studentList = query.getResultList();

        //Завершаем транзакцию
        entityManager.getTransaction().commit();

        //Выводим информацию о студентах в консоль
        for (Student student : studentList) {
            System.out.println("Student ID: " + student.getId());
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student Age: " + student.getAge());
            System.out.println("------------");
        }
        //закрываем EntityManager и EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();

        //В коде используется JPA для выполнения запроса к базе данных


    }
}
