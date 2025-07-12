package com.schoolmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Student ID is required")
    @Column(name = "student_id", unique = true)
    private String studentId;
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "first_name")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name = "last_name")
    private String lastName;
    
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @NotBlank(message = "Address is required")
    @Column(length = 500)
    private String address;
    
    private String city;
    private String state;
    
    @Column(name = "postal_code")
    private String postalCode;
    
    @NotNull(message = "Enrollment date is required")
    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;
    
    @Enumerated(EnumType.STRING)
    private StudentStatus status;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "blood_group")
    private String bloodGroup;
    
    @Column(name = "medical_conditions")
    private String medicalConditions;
    
    @NotBlank(message = "Parent/Guardian name is required")
    @Column(name = "parent_guardian_name")
    private String parentGuardianName;
    
    @NotBlank(message = "Parent/Guardian phone is required")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number should be valid")
    @Column(name = "parent_guardian_phone")
    private String parentGuardianPhone;
    
    @Email(message = "Email should be valid")
    @Column(name = "parent_guardian_email")
    private String parentGuardianEmail;
    
    @Column(name = "emergency_contact_name")
    private String emergencyContactName;
    
    @Column(name = "emergency_contact_phone")
    private String emergencyContactPhone;
    
    @Column(name = "profile_image_url")
    private String profileImageUrl;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Grade> grades;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendance> attendances;
    
    public enum StudentStatus {
        ACTIVE, INACTIVE, GRADUATED, TRANSFERRED, SUSPENDED, EXPELLED
    }
    
    public enum Gender {
        MALE, FEMALE, OTHER
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = StudentStatus.ACTIVE;
        }
        if (enrollmentDate == null) {
            enrollmentDate = LocalDate.now();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public Student() {}
    
    public Student(String studentId, String firstName, String lastName, LocalDate dateOfBirth,
                  String address, String parentGuardianName, String parentGuardianPhone, Gender gender) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.parentGuardianName = parentGuardianName;
        this.parentGuardianPhone = parentGuardianPhone;
        this.gender = gender;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    
    public StudentStatus getStatus() { return status; }
    public void setStatus(StudentStatus status) { this.status = status; }
    
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    
    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    
    public String getMedicalConditions() { return medicalConditions; }
    public void setMedicalConditions(String medicalConditions) { this.medicalConditions = medicalConditions; }
    
    public String getParentGuardianName() { return parentGuardianName; }
    public void setParentGuardianName(String parentGuardianName) { this.parentGuardianName = parentGuardianName; }
    
    public String getParentGuardianPhone() { return parentGuardianPhone; }
    public void setParentGuardianPhone(String parentGuardianPhone) { this.parentGuardianPhone = parentGuardianPhone; }
    
    public String getParentGuardianEmail() { return parentGuardianEmail; }
    public void setParentGuardianEmail(String parentGuardianEmail) { this.parentGuardianEmail = parentGuardianEmail; }
    
    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String emergencyContactName) { this.emergencyContactName = emergencyContactName; }
    
    public String getEmergencyContactPhone() { return emergencyContactPhone; }
    public void setEmergencyContactPhone(String emergencyContactPhone) { this.emergencyContactPhone = emergencyContactPhone; }
    
    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }
    
    public SchoolClass getSchoolClass() { return schoolClass; }
    public void setSchoolClass(SchoolClass schoolClass) { this.schoolClass = schoolClass; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public List<Grade> getGrades() { return grades; }
    public void setGrades(List<Grade> grades) { this.grades = grades; }
    
    public List<Attendance> getAttendances() { return attendances; }
    public void setAttendances(List<Attendance> attendances) { this.attendances = attendances; }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }
}
