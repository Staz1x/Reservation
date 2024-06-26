import { useState } from "react";
import Button from "./Button";
import { useNavigate } from "react-router-dom";
import './Css/LoginPage.css';

function LoginPage() {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleLogin = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/users/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, password }),
            });

            if (response.ok) {
                const data = await response.json();
                sessionStorage.setItem("userId", data.userId);
                navigate('/home');
            } else {
                const errorMessage = await response.text();
                setError(errorMessage);
            }
        } catch (error) {
            console.error("Error during login:", error);
        }
    };

    return (
        <div className="login-container">
            <form onSubmit={(e) => {
                e.preventDefault();
                handleLogin();
            }} className="login-form">
                <h2>Login</h2>
                <label>
                    Email:
                    <input
                        type="text"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="login-input"
                    />
                </label>
                <label>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="login-input"
                    />
                </label>
                <div className="buttons-container">
                    <Button type="submit" text="Login" />
                    <Button onClick={() => navigate("/createNew")} text="Create new" />
                </div>
                {error && <p className="error-message">{error}</p>}
            </form>
        </div>
    );
}

export default LoginPage;
