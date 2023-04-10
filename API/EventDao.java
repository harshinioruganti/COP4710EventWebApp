import java.sql.*;
import java.util.Date;
import java.util.Time;
import java.util.ArrayList;
import java.util.List;

public class EventDao
{
   private DatabaseConnection connection;
   
   public EventDao(DatabaseConnection connection)
   {
      this.connection = connection;
   }
   
   // Add an event to the database
   public void addEvent(Event event) throws SQLException
   {
      Connection conn = connection.getConnection();
      String sql = "INSERT INTO events(event_name, category, description, event_time, event_date, location_name, " +
                   "latitude, longitude, contact_phone, contact_email, event_type, approval_status, rso_id) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                   
      PreparedStatement statement = conn.prepareStatement(sql);
      statement.setString(1, event.getEventName());
      statement.setString(2, event.getCategory());
      statement.setString(3, event.getDescription());
      statement.setTime(4, Time.valueOf(event.getEventTime()));
      statement.setDate(5, Date.valueOf(event.getEventDate()));
      statement.setString(6, event.getLocationName());
      statement.setDouble(7, event.getLatitude());
      statement.setDouble(8, event.getLongitude());
      statement.setString(9, event.getContactPhone());
      statement.setString(10, event.getContactEmail());
      statement.setString(11, event.getEventType());
      statement.setString(12, String.valueOf(event.getApprovalStatus()));
      
      if (event.getRso() != null)
      {
         statement.setInt(13, event.getRso.getRsoId());
      }
      else
      {
         statement.setNull(13, Types.INTEGER);
      }
      
      statement.executeUpdate();
      statement.close();
   }
   
   // Retrieve an event by eventId
   public Event getEventById(int eventId) throws SQLException
   {
      Connection connection = DatabaseConnection.getConnection();
      String sql = "SELECT * FROM events WHERE event_id = ?";
      
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, eventId);
      
      ResultSet resultSet = statement.executeQuery();
      
      if (resultSet.next())
      {
         Event event = new Event(resultSet.getInt("event_id"),
                                 resultSet.getString("event_name"),
                                 resultSet.getString("category"),
                                 resultSet.getString("description"),
                                 resultSet.getDate("event_time"),
                                 resultSet.getDate("event_date"),
                                 resultSet.getString("location_name"),
                                 resultSet.getDouble("latitude"),
                                 resultSet.getDouble("longitude"),
                                 resultSet.getString("contact_phone"),
                                 resultSet.getString("contact_email"),
                                 resultSet.getString("event_type"),
                                 resultSet.getString("approval_status").charAt(0),
                                 resultSet.getInt("rso_id"));
         return event;
      }
      else
      {
         return null;
      }
   }
   
   // Retrieve a list of all events
   public List<Event> getAllEvents() throws SQLException()
   {
      Connection connection = DatabaseConnection.getConnection();
      String sql = "SELECT * FROM events LEFT JOIN rsos ON events.rso_id = rsos.rso_id";
      
      List<Event> events = new ArrayList<>();
      try (PreparedStatement statement = connection.prepareStatement(sql);
           ResultSet resultSet = statement.executeQuery())
      {
         Event event = new Event(resultSet.getInt("event_id"),
                                 resultSet.getString("event_name"),
                                 resultSet.getString("category"),
                                 resultSet.getString("description"),
                                 resultSet.getDate("event_time),
                                 resultSet.getDate("event_date"),
                                 resultSet.getString("location_name"),
                                 resultSet.getDouble("latitude"),
                                 resultSet.getDouble("longitude"),
                                 resultSet.getString("contact_phone"),
                                 resultSet.getString("contact_email"),
                                 resultSet.getString("event_type"),
                                 resultSet.getString("approval_status").charAt(0),
                                 resultSet.getInt("rso_id"));
         
          Rso rso = new Rso(resultSet.getInt("rso_id),
                                 resultSet.getString("rso_name"),
                                 resultSet.getInt("university_id"),
                                 resultSet.getInt("admin_id"));
         if (rso != null)
         {
            event.setRso(rso);
         }
         
         events.add(event)
      }
      
      return events;
   } 
   
   // Retrieve a list of event by categories
   public List<Event> getEventByCategory(int eventCategory) throws SQLException
   {
      Connection conn = DatabaseConnection.getConnection();
      String sql = "SELECT event_id, event_name, category, description, event_time, event_date, location_name, " +
                    "latitude, longitude, contact_phone, contact_email, event_type, approval_status, rso_id " +
                    "FROM events WHERE category = ?";
      PreparedStatement statement = conn.prepareStatement(sql);
      statement.setString(1, category);
      
      ResultSet resultSet = statement.executeQuery();
      List<Event> events = new ArrayList<>();
      
      while (resultSet.next())
      {
         Event event = new Event(resultSet.getInt("event_id"),
                                 resultSet.getString("event_name"),
                                 resultSet.getString("category"),
                                 resultSet.getString("description"),
                                 resultSet.getDate("event_time),
                                 resultSet.getDate("event_date"),
                                 resultSet.getString("location_name"),
                                 resultSet.getDouble("latitude"),
                                 resultSet.getDouble("longitude"),
                                 resultSet.getString("contact_phone"),
                                 resultSet.getString("contact_email"),
                                 resultSet.getString("event_type"),
                                 resultSet.getString("approval_status").charAt(0),
                                 resultSet.getInt("rso_id"));
         events.add(event);
      }
      
      resultSet.close();
      statement.close();
      return events;
   }
   
   // Retrieve a list of events by RSOs
   public List<Event> getEventByRso(Rso rso) throws SQLException
   {
      Connection connection = DatabaseConnection.getConnection();
      String sql = "SELECT event_id, event_name, category, description, event_time, event_date, location_name, " +
                   "latitude, longitude, contact_phone, contact_email, event_type, approval_status, rso_id " +
                   "FROM events WHERE rso_id = ?";
                   
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, rso.getRsoId());
      
      ResultSet resultSet = statement.executeQuery();
      
      while (resultSet.next())
      {
         Event event = new Event(resultSet.getInt("event_id"),
                                 resultSet.getString("event_name"),
                                 resultSet.getString("category"),
                                 resultSet.getString("description"),
                                 resultSet.getDate("event_time),
                                 resultSet.getDate("event_date"),
                                 resultSet.getString("location_name"),
                                 resultSet.getDouble("latitude"),
                                 resultSet.getDouble("longitude"),
                                 resultSet.getString("contact_phone"),
                                 resultSet.getString("contact_email"),
                                 resultSet.getString("event_type"),
                                 resultSet.getString("approval_status").charAt(0),
                                 resultSet.getInt("rso_id"));
         events.add(event);
     }
     
     resultSet.close();
     statement.close();
     return events;
   }
   
   // Update an event in the database
   public void updateEvent(Event event) throws SQLException
   {
      Connection connection = DatabaseConnection.getConnection();
      String sql = "UPDATE events SET event_name = ?, category = ?, description = ?, event_time = ?, "
                + "event_date = ?, location_name = ?, latitude = ?, longitude = ?, contact_phone = ?, "
                + "contact_email = ?, event_type = ?, approval_status = ?, rso_id = ? WHERE event_id = ?";
                
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setString(1, event.getEventName());
         statement.setString(2, event.getCategory());
         statement.setString(3, event.getDescription());
         statement.setTime(4, Time.valueOf(event.getEventTime()));
         statement.setDate(5, Date.valueOf(event.getEventDate()));
         statement.setString(6, event.getLocationName());
         statement.setDouble(7, event.getLatitude());
         statement.setDouble(8, event.getLongitude());
         statement.setString(9, event.getContactPhone());
         statement.setString(10, event.getContactEmail());
         statement.setString(11, event.getEventType());
         statement.setString(12, String.valueOf(event.getApprovalStatus()));
         statement.setInt(13, event.getRsoId());
         statement.setInt(14, event.getEventId());
         
         statement.executeUpdate();
      }
   }
   
   // Delete the eventId event from the database
   public void deleteEvent(int eventId) throws SQLException
   {
      Connection connection = DatabaseConnection.getConnection();
      String sql = "DELETE FROM events WHERE event_id = ?";
      
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setInt(1, eventId);
         statement.executeUpdate();
      }
   }
}