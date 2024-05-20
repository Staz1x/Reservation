import './RoomsPage.css';
import {useCallback, useEffect, useState} from 'react';
import Button from './Button';
import { useNavigate } from 'react-router-dom';

function RoomsPage() {
    const navigate = useNavigate();
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [availableRooms, setAvailableRooms] = useState([]);

    const getNumberOfNights = (startDate, endDate) => {
        const oneDay = 24 * 60 * 60 * 1000;
        return Math.round(Math.abs((new Date(startDate) - new Date(endDate)) / oneDay)-1);
    };

    const fetchAvailableRooms = useCallback(async (startDate, endDate) => {
        try {
            const start = new Date(startDate).toISOString().split('T')[0];
            const end = new Date(endDate).toISOString().split('T')[0];
            const url = `http://localhost:8080/api/rooms/available/${start}/${end}`;
            //console.log('Fetching rooms with URL:', url); // Logga URL för felsökning
            const response = await fetch(url);
            const contentType = response.headers.get('content-type');

            if (response.ok) {
                //console.log("Response was ok") //Log för felsökning
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
    }, []);

    const initializeBookingDates = useCallback(() => {
        const bookingStartDate = sessionStorage.getItem('bookingStartDate');
        const bookingEndDate = sessionStorage.getItem('bookingEndDate');
        if (bookingStartDate && bookingEndDate) {
            setStartDate(new Date(bookingStartDate));
            setEndDate(new Date(bookingEndDate));
            fetchAvailableRooms(bookingStartDate, bookingEndDate);
        } else {
            console.error('Date range not found in sessionStorage.');
        }
    }, [fetchAvailableRooms]);

    useEffect(() => {
        //console.log('Initializing booking dates'); // Log för felsökning med att funktionen kallas två gånger
        initializeBookingDates();
    }, [initializeBookingDates]);

    const handleRoomSelection = (room) => {
        sessionStorage.setItem('selectedRoom', JSON.stringify(room));
        sessionStorage.setItem('totalPrice', room.price * getNumberOfNights(startDate, endDate));
        navigate('/booking');
    };

    return (
        <div className="rooms-page">
            <h2>Available Rooms</h2>
            <p className="date-range">
                {startDate && endDate ? `${startDate.toDateString()} - ${endDate.toDateString()}` : 'Date range not selected'}
            </p>
            <div className="rooms-container">
                {availableRooms.map((room, index) => (
                    <div key={index} className="room-card">
                        <h3>Room {room.roomNumber}</h3>
                        <p><strong>Price per Night:</strong> ${room.price}</p>
                        <p><strong>Type of Room:</strong> {room.roomType}</p>
                        <p><strong>Total Price:</strong> ${room.price * getNumberOfNights(startDate, endDate)}</p>
                        <Button onClick={() => handleRoomSelection(room)} text="Select Room" />
                    </div>
                ))}
            </div>
        </div>
    );
}

export default RoomsPage;
