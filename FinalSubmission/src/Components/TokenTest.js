import React, { useState } from 'react';
import axios from 'axios';

function TokenTest() {
  const [userId, setUserId] = useState('');
  const [response, setResponse] = useState('');

  const handleUserIdChange = (event) => {
    setUserId(event.target.value);
  };

  const handleGetTokenClick = async () => {
    try {
      const response = await axios.get(`http://localhost:5000/api/token/${userId}`);
      setResponse(response.data.success ? 'Token created successfully' : 'Token creation failed');
    } catch (error) {
      console.error(error);
      setResponse('Error creating token');
    }
  };

  return (
    <div>
      <label htmlFor="userId">User ID:</label>
      <input type="text" id="userId" value={userId} onChange={handleUserIdChange} />
      <button onClick={handleGetTokenClick}>Get Token</button>
      <p>{response}</p>
    </div>
  );
}

export default TokenTest;