package edu.sabanciuniv.test;

import edu.sabanciuniv.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class TestStudent {
    public static void main(String[] args) {

        //Creating Student Objects
        Student stu1 = new Student("Efe", "Cavusoglu", 3.76);
        Student stu2 = new Student( "Selim", "Cavusoglu", 3.56);
        Student stu3 = new Student( "Hale", "Cavusoglu", 3.63);
        Student stu4 = new Student( "Yagmur", "Cavusoglu", 3.79);

        //Storing objects for ease of access in an arraylist.
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(stu1);
        studentsList.add(stu2);
        studentsList.add(stu3);
        studentsList.add(stu4);

        //JPA EntityManagerFactory & entityManager Object Creation
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
        EntityManager entityManager = emf.createEntityManager();

        //Looping for-each in studentsList pass each student to emf.

        for (Student s : studentsList){
            entityManager.getTransaction().begin();

            entityManager.persist(s);

            entityManager.getTransaction().commit();
        }
    }
}
