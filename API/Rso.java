import javax.persistence.*;

@Entity
@Table(name = "rsos")
public class Rso
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int rso_id;
   
   @Column(name = "rso_name")
   private String rso_name;
   
   @ManyToOne
   @JoinColumn(name = "university_id")
   private University university;
   
   @ManyToOne
   @JoinColumn(name = "admin_id")
   private User admin;

   @ManyToMany
   @JoinTable(
        name="user_rso",
        joinColumns = @JoinColumn(name = "rso_id"),
        inverseJoinColumns = @JoinColumn(name="user_id"))
    
    private Set<User> users = new HashSet<>();
   
   public Rso()
   {
   }
   
   public Rso (String rso_name, University university, User admin)
   {
        this.rso_name = rso_name;
        this.university = university;
        this.admin = admin;
    }
    
    // Setters and Getters
    public int getRsoId()
    {
      return rso_id;
    }
    
    public void setRsoId(int rso_id)
    {
      this.rso_id = rso_id;
    }
    
    public String getRsoName()
    {
        return rso_name;
    }
    
    public void setRsoName(String rso_name)
    {
        this.rso_name = rso_name;
    }
    
    public University getUniversity()
    {
        return university;
    }
    
    public User getAdmin()
    {
        return admin;
    }

    public Set getUsers()
    {
        return users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }
}