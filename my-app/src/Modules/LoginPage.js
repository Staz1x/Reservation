import React, {useState} from "react";
import Button from "./Button";
import './Css/LoginPage.css'
import {useNavigate} from "react-router-dom";

const LoginPage = (props) => {
    const [user, setUser] = useState('')
    const [password, setPassword] = useState('')
    const [userError, setUserError] = useState('')
    const [passwordError, setPasswordError] = useState('')

    const navigate = useNavigate();

    const handleButtonClick = () => {

        setUserError('')
        setPasswordError('')

        // Check if the user has entered both fields correctly
        if ('' === user) {
            setUserError('Please enter your username')
            return
        }

        if (/\d$/.test(user)) {
            setUserError("Can't use numbers in username")
            return
        }

        if ('' === password) {
            setPasswordError('Please enter a password')
            return
        }

        if (password.length < 3) {
            setPasswordError('The password must be 4 characters or longer')
            return
        }

        navigate({
            pathname: '/calendar',
        });
    };

    return (
        <div className={'mainContainer'}>
            <div className={'titleContainer'}>
                <div>Login</div>
            </div>
            <br/>
            <div className={'inputContainer'}>
                <input
                    value={user}
                    placeholder="Enter your username"
                    onChange={(ev) => setUser(ev.target.value)}
                    className={'inputBox'}
                />
                <label className="errorLabel">{userError}</label>
            </div>
            <br/>
            <div className={'inputContainer'}>
                <input
                    value={password}
                    placeholder="Enter your password"
                    onChange={(ev) => setPassword(ev.target.value)}
                    className={'inputBox'}
                />
                <label className="errorLabel">{passwordError}</label>
            </div>
            <br/>
            <div className={'inputContainer'}>
                <Button onClick={handleButtonClick} text="Log in"/>
            </div>
        </div>
    );
}

export default LoginPage;
