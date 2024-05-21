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
                navigate("/login");
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
                <h2>Register new user</h2>
                <label>
                    First Name:
                    <input
                        type="text"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        className="register-input"
                    />
                </label>
                <label>
                    Last Name:
                    <input
                        type="text"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        className="register-input"
                    />
                </label>
                <label>
                    Phone number:
                    <input
                        type="text"
                        value={phoneNr}
                        onChange={(e) => setPhoneNr(e.target.value)}
                        className="register-input"
                    />
                </label>
                <label>
                    Email:
                    <input
                        type="text"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="register-input"
                    />
                </label>
                <label>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="register-input"
                    />
                </label>
                <Button type="submit" text="Create"/>
                {error && <p className="error-message">{error}</p>} {}
            </form>
        </div>
    );
}

export default CreateUserPage;
