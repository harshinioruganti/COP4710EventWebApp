const express = require('express');
const cors = require('cors');
const axios = require('axios');
const jwtModule = require('./jwt');
const oracledb = require('oracledb');
let idInc = 6;

const app = express();
app.use(express.json());
app.use(cors());

app.get('/api/token/:id', (req, res) => {
    const id = req.params.id;
    const token = jwtModule.createToken(id, res);
    if (token) {
      res.status(200).send({ success: true });
    } else {
      res.status(500).send({ error: 'Error creating JWT' });
    }
  });

  app.post('/api/login', async (req, res) => {
    const { username, password } = req.body;
  
    try {
      // Connect to the Oracle database
      const connection = await oracledb.getConnection({
        user: 'system',
        password: 'raptor12',
        connectString: 'localhost/xe'
      });
  
      // Execute the query to retrieve the user with the matching username and password
      const query = `SELECT * FROM USERS WHERE USERNAME = :username AND PASSWORD = :password`;
      const result = await connection.execute(query, [username, password]);
  
      if (result.rows.length === 0) {
        // No matching user found, return an error response
        res.status(401).send({ error: 'Invalid username or password' });
      } else {
        // Matching user found, return the user information as a response
        const user = {
          id: result.rows[0][0],
          username: result.rows[0][1],
          password: result.rows[0][2],
          userType: result.rows[0][4]

          // Add any other user information you want to return
        };
        res.send({ user });
        console.log(user.username);
      }
  
      // Release the connection
      await connection.close();
  
    } catch (error) {
      console.error(error);
      res.status(500).send({ error: 'Error connecting to Oracle database' });
    }
  });

  app.post('/api/register', async (req, res) => {
    const { username, password, email, userType } = req.body;
    idInc++;
  
    try {
      // Connect to the Oracle database
      const connection = await oracledb.getConnection({
        user: 'system',
        password: 'raptor12',
        connectString: 'localhost/xe'
      });
      
      // Get the current user ID from the database and increment it
      const findID = "SELECT MAX(user_id) FROM Users";
      const IDfound = await connection.execute(findID);
      const currentUserId = IDfound.rows[0][0] || 0;
      const nextUserId = currentUserId + 1;

      console.log(nextUserId);
      console.log(username);
      console.log(password);
      // Insert the new user into the database
      const insertQuery = "INSERT INTO users (user_id, username, password, email, user_type) VALUES (7, 'abc', 'def', 'dasda', 'superadmin')";
      const bindParams = {
        userid: nextUserId,
        username: username,
        password: password,
        email: email,
        user_type: userType
      };
      const insertResult = await connection.execute(insertQuery);
      

      //const query = 'INSERT INTO  (user_id, username, password, email, user_type) VALUES (:idInc, :username, :password, :email, :userType)';
      //const result = await connection.execute(query, [idInc, username, password, email, userType]);
      
      // Matching user found, return the user information as a response
      res.send({ message: 'success' });

      // Add any other user information you want to return
  
      // Release the connection
      await connection.close();
  
    } catch (error) {
      console.error(error);
      res.status(500).send({ error: 'Error connecting to Oracle database' });
    }
  });



app.get('/api/hello', (req, res) => {
  res.send({ message: 'Hello from Express!' });
});

app.get('/api/events', async (req, res) => {
    try {
      const response = await axios.get('http://events.ucf.edu/feed.json%27');
      const events = response.data;
      res.send(events);
    } catch (error) {
      console.error(error);
      res.status(500).send('Error retrieving events from UCF website');
    }
  });

  app.get('/api/data', async (req, res) => {
    try {
      // Connect to the Oracle database
      const connection = await oracledb.getConnection({
        user: 'system',
        //***************UPDATE WITH YOUR OWN PASSWORD BELOW ***************
        password: 'raptor12',
        connectString: 'localhost/xe'
      });

      //Edit this SQL statement to be whatever data you need
      const output = await connection.execute(`SELECT * FROM EMPLOYEES`);

      //Edit these arrays to be whatever value you want from the database [row][col]
      console.log(output.rows[0][1]);
      res.send({ message: output.rows[0][1] });
      
      // Release the connection
      await connection.close();

    } catch (error) {
      console.error(error);
      res.status(500).send('Error connecting to Oracle database');
    }
  });



const port = process.env.PORT || 5000;
app.listen(port, () => {
  console.log('Server started on port ${port}');
});