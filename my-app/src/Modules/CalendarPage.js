import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './CalendarPage.css';
import {useNavigate} from 'react-router-dom';
import {useState} from "react";
import Button from "./Button";

const minDate = Date.now();
const today = new Date(minDate);
const maxDate = today;

console.log(today.toDateString());
console.log(maxDate);

function CalendarPage() {
    const navigate = useNavigate();
    const [value, setValue] = useState(new Date());

    function onChange(nextValue) {
        setValue(nextValue);
    }

    const handleButtonClick = () => {
        navigate({
            pathname: '/booking',
        });
    };

    return (
        <div> <p className="header"> Select date </p>
            <Calendar
                onChange={onChange}
                value={value}
                minDate={new Date(minDate)}
                //maxDate={new Date(maxDate)}
                selectRange={true} />
            <Button
                onClick={handleButtonClick}
                text="NEXT2"
            />
        </div>
    );
}

export default CalendarPage;
