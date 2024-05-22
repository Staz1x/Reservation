import { useState } from "react";
import Button from "./Button";
import { useNavigate } from "react-router-dom";
import './Css/CreateUserPage.css';

function CreateUserPage() {
    const navigate = useNavigate();
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [phoneNr, setPhoneNr] = useState("");
    const [error, setError] = useState("");

    const handleRegister = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/users/", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ firstName, lastName, phoneNr, email, password }),
            });

            if (response.ok) {
                navigate('/home');
            } else {
                const errorMessage = await response.text();
                setError(errorMessage);
            }
        } catch (error) {
            console.error("Error during registration:", error);
        }
    };

    return (
        <div className="register-container">
            <form onSubmit={(e) => {
                e.preventDefault();
                handleRegister();
            }} className="register-form">
                <h2>Create new user</h2>
                <div className="form-group">
                    <label>First Name:</label>
                    <input
                        type="text"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        className="register-input"
                    />
                </div>
                <div className="form-group">
                    <label>Last Name:</label>
                    <input
                        type="text"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        className="register-input"
                    />
                </div>
                <div className="form-group">
                    <label>Phone number:</label>
                    <input
                        type="text"
                        value={phoneNr}
                        onChange={(e) => setPhoneNr(e.target.value)}
                        className="register-input"
                    />
                </div>
                <div className="form-group">
                    <label>Email:</label>
                    <input
                        type="text"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="register-input"
                    />
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="register-input"
                    />
                </div>
                <div className="buttons-container">
                    <Button type="submit" text="Create"/>
                </div>
                {error && <p className="error-message">{error}</p>}
            </form>
        </div>
);
}

export default CreateUserPage;
