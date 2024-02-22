import React from 'react';
import {
    HashRouter as Router,
    Routes,
    Route,
} from "react-router-dom";
import LoginPage from "./Modules/LoginPage"
import CalendarPage from "./Modules/CalendarPage";
import "./App.css"



function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/calendar" element={<CalendarPage />} />
            </Routes>
        </Router>
    );
}

export default App;
