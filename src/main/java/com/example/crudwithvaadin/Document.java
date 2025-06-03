
package com.example.crudwithvaadin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 */
@Entity
@Table(name = "documents")
public class Document 
{
    @Id
    @GeneratedValue
    private Long id;

    private String url;

    private String notes;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)  // this caused a retreive error
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Project project;

    public Document() {
    }    
    
    public Document(String url, String notes, Project customer)
    {
        this.url = url;
        
        this.notes = notes;
        
        this.project = customer;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


}


















