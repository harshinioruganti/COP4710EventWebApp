import React, { useState } from 'react';
import "../Styles/Register.css"
import { useEffect } from 'react'; 
import 'bootstrap/dist/css/bootstrap.min.css'; 

function RSOCreationFields()
{
    useEffect(() => {
        document.title = 'Register';
    }, []);

    let user = '';
    let search = '';
    let registerFirstName;
    let registerLastName;
    let registerUsername;
    let registerEmail;
    let registerPassword;

    const [message, setMessage] = useState()

    const app_name = 'cerealboxd'
    function buildPath(route)
    {
        if (process.env.NODE_ENV === 'production') 
        {
            return 'https://' + app_name +  '.herokuapp.com/' + route;
        }
        else
        {        
            return 'http://localhost:5000/' + route;
        }
    }

    const doRegister = async event => 
    {
        event.preventDefault();
        window.location.href = '/listofrsos';
        var obj = {fName:registerFirstName.value,lName:registerLastName.value,userName:registerUsername.value,password:registerPassword.value};

        var js = JSON.stringify(obj);

        try
        {    
            
            // window.alert(JSON.stringify(js));
            const response = await fetch(buildPath('http://localhost:5000/api/register'),
                {method:'POST',body:js,headers:{'Content-Type': 'application/json'}});
            
            // let txt = await response.text();
            // let res = JSON.parse(txt);

            // if( res.error.length > 0 )
            // {
            //     setMessage( "API Error:" + res.error );
            // }
            // else
            // {
            //     setMessage('User has been added');
            // }

            window.location.href = '/admin';
        }
        catch(e)
        {
            setMessage(e.toString());
        }

    }

    return(
        <div class='wrapper-register d-flex align-items-center justify-content-center'>
            <div class='container align-items-center justify-content-center' id="registerDiv">
                <form onSubmit={doRegister}>
                    <center><span id= "title">Create RSO</span></center>
                    <center><input type="text" id="registerFirstName" placeholder="RSO Name" ref={(c) => registerFirstName =c}/><br /></center>
                    <center><input type="text" id="registerLastName" placeholder="University" ref={(c) => registerLastName =c}/><br /></center>
                    <center><input type="text" id="registerLastName" placeholder="Description" ref={(c) => registerLastName =c}/><br /></center>
                    <center><input type="text" id="registerUsername" placeholder="Student 1 Email" ref={(c) => registerUsername =c}/><br /></center>
                    <center><input type="text" id="registerUsername" placeholder="Student 2 Email" ref={(c) => registerUsername =c}/><br /></center>
                    <center><input type="text" id="registerUsername" placeholder="Student 3 Email" ref={(c) => registerUsername =c}/><br /></center>
                    <center><input type="text" id="registerUsername" placeholder="Student 4 Email" ref={(c) => registerUsername =c}/><br /></center>
                    {/* <center><input type="text" id="registerEmail" placeholder="EMAIL"/><br /></center>
                    <center><input type="password" id="registerPassword" placeholder="PASSWORD" ref={(c) => registerPassword =c}/><br /></center>
                    <center><input type="password" id="retypePassword" placeholder="RETYPE PASSWORD" /><br /></center> */}
                    <center><input type="submit" id="registerButton" class="buttons" value = "Create RSO" onclick={doRegister}/></center>
                </form>
            </div>
        </div>
    );
};

export default RSOCreationFields;
