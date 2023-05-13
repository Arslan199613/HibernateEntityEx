import javax.persistence.*;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("myPersistenceUnit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String jpqlQuery = "SELECT s FROM Student s WHERE s.age > :minAge";
        TypedQuery<Student> query = entityManager.createQuery(jpqlQuery, Student.class);
        query.setParameter("minAge", 18);
        List<Student> studentList = query.getResultList();

        entityManager.getTransaction().commit();

        for (Student student : studentList) {
            System.out.println("Student ID: " + student.getId());
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student Age: " + student.getAge());
            System.out.println("------------");
        }

        entityManager.close();
        entityManagerFactory.close();


    }
}
