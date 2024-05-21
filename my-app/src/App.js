import React from 'react';
import {
    HashRouter as Router,
    Routes,
    Route,
} from "react-router-dom";
import LoginPage from "./Modules/LoginPage";
import CreateUserPage from "./Modules/CreateUserPage";
import CalendarPage from "./Modules/CalendarPage";
import RoomsPage from "./Modules/RoomsPage";
import BookingPage from "./Modules/BookingPage";
import MyPage from "./Modules/MyPage";
import "./App.css";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/mypage" element={<MyPage />} />
                <Route path="/calendar" element={<CalendarPage />} />
                <Route path="/rooms" element={<RoomsPage />} />
                <Route path="/createNew" element={<CreateUserPage />} />
                <Route path="/booking" element={<BookingPage />} />
            </Routes>
        </Router>
    );
}

export default App;
