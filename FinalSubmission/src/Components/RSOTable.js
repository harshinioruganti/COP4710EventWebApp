import React, { useState } from "react";
import "../Styles/RSOTable.css";

function RSOTable() {
  const data = [
    { name: "Chess Club" },
    { name: "Knight Hacks" },
    { name: "Key Club" }
  ];

  const [joinStatus, setJoinStatus] = useState({});

  const handleJoinStatusToggle = (index) => {
    setJoinStatus((prevStatus) => {
      const newStatus = { ...prevStatus };
      newStatus[index] = !prevStatus[index];
      return newStatus;
    });
  };

  return (
    <div>
      <h2 class = "title">Available RSOs</h2>
      <table>
        <thead class = "head">
          <tr>
            <th>RSO Name</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody class = "body">
          {data.map((item, index) => (
            <tr key={index}>
              <td>{item.name}</td>
              <td>
                <button
                  onClick={() => handleJoinStatusToggle(index)}
                >
                  {joinStatus[index] ? "Joined" : "Not Joined"}
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default RSOTable;