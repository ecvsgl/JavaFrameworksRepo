package edu.sabanciuniv.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Student {

    // class fields - variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;
    private String studentName;
    private String studentSurname;
    private double studentGPA;

    // Constructors full & empty
    public Student() {
    }

    public Student(String studentName, String studentSurname, double studentGPA) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentGPA = studentGPA;
    }

    public static void saveStudents(List<Student> studentsList, EntityManager entityManager) {
        for (Student s : studentsList){
            entityManager.getTransaction().begin();
            entityManager.persist(s);
            entityManager.getTransaction().commit();
        }
    }

    public static void findAllStudents(EntityManager entityManager) {
        TypedQuery studentJpql = entityManager.createQuery("FROM Student s", Student.class);
        List<Student> stuList = studentJpql.getResultList();

        for (Student s : stuList){
            System.out.println(s);
        }
    }

    public static void findByStudentName(EntityManager entityManager, String studentName) {
        Student result = entityManager.createQuery("FROM Student s WHERE s.studentName = ?1", Student.class)
                .setParameter(1, studentName).getSingleResult();
        System.out.println(result);
    }

    public static void findByStudentNameAndGPA(EntityManager entityManager, String studentName, double studentGPA) {
        Student result = entityManager.createQuery("FROM Student s WHERE s.studentName = ?1 AND " +
                        "s.studentGPA = ?2", Student.class)
                .setParameter(1, studentName)
                .setParameter(2, studentGPA)
                .getSingleResult();
        System.out.println(result);
    }

    public static void updateStudentGPA(EntityManager entityManager, Student stu, double newGPA) {
        entityManager.getTransaction().begin();

        Student result = entityManager.createQuery("FROM Student s WHERE s.studentName = ?1", Student.class)
                .setParameter(1, stu.getStudentName())
                .getSingleResult();
        result.setStudentGPA(newGPA);

        entityManager.merge(result);
        entityManager.getTransaction().commit();

        System.out.println("GPA of " + stu.getStudentName() + " " + stu.getStudentSurname() + " has been updated to : "
                            + newGPA);
    }

    public static void removeStudent(EntityManager entityManager, Student stu) {
        entityManager.getTransaction().begin();

        Student result = entityManager.createQuery("FROM Student s WHERE s.studentName = ?1", Student.class)
                .setParameter(1, stu.getStudentName())
                .getSingleResult();

        entityManager.remove(result);
        entityManager.getTransaction().commit();
        System.out.println("Student record deleted.");
    }


    //Getters&Setters for Fields
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public double getStudentGPA() {
        return studentGPA;
    }

    public void setStudentGPA(double studentGPA) {
        this.studentGPA = studentGPA;
    }

    // equals & hashCode & toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentSurname='" + studentSurname + '\'' +
                ", studentGPA=" + studentGPA +
                '}';
    }
}
