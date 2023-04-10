import javax.persistence.*;

@Entity
@Table(name = "event_attendees")
public class EventAttendees
{
   @Id
   @ManyToOne
   @JoinColumn(name = "user_id", referencedColumnName = "user_id")
   private User user;
   
   @Id
   @ManyToOne
   @JoinColumn(name = "event_id", referencedColumnName = "event_id")
   private Event event;
 
   public EventAttendees(User user, Event event)
   {
      this.user = user;
      this.event = event;
   }

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   public Event getEvent()
   {
      return event;
   }

   public void setEvent(Event event)
   {
      this.event = event;
   }
}