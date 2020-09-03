package com.project.university.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "exam_date", nullable = false)
    private LocalDate examDate;
    @Column(name = "semester", nullable = false)
    private int semester;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Subject subject;

    @Column(name = "grade", nullable = false)
    private int grade;

    @Override
    public String toString() {
        return String.format("ID %d: exam date - %tD, semester - %d, student - %s %s, subject - %s, grade - %d",
                id, examDate, semester, student.getFirstName(), student.getLastName(), subject.getSubjectName(), grade);
    }
}
