import { useRef } from "react"; 
import { FaBars, FaTimes } from "react-icons/fa"; 
import { useState } from "react";
import { useEffect } from "react"; 
import "../Styles/ShowEvent.css";

const ShowEvent = () =>{

    const [events, setEvents] = useState([]);


  return (
    <div>
      <div class = "info">
        <h1>Key Club Initiation</h1>
        <p>4/20/23 @ 6:00PM</p>
        <p>Student Union</p>
        <p>Join us for our new member initiation! This is your chance to become a part of a passionate and dedicated group of young leaders who are committed to serving others. Through Key Club, you'll have the opportunity to volunteer at local events, fundraise for important causes, and develop your leadership skills through various workshops and activities. Our initiation event is the perfect introduction to this incredible organization. You'll meet current members, learn more about our goals and initiatives, and participate in fun team-building exercises. Don't miss out on this chance to make a positive impact and build lifelong friendships. Sign up now for our Key Club initiation event!</p>
      </div>
      <ul>
        {events.map(event => (
          <li key={event.event_id}>
            <h2>{event.event_name}</h2>
            <p>{event.description}</p>
            <p>{event.event_date_and_time}</p>
            <p>{event.location_name}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ShowEvent; 

/*
INSERT INTO events 
(event_id, event_name, category, description, event_date_and_time, location_name, latitude, longitude, contact_phone, contact_email, event_type)

VALUES (2, 'Ice Cream Social', 'public', 'Free Ice Cream and Mingling!', TO_DATE('2023-04-25 3:15:00', 'YYYY-MM-DD HH24:MI:SS')
, 'Toppers Ice Cream', 312.46, 413.84, 1112223333, 'toppers@gmail.com', 'social');
*/