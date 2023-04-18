const express = require('express');
const cors = require('cors');
const axios = require('axios');
const jwtModule = require('./jwt');
const oracledb = require('oracledb');

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

  app.get('/api/login', (req, res) => {
    res.send({ message: 'Hello from Express!' });

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
        password: '',
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