import './RoomsPage.css';
import {useEffect, useState} from 'react';
import Button from './Button';
import { useNavigate } from 'react-router-dom';

function RoomsPage() {
    const navigate = useNavigate();
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [availableRooms, setAvailableRooms] = useState([]);

    const getNumberOfNights = (startDate, endDate) => {
        const oneDay = 24 * 60 * 60 * 1000;
        return Math.round(Math.abs((new Date(startDate) - new Date(endDate)) / oneDay));
    };

    const fetchAvailableRooms = async (startDate, endDate) => {
        try {
            const start = new Date(startDate).toISOString().split('T')[0];
            const end = new Date(endDate).toISOString().split('T')[0];
            const url = `http://localhost:8080/api/rooms/available/${start}/${end}`;
            console.log('Fetching rooms with URL:', url); // Log URL
            const response = await fetch(url);
            const contentType = response.headers.get('content-type');

            if (response.ok) {
                console.log("Response was ok")
                if (contentType && contentType.includes('application/json')) {
                    const roomsData = await response.json();
                    setAvailableRooms(roomsData);
                } else {
                    console.error('Response was not JSON:', await response.text());
                }
            } else {
                console.error('Failed to fetch available rooms:', response.statusText);
                console.error('Response was:', await response.text());
            }
        } catch (error) {
            console.error('Error fetching available rooms:', error);
        }
    };

    const initializeBookingDates = () => {
        const bookingStartDate = sessionStorage.getItem('bookingStartDate');
        const bookingEndDate = sessionStorage.getItem('bookingEndDate');
        if (bookingStartDate && bookingEndDate) {
            setStartDate(new Date(bookingStartDate));
            setEndDate(new Date(bookingEndDate));
            fetchAvailableRooms(bookingStartDate, bookingEndDate);
        } else {
            console.error('Date range not found in sessionStorage.');
        }
    };

    useEffect(() => {
        console.log('Initializing booking dates'); // Log to see if this runs more than once
        initializeBookingDates();
    }, []); // Empty dependency array to ensure it runs only once

    const handleRoomSelection = (room) => {
        sessionStorage.setItem('selectedRoom', JSON.stringify(room));
        sessionStorage.setItem('totalPrice', room.price * getNumberOfNights(startDate, endDate));
        navigate('/booking');
    };

    return (
        <div>
            <h2>Available Rooms</h2>
            <p>Selected Date Range: {startDate && endDate ? `${startDate.toDateString()} - ${endDate.toDateString()}` : 'Not selected'}</p>
            <div>
                {availableRooms.map((room, index) => (
                    <div key={index}>
                        <p>Room: {room.roomNumber}</p>
                        <p>Price per Night: ${room.price}</p>
                        <p>Type of Room: {room.roomType}</p>
                        <p>Capacity: {room.capacity}</p>
                        <p>Total Price: ${room.price * getNumberOfNights(startDate, endDate)}</p>
                        <Button onClick={() => handleRoomSelection(room)} text="Select Room" />
                    </div>
                ))}
            </div>
        </div>
    );
}

export default RoomsPage;
