package entities;

import entities.User;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;

/**
 * Entity implementation class for Entity: Student
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Student extends User implements Serializable {

	
	private String firstName;
	private String lastName;
	@ManyToOne
	private Filiere filiere;
	private static final long serialVersionUID = 1L;
	


	public Student() {
		super();
	}   
	
	public Student(String firstname,String lastName) {
		this.firstName = firstname;
		this.lastName = lastName;
	}  


	public Student(String password, String login,String firstName, String lastName,Filiere filiere) {
		super(password, login);
		this.firstName = firstName;
		this.lastName = lastName;
		this.filiere = filiere;
	}

/*
	public Student(String firstName, String lastName, Filiere filiere) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.filiere = filiere;
	}*/


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

/*
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
*/

	public Filiere getFiliere() {
		return filiere;
	}


	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	
	
   
}
