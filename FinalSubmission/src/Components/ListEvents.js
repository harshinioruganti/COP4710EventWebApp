import React from 'react';
import { useState } from 'react';
import Login from './Login';


function ListEvents()
{

    // get the user id to find RSO's student is involved in (from login?)

    // append the RSO's to the current json?

    const [users, setUsers] = useState([
        // use json return from api
        { id: 1, firstName: 'Frank', lastName: 'Murphy', email: 'frank.murphy@test.com', role: 'User' },
        { id: 2, firstName: 'Vic', lastName: 'Reynolds', email: 'vic.reynolds@test.com', role: 'Admin' },
        { id: 3, firstName: 'Gina', lastName: 'Jabowski', email: 'gina.jabowski@test.com', role: 'Admin' },
        { id: 4, firstName: 'Jessi', lastName: 'Glaser', email: 'jessi.glaser@test.com', role: 'User' },
        { id: 5, firstName: 'Jay', lastName: 'Bilzerian', email: 'jay.bilzerian@test.com', role: 'User' }
    ]);
    

    return(
        <div>
            <h1>Events</h1>
            <table className='ListEvents'>
                <thead>
                    <tr>
                        <th>Event Name</th>
                        <th>Type</th>
                        <th>ksjafl;</th>
                    </tr>
                </thead>
                <tbody>
                    {users && users.map(user =>
                        <tr key={user.id}>
                            <td>{user.firstName} {user.lastName}</td>
                            <td>{user.email}</td>
                            <td>{user.role}</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>

        
    );

};

export default ListEvents;