import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao
{
    
    private final DatabaseConnection connection;
    
    public UserDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }
    
    // Get user by ID 
    public User getUserById(int userId) throws SQLException
    {
        String sql = "SELECT users.*, university.university_name, university.location, university.email_domain" +
                     "FROM users" +
                     "LEFT JOIN universities ON users.university_id = universities.university_id" +
                     "WHERE users.user_id = ?";

        try (/*Connection connection = DriverManager.getConnection(url, username, password);*/
             PreparedStatement statement = connection.prepareStatement(sql))
        {
           statement.setInt(1, userId);
           ResultSet resultSet = statement.executeQuery();
           
           if (resultSet.next())
           {
               University university = new University(resultSet.getInt("university_id"),
                                                      resultSet.getString("university_name"),
                                                      resultSet.getString("location"),
                                                      resultSet.getString("email_domain"));
               
               User user = new User(resultSet.getInt("user_id"),
                                    resultSet.getString("username"),
                                    resultSet.getString("password"),
                                    resultSet.getString("email"),
                                    UserType.valueOf(resultSet.getString("user_type")),
                                    university);
               return user;
           }
           else
           {
               return null;
           }
           resultSet.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    // Get user by username 
    public User getUserByUsername(String username) throws SQLException
    {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next())
        {
            User user = new User();
            user.setUserId(resultSet.getInt("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setUserType(resultSet.getString("user_type"));
            user.setUniversityId(resultSet.getInt("university_id"));
            return user;
        }
        else
        {
            return null;
        }
    }
    
    // Retrieve all users in the database
    public List<User> getAllUsers() throws SQLException
    {
      String sql = "SELECT user_id, username, password, email, user_type, " +
                    "university_id, university.university_name, university.location, university.email_domain " +
                    "FROM users" +
                    "LEFT JOIN universities university ON university_id = university.university_id";
                    
      List<User> users = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                University university = new University(resultSet.getInt("university_id"),
                                                       resultSet.getString("university_name"),
                                                       resultSet.getString("location"),
                                                       resultSet.getString("email_domain"));
                
                User user = new User(resultSet.getInt("user_id"),
                                     resultSet.getString("username"),
                                     resultSet.getString("password"),
                                     resultSet.getString("email"),
                                     UserType.valueOf(resultSet.getString("user_type")),
                                     university);
                users.add(user);
            }
        }
        
        return users;
    }
    
    // Add user in the database
    public void addUser(User user) throws SQLException
    {
        String sql = "INSERT INTO users (user_id, username, password, email, user_type, university_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
           statement.setString(1, user.getUsername());
           statement.setString(2, user.getPassword());
           statement.setString(3, user.getEmail());
           statement.setString(4, user.getUserType());
           statement.setInt(5, user.getUniversity().getUniversityId());
           
           statement.executeUpdate();
        }
    }
    
    // Update user in the database
    public void updateUser(User user) throws SQLException
    {
        String sql = "UPDATE users SET username = ?, password = ?, email = ?, user_type = ?, university_id = ? WHERE user_id = ?";
       
        try(PreparedStatement statement = connection.prepareStatement(sql))
        {
           statement.setString(1, user.getUsername());
           statement.setString(2, user.getPassword());
           statement.setString(3, user.getEmail());
           statement.setString(4, user.getUserType());
           statement.setInt(5, user.getUniversity.getUniversityId());
           statement.setInt(6, user.getUserId());
           
           statement.executeUpdate();
        }
    }
    
    // Delete user in the database
    public void deleteUser(int userId) throws SQLException
    {
        String sql = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        statement.executeUpdate();
    }
}
