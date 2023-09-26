package com.onkar.curddemo;

import com.onkar.curddemo.dao.StudentDAO;
import com.onkar.curddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CurddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			System.out.println("Radhe Radhe!! ");
			createStudent(studentDAO);

			//readStudent(studentDAO);

			//queryForStudents(studentDAO);

			//queryForStudentsByLastName(studentDAO);

			//updateStudent(studentDAO);

			//deleteStudent(studentDAO);

			//deleteAll(studentDAO);
		};
	}

	private void deleteAll(StudentDAO studentDAO) {

		int numRowsDeleted = studentDAO.deleteAll();

		System.out.println("Num of Rows Deleted  : "+ numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {

		int studentID = 1;
		System.out.println("Deleting the Student : ");
		System.out.println(studentDAO.findById(studentID));
		studentDAO.delete(studentID);
	}

	private void updateStudent(StudentDAO studentDAO) {

		int studentId = 2;

		Student tempStd = studentDAO.findById(studentId);

		tempStd.setLastName("Rama");

		studentDAO.update(tempStd);

		Student updatedStd = studentDAO.findById(studentId);

		System.out.println(updatedStd);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {

		List<Student> theStudents = studentDAO.findByLastName("Bartakke");

		for(Student tmpStd : theStudents){
			System.out.println(tmpStd);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		//Get list of Students
		List<Student> theStudents = studentDAO.findAll();
		//display list of students

		for(Student tempStd : theStudents){
			System.out.println(tempStd);
		}
	}

	private void readStudent(StudentDAO studentDAO){

		//Creating a new Student
		System.out.println("Creating the new Student ...");

		Student tempStudent = new Student("Hare" , "Krushna", "radharani@barsana.com");

		//Savind the new Student
		System.out.println("Saving the New Student...");
		studentDAO.save(tempStudent);

		//Display the ID of new Student
		System.out.println("ID of New Student is : "+tempStudent.getId());

		//Reteriving the Student
		Student std = studentDAO.findById(tempStudent.getId());
		System.out.println(std.toString());
	}

	private void createStudent(StudentDAO studentDAO){

		//Creating the student object
		System.out.println("Creating the new student object...");

		Student tempStudent = new Student("Onkar","Bartakke","onkarbartakke27136@gmail.com");

		//Save the Student Object
		System.out.println("Saving the Student Object...");
		studentDAO.save(tempStudent);

		//Display the ID of Saved Student
		System.out.println("Saved Student. Generated ID : " +  tempStudent.getId());
	}

}
