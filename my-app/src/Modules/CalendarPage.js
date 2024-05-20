import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './CalendarPage.css';
import { useNavigate } from 'react-router-dom';
import { useState } from "react";
import Button from "./Button";

function CalendarPage() {
    const navigate = useNavigate();
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);

    const handleDateChange = (dates) => {
        const [start, end] = dates;
        setStartDate(start);
        setEndDate(end);
    };

    const handleButtonClick = () => {
        if (startDate && endDate) {
            // Spara datum till sessionStorage
            sessionStorage.setItem('bookingStartDate', startDate.toISOString());
            sessionStorage.setItem('bookingEndDate', endDate.toISOString());
            navigate('/rooms');
        } else {
            console.error('Please select a date range.');
        }
    };

    return (
        <div className="calendarPage">
            <p className="header">Select date for your stay</p>
            <Calendar
                onChange={handleDateChange}
                selectRange={true}
                minDate={new Date()}
                maxDate={new Date(Date.now() + 60 * 24 * 60 * 60 * 1000)} // Kan max boka 60 dagar framåt
            />
            <Button
                onClick={handleButtonClick}
                text="Book"
            />
        </div>
    );
}

export default CalendarPage;