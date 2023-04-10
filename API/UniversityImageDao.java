import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UniversityImageDao
{
    private Connection connection;

    public UniversityImageDao(Connection connection) 
    {
        this.connection = connection;
    }

    public void addUniversityImage(UniversityImage universityImage) throws SQLException
    {
        String sql = "INSERT INTO university_images (university_id, picture) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, universityImage.getUniversity().getId());
        statement.setBlob(2, universityImage.getPicture());
        statement.executeUpdate();
    }

    public List<UniversityImage> getUniversityImagesByUniversityId(int universityId) throws SQLException
    {
        List<UniversityImage> universityImages = new ArrayList<>();
        String sql = "SELECT * FROM university_images WHERE university_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, universityId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next())
        {
            UniversityImage universityImage = createUniversityImageFromResultSet(rs);
            universityImages.add(universityImage);
        }
        
        return universityImages;
    }

    private UniversityImage createUniversityImageFromResultSet(ResultSet rs) throws SQLException
    {
        int universityId = rs.getInt("university_id");
        Blob picture = rs.getBlob("picture");
        University university = new UniversityDao(connection).getUniversityById(universityId);
        
        return new UniversityImage(university, picture);
    }
}
