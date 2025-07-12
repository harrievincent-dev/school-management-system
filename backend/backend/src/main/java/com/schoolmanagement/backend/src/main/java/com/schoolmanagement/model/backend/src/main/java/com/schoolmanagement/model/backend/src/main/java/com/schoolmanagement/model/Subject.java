package com.schoolmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Subject code is required")
    @Size(min = 2, max = 20, message = "Subject code must be between 2 and 20 characters")
    @Column(name = "subject_code", unique = true)
    private String subjectCode;
    
    @NotBlank(message = "Subject name is required")
    @Size(min = 2, max = 100, message = "Subject name must be between 2 and 100 characters")
    @Column(name = "subject_name")
    private String subjectName;
    
    @Column(length = 1000)
    private String description;
    
    @NotNull(message = "Credits are required")
    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 10, message = "Credits cannot exceed 10")
    private Integer credits;
    
    @NotBlank(message = "Department is required")
    private String department;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "subject_type")
    private SubjectType subjectType;
    
    @Enumerated(EnumType.STRING)
    private SubjectStatus status;
    
    @Column(name = "prerequisites")
    private String prerequisites;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TeacherSubject> teacherSubjects;
    
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Grade> grades;
    
    public enum SubjectType {
        CORE, ELECTIVE, MANDATORY, OPTIONAL
    }
    
    public enum SubjectStatus {
        ACTIVE, INACTIVE, ARCHIVED
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = SubjectStatus.ACTIVE;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public Subject() {}
    
    public Subject(String subjectCode, String subjectName, String description, Integer credits, String department) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.description = description;
        this.credits = credits;
        this.department = department;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSubjectCode() { return subjectCode; }
    public void setSubjectCode(String subjectCode) { this.subjectCode = subjectCode; }
    
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public SubjectType getSubjectType() { return subjectType; }
    public void setSubjectType(SubjectType subjectType) { this.subjectType = subjectType; }
    
    public SubjectStatus getStatus() { return status; }
    public void setStatus(SubjectStatus status) { this.status = status; }
    
    public String getPrerequisites() { return prerequisites; }
    public void setPrerequisites(String prerequisites) { this.prerequisites = prerequisites; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public List<TeacherSubject> getTeacherSubjects() { return teacherSubjects; }
    public void setTeacherSubjects(List<TeacherSubject> teacherSubjects) { this.teacherSubjects = teacherSubjects; }
    
    public List<Grade> getGrades() { return grades; }
    public void setGrades(List<Grade> grades) { this.grades = grades; }
}
