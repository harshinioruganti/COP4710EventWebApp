import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment
{
   @Id
   @GeneratedValue(Strategy = GenerationType.IDENTITY)
   @Column(name = "comment_id")
   private int comment_id;
   
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;
   
   @ManyToOne
   @JoinColumn(name = "event_id")
   private Event event;
    
   @Column(name = "comment_text", nullable = false)
   private String comment_text;
    
   @Column(name = "rating")
   private int rating;
   
   // Constructor
   public Comments()
   {
   }
   
   public Comments(User user, Event event, String comment_text, int rating)
   {
      this.user = user;
      this.event = event;
      this.comment_text = comment_text;
      this.rating = rating;
   }
   
   // Setters and Getters
   public int getCommentId()
   {
      return comment_id;
   }
   
   public int getUser()
   {
      return user;
   }
   
   public int getEvent()
   {
      return event;
   }
   
   public String getCommentText()
   {
      return comment_text;
   }
   
   public void setCommentText(String comment_text)
   {
      this.comment_text = comment_text;
   }
   
   public int getRating()
   {
      return rating;
   }
   
   public void setRating(int rating)
   {
      this.rating = rating;
   }
}