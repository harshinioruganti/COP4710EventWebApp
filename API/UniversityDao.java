import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UniversityDao
{
   private DatabaseConnection connection;
   
   public UniversityDao(DatabaseConnection connection)
   {
      this.connection = connection;
   }
   
   // Retrieve a university by its ID
   public University getUniversityById(int universityId) throws SQLException
   {
      String sql = "SELECT * FROM universities WHERE university_id = ?";
      
      try (PreparedStatement statement = connection.prepareStatement(sql))
      {
         statement.setInt(1, universityId);
         
         try (Result resultSet = statement.executeQuery())
         {
            if (resultSet.next())
            {
               University university = new University(universityId, resultSet.getString("university_name"),
                                                      resultSet.getString("location"),
                                                      resultSet.getString("email_domain"),
                                                      resultSet.getInt("num_students");
            }
         }
      }
      
      return university;
   }
   
   // Retrieve all universities in the database
   public List<University> getAllUniversities() throws SQLException
   {
      List<University> universities = new ArrayList<>();
      String sql = "SELECT * FROM universities";
      
      try (PreparedStatement statement = connection.prepareStatement(sql);
           ResultSet resultSet = statement.executeQuery())
      {
         while (resultSet.next())
         {
            University university = new University(resultSet.getInt("university_id"),
                                                   resultSet.getString("university_name"),
                                                   resultSet.getString("location"),
                                                   resultSet.getString("email_domain")
                                                   resultSet.getInt("num_students"));
            universities.add(university);
         }
      }
      
      return universities;
   }
   
   // Add a new university to the database
   public int add(University university) throws SQLException
   {
      String sql = "INSERT INTO universities (university_name, location, email_domain) VALUES (?, ?, ?)";
      
      try (PreparedStatement statement = connection.prepareStatement(sql, new String[]{"university_id"}))
      {
         statement.setString(1, university.getName());
         statement.setString(2, university.getLocation());
         statement.setString(3, university.getEmailDomain());
         
         int rowsInserted = statement.executeUpdate();
         
         if (rowsInserted > 0)
         {
            try (ResultSet generatedKeys = statement.getGeneratedKeys())
            {
               if (generatedKeys.next())
               {
                  int id = generatedKeys.getInt(1);
                  university.setId(id);
                  return id;
               }
            }
         }
      }
      
      return -1;
   }
   
   // Update a university in the database
   public void update(University university) throws SQLException
   {
        String query = "UPDATE universities SET university_name = ?, location = ?, email_domain = ? WHERE university_id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, university.getName());
            statement.setString(2, university.getLocation());
            statement.setString(3, university.getEmailDomain());
            statement.setInt(4, university.getUniversityId());
            
            statement.executeUpdate();
        }
    }
    
    // Delete a university in the database
    public void deleteUniversity(int universityId)
    {
      String sql = "DELETE FROM universities WHERE university_id = ?";
      try (Connection conn = getConnection();
           PreparedStatement stmt = conn.prepareStatement(sql))
      {
         stmt.setInt(1, universityId);
         stmt.executeUpdate();
      }
      catch (SQLException ex)
      {
         System.err.println("Error deleting university: " + ex.getMessage());
      }
   }
}