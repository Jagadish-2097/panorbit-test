import React, { useState } from "react";
import "./login.css"
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import 'bootstrap/dist/css/bootstrap.css';
import { useNavigate } from "react-router-dom";

const Login = () => {

    const [getLoginInput, setLoginInput] = useState('')
    const navigate = useNavigate()
    const loginApi = (e) => {
        e.preventDefault()
        fetch("http://localhost:8080/verify-email",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    "email": getLoginInput,
                })

            })
            .then(res => {
                navigate("/otp")
                sessionStorage.setItem("email", getLoginInput)
            }).catch(err => {
                alert(err)
            })
    }
    return (
        <div className="container">
            <Form>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control type="text" placeholder="Email" onChange={(e) => setLoginInput(e.target.value)} />
                </Form.Group>
                <Button variant="primary" type="submit" onClick={(e) => loginApi(e)}>
                    Submit
                </Button>
                <p>Don't Have an account? <a href="/register">Sign up</a></p>
            </Form>
        </div>
    )
};

export default Login;