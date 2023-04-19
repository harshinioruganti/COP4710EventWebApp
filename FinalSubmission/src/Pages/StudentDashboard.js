import React from 'react'; 
import Navbar from "../Components/Navbar"; 
import ListEvents from '../Components/ListEvents';
import UCFEventsPrintout from '../Components/UCFEventsPrintout'
import TokenTest from '../Components/TokenTest';
import Events from '../Components/Events';

const StudentDashboard = () =>
{
    return (
        <div className="page">
            <Navbar />
            <Events />

        </div>
    ); 
}; 

export default StudentDashboard;

