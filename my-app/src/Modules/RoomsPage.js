import './RoomsPage.css'
import {useEffect, useState} from "react";
import Button from "./Button";
import {useNavigate} from "react-router-dom";
import bookingPage from "./BookingPage";

function RoomsPage() {
    const navigate = useNavigate();
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [roomId, setRoomId] = useState([]);
    const [totalPrice, setTotalPrice] = useState([]);
    const [roomType, setRoomType] = useState([]);
    const [availableRooms, setAvailableRooms] = useState([]);

    useEffect(() => {
        // Hämtar datum från sessionStorage
        const bookingStartDate = sessionStorage.getItem('bookingStartDate');
        const bookingEndDate = sessionStorage.getItem('bookingEndDate');
        if (bookingStartDate && bookingEndDate) {
            setStartDate(new Date(bookingStartDate));
            setEndDate(new Date(bookingEndDate));
            // Hämtar lediga rum inom datumen
            fetchAvailableRooms(bookingStartDate, bookingEndDate);
        } else {
            console.error('Date range not found in localStorage.');
        }
    }, []);

    // Räknar ut antal nätter
    const getNumberOfNights = (startDate, endDate) => {
        const oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
        return Math.round(Math.abs((startDate - endDate) / oneDay));
    };

    const fetchAvailableRooms = async (startDate, endDate) => {
        try {
            const response = await fetch(`/api/rooms/availabile?startDate=${startDate}&endDate=${endDate}`);
            if (response.ok) {
                const roomsData = await response.json();
                setAvailableRooms(roomsData);
            } else {
                console.error('Failed to fetch available rooms:', response.statusText);
            }
        } catch (error) {
            console.error('Error fetching available rooms:', error);
        }
    };

    const handleRoomSelection = (room) => {
        //Logik för att spara ruminfo till sessionStorage och gå vidare till bokningssidan
        sessionStorage.setItem('selectedRoom', JSON.stringify(room));
        sessionStorage.setItem('totalPrice', room.price * getNumberOfNights(startDate, endDate));

        navigate("/booking")

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
                        <p>Total Price: ${room.price * getNumberOfNights(startDate, endDate)}</p>
                        <Button onClick={() => handleRoomSelection(room)} text="Select Room" />
                    </div>
                ))}
            </div>
        </div>
    );

}


export default RoomsPage;