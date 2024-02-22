import {useState} from "react";
import Button from "./Button";
import {useNavigate} from "react-router-dom";

function LoginPage() {
    const navigate = useNavigate();

    const handleButtonClick = () => {
        console.log("Button Clicked");
        navigate({
            pathname: '/calendar',
        });
    };

    return (

        <div>
            <p>Hello World</p>
            <Button
                onClick={handleButtonClick}
                text="NEXT"
            />
        </div>

    );

}
console.log("LoginPage rendered");
export default LoginPage;
