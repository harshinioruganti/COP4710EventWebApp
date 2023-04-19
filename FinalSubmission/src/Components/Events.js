import React from 'react'; 
import { useEffect } from 'react';
import "../Styles/Events.css"; 

function Events()
{
    useEffect(() => {

    }, []);

    return(
        <div>
            <div class="heading">
                Hello, Welcome Muhammad
            </div>
            <div class="cardContainer">    
                <div class="card">
                    <h2>ICE CREAM SOCIAL</h2>
                    <p>4/25/23 @ 3:15PM</p>
                    <p>Toppers Ice Cream</p>
                    <p>Free Ice Cream and Mingling!</p>
                    <a href="LoginPage">
                        <button>Register Now</button>
                    </a>
                </div>
                <div class="card">
                    <h2>Garden Volunteering</h2>
                    <p>4/18/23 @ 12PM</p>
                    <p>UCF Arboretum</p>
                    <p>Come volunteer with us at the campus garden!</p>
                    <a href="LoginPage">
                        <button>Register Now</button>
                    </a>
                </div>
                <div class="card">
                    <h2>Key Club Initiation</h2>
                    <p>4/20/23 @ 6pm</p>
                    <p>UCF Student Union</p>
                    <p>Join us for our new member initiation</p>
                    <a href="event1">
                        <button>Register Now</button>
                    </a>
                </div>
            </div>     
        </div>
    );

};




export default Events;

