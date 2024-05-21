import {useEffect, useState} from "react";
import './Css/MyPage.css'

function MyPage() {

    const [user, setUser] = useState({});
    const [bookings, setBookings] = useState([]);

    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                const userId = sessionStorage.getItem('userId');
                const response = await fetch(`http://localhost:8080/api/users/${userId}`);
                if (response.ok) {
                    const data = await response.json();
                    setUser(data);
                } else {
                    console.error('Failed to fetch user information');
                }
            } catch (error) {
                console.error('Error fetching user information:', error);
            }
        };

        const fetchBookings = async () => {
            try {
                const userId = sessionStorage.getItem('userId');
                const response = await fetch(`http://localhost:8080/api/booking/bookings/user/${userId}`);
                if (response.ok) {
                    const data = await response.json();
                    setBookings(data);
                } else {
                    console.error('Failed to fetch bookings');
                }
            } catch (error) {
                console.error('Error fetching bookings:', error);
            }
        };

        fetchUserInfo();
        fetchBookings();
    }, []);

    return (
        <div className="mypage-container">
            <h2>My Page</h2>
            <div className="user-info">
                <h3>User Information</h3>
                <p>Name: {user.firstName} {user.lastName}</p>
                <p>Email: {user.email}</p>
                <p>Phone: {user.phoneNr}</p>
            </div>
            <div className="user-bookings">
                <h3>Current Bookings</h3>
                {bookings.length > 0 ? (
                    <ul>
                        {bookings.map((booking) => (
                            <li key={booking.id}>
                                {booking.room} from {booking.startDate} to {booking.endDate}
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>No bookings found.</p>
                )}
            </div>
        </div>
    );

}

export default MyPage();