import './Css/HomePage.css';
import {useEffect, useState} from "react";
import Button from "./Button";
import {useNavigate} from "react-router-dom";

function HomePage() {

    const navigate = useNavigate();
    const [bookings, setBookings] = useState([]);
    const [error, setError] = useState("");

    const fetchBookings = async () => {
        const userId = sessionStorage.getItem("userId");
        if (userId) {
            try {
                const response = await fetch(`http://localhost:8080/api/bookings/booking/${userId}`);
                if (response.ok) {
                    const data = await response.json();
                    setBookings(data);
                } else {
                    setError(" ");
                }
            } catch (error) {
                setError("Error fetching bookings.");
                console.error("Error fetching bookings:", error);
            }
        } else {
            setError("User not logged in.");
        }
    };

    const handleDeleteBooking = async (bookingId) => {
        try {
            const response = await fetch(`http://localhost:8080/api/bookings/?id=${bookingId}`, {
                method: "DELETE"
            });
            if (response.ok) {
                setBookings(bookings.filter(booking => booking.bookingId !== bookingId));
                setError("Your booking was deleted");
            } else {
                const errorMessage = await response.text();
                setError(errorMessage);
            }
        } catch (error) {
            console.error("Error deleting booking:", error);
        }
    };

    useEffect(() => {
        fetchBookings();
    }, []);

    return (
        <div className="home-page">
            <h2>Your Bookings</h2>
            {error && <p className="error-message">{error}</p>}
            <div className="bookings-container">
                {bookings.length > 0 ? (
                    bookings.map((booking) => (
                        <div key={booking.bookingId} className="booking-card">
                            <p><strong>Room:</strong> {booking.room.roomNumber}</p>
                            <p><strong>Start Date:</strong> {new Date(booking.startDate).toLocaleDateString()}</p>
                            <p><strong>End Date:</strong> {new Date(booking.endDate).toLocaleDateString()}</p>
                            <Button
                                onClick={() => handleDeleteBooking(booking.bookingId)}
                                text="Cancel Booking"
                            />
                        </div>
                    ))
                ) : (
                    <p>No bookings available.</p>
                )}
            </div>
            <Button className onClick={() => navigate("/calendar")} text="Make new booking" />
        </div>
    );

}

export default HomePage;