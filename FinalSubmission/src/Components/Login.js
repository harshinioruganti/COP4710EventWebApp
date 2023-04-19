import React, { useState } from 'react';
import "../Styles/Login.css"; 
import { useEffect } from 'react'; 
import 'bootstrap/dist/css/bootstrap.min.css'; 

function Login()
{
    // Page Title
    useEffect(() => {
        document.title = 'Login';
    }, []);

    let loginName;
    let loginPassword;

    const [message,setMessage] = useState('');

    function buildPath(route)
    {
        return 'http://localhost:5000/' + route;
    }

    const doLogin = async event =>
    {
        event.preventDefault();

        // get text fields & send to json object
        var obj = {username:loginName.value,password:loginPassword.value};
        var js = JSON.stringify(obj);
        
        var userType = 'admin';
        var student = 'student';
        var admin = 'admin';
        var super_admin = 'super_admin';
        var username = 'bob';

        try
        {
            const response = await fetch(buildPath('api/login'),
                {method:'POST',body:js,headers:{'Content-Type': 'application/json'}});

            var res = JSON.parse(await response.text());

            if( res.id <= 0 )
            {
                setMessage('User/Password combination incorrect');
            }
            else
            {
                var user = {username:res.user.username, password:res.user.password, id:res.user.id, userType:res.user.userType};
                
                // redirect the user to the student page if student
                if(user.userType === 'student')
                {
                    window.location.href = '/student';
                }

                // redirect the user to admin page if admin
                else if(user.userType === 'admin')
                {
                    window.location.href = '/admin';
                }
                // redirect the user to super_admin page if super_admin
                else if(user.userType === 'superadmin')
                {
                    window.location.href = '/';
                }
                // if no userType is found, alert with 'no user
                else
                {
                    alert('no user found.')
                }
                

            }
            // validate the user... i will fill this in myself.
            
            // api will return userType (student or admin or super_admin)
            
            // if username found
            if(true)
            {
                localStorage.setItem('username', username);
            }
            

        }
        catch(e)
        {
            alert(e.toString())
            return;
        }
    }

    return(
        <div class='wrapper-login d-flex align-items-center justify-content-center'>
            <div class='container align-items-center justify-content-center' id="loginDiv">
                <form onSubmit={doLogin}>
                    <center><span id= "title">LOGIN</span></center>
                    <center><input type="text" id="loginName" placeholder='USERNAME' ref={(c) => loginName = c} /><br /></center>
                    <center><input type="password" id="loginPassword" placeholder="PASSWORD" ref={(c) => loginPassword = c} /><br /></center>
                    <center><input type="submit" id="loginButton" class="buttons" value="SIGN IN" onClick={doLogin} /></center>
                    <center><a href="/RegisterPage"><span id="noAccount">Don't Have An Account? Register Here!</span></a></center>
                    <span id="loginResult">{message}</span>
                </form>
            </div>
        </div>
    );
};

export default Login;
