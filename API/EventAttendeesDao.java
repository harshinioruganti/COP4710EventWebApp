import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventAttendeesDao
{
   private Connection connection;
   
   public EventAttendeesDao(Connection connection)
   {
      this.connection = connection;
   }
   
   // Add an EventAttendee to the database
   public void addAttendee(User user, Event event) throws SQLException
   {
      String sql = "INSERT INTO event_attendees(user_id, event_id) VALUES (?, ?)";
      
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setInt(1, user.getUserId());
         statement.setInt(2, event.getEventId());
         
         statement.executeUpdate();
      }
   }
   
   // Delete an EventAttendee from the database
   public void deleteAttendee(User user, Event event) throws SQLException
   {
      String sql = "DELETE FROM event_attendees WHERE user_id = ? AND event_id = ?";
      
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setInt(1, user.getUserId());
         statement.setInt(2, event.getEventId());
         
         statement.executeUpdate();
      }
   }
   
   // Get a list of all Attended Events
   public List<Event> getAttendedEvents(User user) throws SQLException
   {
      String sql = "SELECT e.* FROM events e JOIN event_attendees a ON e.event_id = a.event_id WHERE a.user_id = ?";
      List<Event> events = new ArrayList<>();
      
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setInt(1, user.getUserId());
         
         try (ResultSet resultSet = statement.executeQuery())
         {
            while (resultSet.next())
            {
               Event event = createEventFromResultSet(resultSet);
               events.add(event);
            }
         }
      }
      
      return events;
   }
   
   
   private Event createEventFromResultSet(ResultSet rs) throws SQLException
   {
      int id = rs.getInt("event_id");
      String name = rs.getString("event_name");
      String category = rs.getString("category");
      String description = rs.getString("description");
      Date time = rs.getDate("event_time");
      Date date = rs.getDate("event_date");
      String location = rs.getString("location_name");
      double latitude = rs.getDouble("latitude");
      double longitude = rs.getDouble("longitude");
      String phone = rs.getString("contact_phone");
      String email = rs.getString("contact_email");
      String eventType = rs.getString("event_type");
      String approvalStatus = rs.getString("approval_status");
      
      Rso rso = extractRsoFromResultSet(rs);
      
      Event newEvent = new Event(id, name, category, description, time, date, location, latitude, longitude, phone, email, eventType, approvalStatus);
      newEvent.setRso(rso);

      return newEvent;
   }
   
   private Rso extractRsoFromResultSet(ResultSet rs) throws SQLException
   {    
        if (rs.wasNull())
        {
            return null;
        }
        
        int rsoId = rs.getInt("rso_id");
        String rsoName = rs.getString("rso_name");
        University university = extractUniversityFromResultSet(rs);
        User admin = extractUserFromResultSet(rs, "admin_id");
        
        Rso newRso = new Rso(rsoName, university, admin);
        newRso.setRsoId(rsoId);
        
        return newRso;
    }
    
    private University extractUniversityFromResultSet(ResultSet rs) throws SQLException
    {       
        if (rs.wasNull())
        {
            return null;
        }
        
        int universityId = rs.getInt("university_id");
        String universityName = rs.getString("university_name");
        String universityLocation = rs.getString("location");
        String universityEmailDomain = rs.getString("email_domain");
        String universityNumStudents = rs.getString("num_students");
        
        University newUniversity = new University(universityName, universityLocation, universityEmailDomain, universityNumStudents);
        newUniversity.setUniveristyId(universityId);                                        
        
        return newUniversity;
     }
     
     private User extractUserFromResultSet(ResultSet rs, String columnLabel) throws SQLException
     {      
        if (rs.wasNull())
        {
            return null;
        }
        
        int userId = rs.getInt(columnLabel);
        String username = rs.getString("username");
        String password = rs.getString("password");
        String email = rs.getString("email");
        String userType = rs.getString("user_type");
        String universityId = rs.getInt("university_id");
        
        User newUser = new User(username, password, email, user_type);
        newUser.setUserId(userId);
        newUser.getUniverisity().setUniversityId(universityId);
        
        return newUser;
     }
}