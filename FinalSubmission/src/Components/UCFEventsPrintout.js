import axios from 'axios';
import { useState } from 'react';
import { useEffect } from 'react';

function UCFEventsPrintout() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:5000/api/events')
      .then(response => {
        setEvents(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);

  return (
    <div>
      {events.map(event => (
        <div key={event.id}>
          <h2 style={{ color: 'white' }}>{event.title}</h2>
          <p style={{ color: 'white' }}>{event.location}</p>
        </div>
      ))}
    </div>
  );
}

export default UCFEventsPrintout;