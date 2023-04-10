import javax.persistence.*;

@Entity
@Table(name = "university_images")
public class UniversityImage
{
    private int universityId;
    private byte[] picture;
    private University university;

    public UniversityImage(int universityId, byte[] picture)
    {
        this.universityId = universityId;
        this.picture = picture;
    }

    public int getUniversityId()
    {
        return universityId;
    }
    
    public void setUniversityId(int universityId)
    {
        this.universityId = universityId;
    }

    public byte[] getPicture()
    {
        return picture;
    }

    public void setPicture(byte[] picture)
    {
        this.picture = picture;
    }

    public University getUniversity()
    {
        return university;
    }

    public void setUniversity(University university)
    {
        this.university = university;
    }

}
