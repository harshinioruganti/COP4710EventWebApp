import React from 'react'; 
import Navbar from "../Components/Navbar"; 
import ListEvents from '../Components/ListEvents';
import UCFEventsPrintout from '../Components/UCFEventsPrintout'
import TokenTest from '../Components/TokenTest';

const UCFEventsPrintoutPage = () =>
{
    return (
        <div className="page">
            <Navbar />

            <UCFEventsPrintout/>
           

        </div>
    ); 
}; 

export default UCFEventsPrintoutPage;