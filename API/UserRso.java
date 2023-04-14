import javax.persistence.*;

@Entity
@Table(name = "user_rso", uniqueConstraints = @UniqueConstraints(columnNames={"user_id", "rso_id"}))
public class UserRso 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_rso_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rso_id", nullable = false)
    private Rso rso;

    public UserRso() {}

    public UserRso(User user, Rso rso)
    {
        this.user = user;
        this.rso = rso;
    }

    // Setters and Getters
    public int getUserRsoId()
    {
      return user_rso_id;
    }
    
    public void setUserRsoId(int user_rso_id)
    {
      this.user_rso_id = user_rso_id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Rso getRso()
    {
        return rso;
    }

    public void setRso(Rso rso)
    {
        this.rso = rso;
    }
}