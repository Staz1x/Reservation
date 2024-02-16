import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './App.css';
import {useState} from "react";

const minDate = Date.now();
const today = new Date(minDate);
const maxDate = today;

console.log(today.toDateString());
console.log(maxDate);

function App() {
    const [value, setValue] = useState(new Date());

    function onChange(nextValue) {
        setValue(nextValue);
    }

  return (
    <div> <p className="header"> Select date </p>
      <Calendar
          onChange={onChange}
          value={value}
          minDate={new Date(minDate)}
          //maxDate={new Date(maxDate)}
          selectRange={true} />
    </div>
  );
}

export default App;
