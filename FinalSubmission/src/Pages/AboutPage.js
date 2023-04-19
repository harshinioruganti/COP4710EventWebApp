import React from 'react'; 
import Navbar from "../Components/Navbar"; 
import About from "../Components/About";
import MyComponent from "../Components/MyComponent";
import UCFEventsPrintout from "../Components/UCFEventsPrintout"

const AboutPage = () =>
{
    return (
        <div className="page">
            <Navbar />
            <About />
            <MyComponent/>
        </div>
    ); 
}; 

export default AboutPage;
