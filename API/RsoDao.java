import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RsoDao
{
   private DatabaseConnection connection;
   
   public RsoDao(DatabaseConnection connection)
   {
      this.connection = connection;
   }
   
   // Add a RSO to the database
   public Rso addRso(Rso rso) throws SQLException
   {
      String sql = "INSERT INTO rsos (rso_name, university_id, admin_id) VALUES (?, ?, ?)";
      
      try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
      {
         statement.setString(1, rso.getName());
         statement.setInt(2, rso.getUniversity.getUniversityId());
         statement.setInt(3, rso.getAdmin().getAdminId());
         
         statement.executeUpdate();
         
         Result generatedKeys = statement.getGeneratedKeys();
         
         if (generatedKeys.next())
         {
            int rsoId = generatedKeys.getInt(1);
            rso.setRsoId(rsoId);
            return rso;
         }
      }
      else
      {
         throw new SQLException("Creating RSO failed, no ID obtained.");
      }
   }
   
   // Get RSO by Id
   public Rso getRsoById(int rsoId) throws SQLException
   {
      String sql = "SELECT rsos.rso_id, rsos.rso_name, rsos.university_id, universities.university_name, universities.location, universities.email_domain, rsos.admin_id, users.username, users.password, users.email, users.user_type FROM rsos JOIN universities ON rsos.university_id = universities.university_id JOIN users ON rsos.admin_id = users.user_id WHERE rsos.rso_id = ?";
      
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setInt(1, rsoId);
         try (ResultSet resultSet = statement.executeQuery())
         {
            if (resultSet.next())
            {
               return extractRsoFromResultSet(resultSet);
            }
            else
            {
               return null;
            }
         }
      }
   }
   
   // Get a list of all RSOs
   public List<Rso> getAllRsos() throws SQLException
   {
      List<Rso> rsos = new ArrayList<>();
      String sql = "SELECT rsos.rso_id, rsos.rso_name, rsos.university_id, universities.university_name, universities.location, universities.email_domain, rsos.admin_id, users.username, users.password, users.email, users.user_type FROM rsos JOIN universities ON rsos.university_id = universities.university_id JOIN users ON rsos.admin_id = users.user_id";
     
     try (PreparedStatement statement = connection.prepareStatement(sql))
     {
         try (ResultSet resultSet = statement.executeQuery())
         {
            while (resultSet.next())
            {
               Rso rso = new Rso(resultSet.getInt("rso_id),
                                 resultSet.getString("rso_name"),
                                 resultSet.getInt("university_id"),
                                 resultSet.getInt("admin_id"));
               rsos.add(rso);
            }
         }
     }
     
     return rsos;
   }
   
   // Get a list of RSOs by university
   public List<Rso> getRsosByUniversity(University university) throws SQLException
   {
      List<Rso> rsos = new ArrayList<>();
      String sql = "SELECT rsos.rso_id, rsos.rso_name, rsos.university_id, universities.university_name, universities.location, universities.email_domain, rsos.admin_id, users.username, users.password, users.email, users.user_type FROM rsos JOIN universities ON rsos.university_id = universities.university_id JOIN users ON rsos.admin_id = users.user_id WHERE rsos.university_id = ?";     
     try (PreparedStatement statement = connection.prepareStatement(sql))
     {
         statement.setInt(1, university.getUniversityId());
         try (ResultSet resultSet = statement.executeQuery())
         {
            while (resultSet.next())
            {
               Rso rso = new Rso(resultSet.getInt("rso_id),
                                 resultSet.getString("rso_name"),
                                 resultSet.getInt("university_id"),
                                 resultSet.getInt("admin_id"));
               rsos.add(rso);
            }
         }
     }
     
     return rsos;
   }
   
   // Update Rso in the database
   public void updateRso(Rso rso) throws SQLException
   {
      String sql = "UPDATE rsos SET rso_name = ?, university_id = ?, admin_id = ? WHERE rso_id = ?";
      try (PreparedStatement stmt = connection.prepareStatement(sql))
      {
        stmt.setString(1, rso.getRsoName());
        stmt.setInt(2, rso.getUniversity().getUniversityId());
        stmt.setInt(3, rso.getAdmin().getUserId());
        stmt.setInt(4, rso.getRsoId());
        
        stmt.executeUpdate();
      }
   }

   // Delete a Rso from the database
   public void deleteRso(int rsoId) throws SQLException
   {
      String sql = "DELETE FROM rsos WHERE rso_id = ?";
      try (PreparedStatement stmt = connection.prepareStatement(sql))
      {
         stmt.setInt(1, rsoId);
         stmt.executeUpdate();
      }
   }
}