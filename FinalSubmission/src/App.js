import React from 'react'; 
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./Components/Navbar"; 
import Home from "./Components/Home"; 
import About from "./Components/About"; 
import Login from "./Components/Login"; 
import Register from "./Components/Register"; 
import ListEvents from "./Components/ListEvents";
import MyComponent from './Components/MyComponent';

import StudentDashboard from './Pages/StudentDashboard';
import UniDash from './Pages/UniDash';
import AdminDash from './Pages/AdminDash';
import UCFEventsPrintoutPage from './Pages/UCFEventsPrintoutPage';

import RSOCreationFields from './Components/RSOCreationFields';
import EventCreationFields from './Components/EventCreationFields';

import CommentsPage from './Pages/CommentsPage';
import ListofRSOs from './Pages/ListofRSOs';

function App() {
  return(
    <BrowserRouter>
      <Routes>
       <Route path="/" index element={<React.Fragment><Navbar /><Home /></React.Fragment>} />
       <Route path="/AboutPage" index element = {<React.Fragment><Navbar /><MyComponent /></React.Fragment>}/>
       <Route path="/LoginPage" index element={<React.Fragment><Navbar /><Login /></React.Fragment>} />
       <Route path="/RegisterPage" index element = {<React.Fragment><Navbar /><Register /></React.Fragment>}/>
       <Route path="/student" index element ={<StudentDashboard />}></Route>
       <Route path="/superadmin" index element ={<UniDash />}></Route>
       <Route path="/admin" index element ={<AdminDash />}></Route>
       <Route path="/RsoCreation" index element ={<RSOCreationFields />}></Route>
       <Route path="/EventCreation" index element ={<EventCreationFields />}></Route>
       <Route path="/event1" index element ={<CommentsPage/>}></Route>
       <Route path="/event2" index element ={<CommentsPage/>}></Route>
       <Route path="/listofrsos" index element ={<ListofRSOs/>}></Route>



      
      </Routes>
    </BrowserRouter>
  ); 
}

export default App; 