package com.onkar.curddemo.dao;

import com.onkar.curddemo.entity.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    //define field for entity manager
    private EntityManager entityManager;
    //Inject entity manager using constructor injection
    @Autowired
    public  StudentDAOImpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        //create Query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student" , Student.class);
        //return Query results

        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData" , Student.class);

        theQuery.setParameter("theData",theLastName);

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer studentID) {
        Student theStudent = entityManager.find(Student.class, studentID);
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
       int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

       return numRowsDeleted;
    }

}
