import javax.persistence.*;

@Entity
@Table(name = "universities")
public class University
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int university_id;
   
   @Column(name = "university_name")
   private String university_name;
   
   @Column(name = "location")
   private String location;
   
   @Column(name = "email_domain")
   private String email_domain;
   
   @Column(name = "num_students")
   private int num_students;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "super_admin_id")
   private User super_admin;
   
   public University()
   {
   }
   
   /* NOTE: university_id is generated automatically by the database and set as primary key,
            so it should not be included in constructor since it will be set by the database
            when a new row in inserted */
   public University(String university_name, String location, String email_domain, int num_students)
   {
      this.university_name = university_name;
      this.location = location;
      this.email_domain = email_domain;
      this.num_students = num_students;
    }
    
    // Getters and Setters
    
    // Primary Key Getter 
    public int getUniversityId()
    {
         return university_id;
    }
    
    // If we want to set the university_id of a university
    public void setUniversityId(int university_id)
    {
      this.university_id = university_id;
    }
    
    public String getUniversityName()
    {
      return university_name;
    }
    
    public void setUniversityName(String university_name)
    {
      this.university_name = university_name;
    }
    
    public String getLocation()
    {
      return location;
    }
    
    public void setLocation(String location)
    {
      this.location = location;
    }
    
    public String getEmailDomain()
    {
      return email_domain;
    }
    
    public void setEmailDomain(String email_domain)
    {
      this.email_domain = email_domain;
    }
    
    public int getNumStudents()
    {
      return numStudents;
    }
    
    public void setNumStudents(int numStudents)
    {
      this.numStudents = numStudents;
    }
    
    // Foreign Key Getter
    public User getSuperAdmin()
    {
      return super_admin;
    }
    
    public void setSuperAdmin(User super_admin)
    {
      this.super_admin = super_admin;
    }
    
    public int getSuperAdminId()
    {
      return getSuperAdmin().getUserId();
    }
}