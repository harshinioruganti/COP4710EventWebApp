const oracledb = require('oracledb');

async function initialize() {
  const pool = await oracledb.createPool({
    user: 'sys',
    password: 'harshini',
    connectString: 'jdbc:oracle:thin:@localhost:1521:orcl',
    poolMax: 5,
    poolMin: 2,
    poolIncrement: 1,
    poolTimeout: 4,
  });
  return pool;
}

function close(pool) {
  return pool.close();
}

module.exports.initialize = initialize;
module.exports.close = close;
