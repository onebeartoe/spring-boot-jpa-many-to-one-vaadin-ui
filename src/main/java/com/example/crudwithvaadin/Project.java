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

	private String title;

	private String notes;

	protected Project() {
	}

	public Project(String firstName, String lastName) {
		this.title = firstName;
		this.notes = lastName;
	}

	public Long getId() {
		return id;
	}

        public void setId(Long id) {
            this.id = id;
        }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }





	@Override
	public String toString() {
		return String.format("Project[id=%d, firstName='%s', lastName='%s']", id,
				title, notes);
	}

}
