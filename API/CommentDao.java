import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao
{
   private Connection connection;
   
   public CommentsDao(Connection connection)
   {
      this.connection = connection;
   }
   
   // Add a comment to the database
   public void addComment(Comment comment) throws SQLException
   {
      String sql = "INSERT INTO comments (user_id, event_id, comment_text, rating) VALUES (?, ?, ?, ?)";\
      
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setInt(1, comment.getUser().getUserId());
         statement.setInt(2, comment.getEvent().getEventId());
         statement.setString(3, comment.getCommentText());
         statement.setInt(4, comment.getRating());
         
         statement.executeUpdate();
      }
   }
   
   // Update a comment in the database) 
   public void updateComment(Comment comment) throws SQLException
   {
      String sql = "UPDATE comments SET user_id = ?, event_id = ?, comment_text = ?, rating = ? WHERE comment_id = ?";
      
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setInt(1, comment.getUser().getUserId());
         statement.setInt(2, comment.getEvent().getEventId());
         statement.setString(3, comment.getCommentText());
         statement.setInt(4, comment.getRating());
         statement.setInt(5, comment.getCommentId());
         
         stmt.executeUpdate();
      }
   }
   
   // Delete a comment in the database
   public void deleteComment(int commentId) throws SQLException
   {
      String sql = "DELETE FROM comments WHERE comment_id = ?";
      
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setInt(1, commentId);
         statement.executeUpdate();
      }
   }
   
   // Get a comment by commentId
   public Comment getCommentById(int commentId) throws SQLException
   {
      String sql = "SELECT comment_id, user_id, event_id, comment_text, rating FROM comments WHERE comment_id = ?";
      
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setInt(1, commentId);
         
         try (ResultSet resultSet = statement.executeQuery())
         {
            if (resultSet.next())
            {
               Comment comment = createCommentFromResultSet(resultSet);               return comment;
            }
            else
            {
               return null;
            }
         }
      }
   }
   
   // Get a list of all comments by user
   public List<Comment> getCommentsByUser(User user) throws SQLException
   {
      String sql = "SELECT comment_id, user_id, event_id, comment_text, rating FROM comments WHERE user_id = ?";
      List<Comment> comments = new ArrayList<>();
      
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setInt(1, user.getUserId());
         
         try (ResultSet resultSet = statement.executeQuery())
         {
            while (resultSet.next())
            {
               comments.add(createCommentFromResultSet(resultSet));  
            }
         }
      }
      
      return comments;
   }
   
   // Get a list of all comments by Event
   public List<Comment> getCommentsByEvent(Event event) throws SQLException
   {
      String sql = "SELECT comment_id, user_id, event_id, comment_text, rating FROM comments WHERE event_id = ?";
      List<Comment> comments = new ArrayList<>();
      
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setInt(1, event.getEventId());
         
         try (ResultSet resultSet = statement.executeQuery())
         {
            while (resultSet.next())
            {
               comments.add(createCommentFromResultSet(resultSet));        
            }
         }
      }
      
      return comments;
   }
   
   private Comment createCommentFromResultSet(ResultSet rs) throws SQLException
   {
       int commentId = rs.getInt("comment_id");
       int userId = rs.getInt("user_id");
       int eventId = rs.getInt("event_id");
       String commentText = rs.getString("comment_text");
       int rating = rs.getInt("rating");
   
       User user = userDao.getUserById(userId);
       Event event = eventDao.getEventById(eventId);
   
       return new Comment(commentId, user, event, commentText, rating);
   }
}