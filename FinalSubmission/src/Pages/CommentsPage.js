import React from 'react'; 
import Navbar from "../Components/Navbar"; 
import ListEvents from '../Components/ListEvents';
import UCFEventsPrintout from '../Components/UCFEventsPrintout'
import TokenTest from '../Components/TokenTest';
import Comments from '../Components/Comments';
import ShowEvent from '../Components/ShowEvent';

const CommentsPage = () =>
{
    return (
        <div className="page">
            <Navbar />
            <ShowEvent />

            <Comments/>
        </div>
    ); 
}; 

export default CommentsPage;