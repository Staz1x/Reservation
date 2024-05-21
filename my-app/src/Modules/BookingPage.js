import { useEffect, useState } from 'react';
import './Css/BookingPage.css';

function BookingPage() {
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [room, setRoom] = useState(null);
    const [totalPrice, setTotalPrice] = useState(0);
    const [user, setUser] = useState('');

    useEffect(() => {
        const start = sessionStorage.getItem("bookingStartDate");
        const end = sessionStorage.getItem("bookingEndDate")
        const selectedRoom = JSON.parse(sessionStorage.getItem('selectedRoom'));
        const price = sessionStorage.getItem('totalPrice');
        const userId = sessionStorage.getItem('userId');

        if (userId) {
            fetch(`http://localhost:8080/api/users/${userId}`)
                .then(response => response.json())
                .then(data => setUser(data))
                .catch(error => console.error('Error fetching user details:', error));
        }
        if (selectedRoom) {
            setRoom(selectedRoom);
        }
        if (start && end) {
            setStartDate(new Date(start));
            setEndDate(new Date(end));
        }
        if (price) {
            setTotalPrice(price);
        }
    }, []);

    return (
        <div className="booking-page">
            <h2>Booking Confirmation</h2>
            <div className="confirmation-card">
                {user && (
                    <div className="user-details">
                        <p><strong>Name:</strong> {user.firstName} {user.lastName}</p>
                    </div>
                )}
                {room && (
                    <div className="room-details">
                        <p><strong>Type of Room:</strong> {room.roomType}</p>
                        <p><strong>Price per Night:</strong> ${room.price}</p>
                        <p><strong>Total Price:</strong> ${totalPrice}</p>
                    </div>
                )}
                {startDate && endDate && (
                    <div className="date-details">
                        <p><strong>Start Date:</strong> {startDate.toDateString()}</p>
                        <p><strong>End Date:</strong> {endDate.toDateString()}</p>
                    </div>
                )}
            </div>
            <h2 className="bottom">Enjoy your stay!</h2>
        </div>
    );
}

export default BookingPage;
