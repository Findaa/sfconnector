import React, {useState} from 'react';
import FormGroup from "react-bootstrap/lib/FormGroup";
import ControlLabel from "react-bootstrap/lib/ControlLabel";
import FormControl from "react-bootstrap/lib/FormControl";
import Button from "react-bootstrap/lib/Button";

export default function LoginForm() {
    const [username, setUsername] = useState("a");
    const [password, setPassword] = useState("b");

    let requestAuthentication = (username, password) => {
        const requestParameters = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({username, password})
        };
        return fetch("http://localhost:8080/loginfront", requestParameters)
            .then(handleResponse)
            .then(user => {
                if (user) {
                }
            })
    };

    let handleResponse = (response) => {
        return response.text().then(text => {
            const data = text && JSON.parse(text);
            if (!response.ok) {
                const error = (data && data.message) || response.statusText;
                console.log("promise rejesct");
                return Promise.reject(error);
            }
            return data;
        });
    };

    let handleSubmit = (event) => {
        requestAuthentication(username, password);
        event.preventDefault();
    };

    return (
        <div className="Login">
            <form onSubmit={handleSubmit}>
                <FormGroup controlId="email" bsSize="large">
                    <ControlLabel>Email</ControlLabel>
                    <FormControl
                        autoFocus
                        type="text"
                        onChange={e => {
                            setUsername(e.target.value);
                        }}/>
                </FormGroup>
                <FormGroup controlId="password" bsSize="large">
                    <ControlLabel>Password</ControlLabel>
                    <FormControl
                        onChange={e => {
                            setPassword(e.target.value);
                        }}
                        type="password"/>
                </FormGroup>
                <button type="submit" onClick={handleSubmit}>
                    Login
                </button>
            </form>
            <div></div>
            <div></div>
            <div>
                To authorize with Salesforce, login here:
            </div>
            <Button variant="primary" size="sm" href="http://localhost:8080/login">Login with Salesforce</Button>
        </div>
    );
}
