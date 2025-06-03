package com.example.crudwithvaadin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
        @SequenceGenerator(name = "my_sequence", sequenceName = "my_seq", initialValue = 5976359, allocationSize = 1)
	private Long id;

	private String firstName;

	private String lastName;

	protected Project() {
	}

	public Project(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

        public void setId(Long id) {
            this.id = id;
        }

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

	@Override
	public String toString() {
		return String.format("Project[id=%d, firstName='%s', lastName='%s']", id,
				firstName, lastName);
	}

}
