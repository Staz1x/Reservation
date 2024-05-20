import { useEffect, useState } from 'react';

function BookingPage() {
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [selectedRoom, setSelectedRoom] = useState(null);
    const [totalPrice, setTotalPrice] = useState(0);

    useEffect(() => {
        const start = sessionStorage.getItem("bookingStartDate");
        const end = sessionStorage.getItem("bookingEndDate")
        const roomData = sessionStorage.getItem('selectedRoom');
        const price = sessionStorage.getItem('totalPrice');

        if (roomData && price) {
            setSelectedRoom(JSON.parse(roomData));
            setTotalPrice(parseFloat(price));
        }
    }, []);

    return (
        <div>
            <h2>Booking Confirmation</h2>
            {selectedRoom && (
                <div>
                    <p>Selected Room: {selectedRoom.roomNumber}</p>
                    <p>Total Price: ${totalPrice}</p>
                    {/* Add more details about the selected room here */}
                </div>
            )}
            {!selectedRoom && <p>No room selected</p>}
        </div>
    );
}

export default BookingPage;
