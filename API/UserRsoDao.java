
public class UserRsoDao 
{
    private DatabaseConnection connection;
   
    public UserRsoDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    
    // adds a user-rso mapping entry into this table
    public void addUserRsoDao(User user, Rso rso)
    {
        String sql = "INSERT INTO user_rso (user, rso) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, user.getUserId());
            statement.setInt(2, rso.getRsoId());
            statement.executeUpdate();
        }
        else
        {
            throw new SQLException("Creating User-RSO mapping failed.");
        }
    }

    public void removeUserRsoDao(int userId, int rsoId)
    {
        String sql = "DELETE FROM user_rso WHERE user_id = :userId AND rso_id = :rsoId";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, userId);
            statement.setInt(2, rsoId);
            statement.executeUpdate();
        }
        else 
        {
            throw new SQLException("Deleting User-RSO mapping failed.");
        }
    }
}