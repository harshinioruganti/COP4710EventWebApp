import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "event_id")
   private int event_id;
   
   @Column(name = "event_name", nullable = false)
   private String event_name;
   
   @Column(name = "category", nullable = false)
   private String category;

   @Column(name = "description", nullable = false)
   private String description;

   @Column(name = "event_time", nullable = false)
   private Date event_time;

   @Column(name = "event_date", nullable = false)
   private Date event_date;

   @Column(name = "location_name", nullable = false)
   private String location_name;
   
   @Column(name = "latitude", nullable = false)
   private double latitude;

   @Column(name = "longitude", nullable = false)
   private double longitude;

   @Column(name = "contact_phone", nullable = false)
   private String contact_phone;

   @Column(name = "contact_email", nullable = false)
   private String contact_email;

   @Column(name = "event_type", nullable = false)
   private String event_type;

   @Column(name = "approval_status", nullable = false)
   private char approval_status;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "rso_id")
   private int rso_id;
   
   private Rso rso;
   
   public Events()
   {
   }
   
   public Events(String event_name, String category, String description, Date event_time, Date event_date,
                  String location_name, double latitude, double longitude, String contact_phone, String contact_email,
                  String event_type, char approval_status)
   {
        this.event_name = event_name;
        this.category = category;
        this.description = description;
        this.event_time = event_time;
        this.event_date = event_date;
        this.location_name = location_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contact_phone = contact_phone;
        this.contact_email = contact_email;
        this.event_type = event_type;
        this.approval_status = approval_status;
        
        /* every event does not have to have an associated RSO, 
           so rso_id can be excluded from the constructor
           and be set using a separate setter method.*/
    }
    
    public int getEventId()
    {
      return event_id;
    }
    
    public String getEventName()
    {
      return event_name;
    }
    
    public void setEventName(String event_name)
    {
      this.event_name = event_name;
    }
    
    public String getCategory()
    {
      return category;
    }
    
    public void setCategory(String category)
    {
      this.category = category;
    }
    
    public String getDescription()
    {
      return description;
    }
    
    public void setDescription(String description)
    {
      this.description = description;
    }
    
    public Date getEventTime()
    {
      return event_time;
    }
    
    public Date getEventDate()
    {
      return event_date;
    }
    
    public void setEventDate(Date event_date)
    {
      this.event_date = event_date;
    }
    
    public String getLocationName()
    {
      return location_name;
    }
    
    public void setLocationName(String location_name)
    {
      this.location_name = location_name;
    }
    
    public double getLatitude()
    {
      return latitude;
    }
    
    public void setLatitude(double latitude)
    {
      this.latitude = latitude;
    }
    
    public double getLongitude()
    {
      return longitude;
    }
    
    public void setLongitude(double longitude)
    {
      this.longitude = longitude;
    }
    
    public String getContactPhone()
    {
        return contact_phone;
    }

    public void setContactPhone(String contact_phone)
    {
        this.contact_phone = contact_phone;
    }
    
    public String getContactEmail()
    {
        return contact_email;
    }

    public void setContactEmail(String contact_email)
    {
        this.contact_email = contact_email;
    }
    
    public String getEventType() // social, fundraising, tech talks, etc.
    {
        return event_type;
    }

    public void setEventType(String event_type)
    {
        this.event_type = event_type;
    }
    
    public char getApprovalStatus()
    {
      return approval_status;
    }
    
    public void setApprovalStatus(char approval_status)
    {
      this.approval_status = approval_status;
    }
    
    public int getRso()
    {
      return rso;
    }   
    
    public void setRso(Rso rso)
    {
      this.rso = rso;
    }
}