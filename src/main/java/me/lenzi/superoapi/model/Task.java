package me.lenzi.superoapi.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Boolean isCompleted;

    @CreatedDate
    private Date createdOn;

    @LastModifiedDate
    private Date lastEditedOn;

    private Date completedOn;

    public Task(){
        super();
    }

    public Task(Long id, String title, String description, Boolean isCompleted) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Date getCreatedOn() { return createdOn; }

    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; }

    public Date getLastEditedOn() { return lastEditedOn; }

    public void setLastEditedOn(Date lastEditedOn) { this.lastEditedOn = lastEditedOn; }

    public Date getCompletedOn() { return completedOn; }

    public void setCompletedOn(Date completedOn) { this.completedOn = completedOn; }
}