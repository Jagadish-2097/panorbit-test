import React, { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import './Register.css';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import 'bootstrap/dist/css/bootstrap.css'
function Register() {
    const navigate = useNavigate();
    const [data, setData] = useState([]);
    const [firstName, setFirstName] = useState();
    const [lastName, setLastName] = useState();
    const [gender, setGender] = useState();
    const [email, setEmail] = useState();
    const [phone, setPhone] = useState();

    const getRegister = (e) => {
        fetch("http://192.168.1.5:8080/sign-up",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    "firstName": firstName,
                    "lastName": lastName,
                    "gender": gender,
                    "email": email,
                    "phone": phone
                })

            })
            .then(myresponse => myresponse.json())
            .then(res => {
                setData(res);
                navigate("/login")
            }).catch(err => {
                alert(err)
            })
    }

    useEffect(() => {
        getRegister();
    }, [])

    return (
        <div className="container">
            <Form>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control type="text" placeholder="First Name" onChange={(e) => setFirstName(e.target.value)} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput2">
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control type="text" placeholder="Last Name" onChange={(e) => setLastName(e.target.value)} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput3">
                    <Form.Label>Gender</Form.Label>
                    <Form.Control type="text" placeholder="Gender" onChange={(e) => setGender(e.target.value)} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" onChange={(e) => setEmail(e.target.value)} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput4">
                    <Form.Label>Phone</Form.Label>
                    <Form.Control type="number" placeholder="Phone" onChange={(e) => setPhone(e.target.value)} />
                </Form.Group>
                <Button variant="primary" type="button" onClick={(e) => getRegister(e)}>
                    Submit
                </Button>
                <p>Already Have an account? <a href="/login">Sign in</a></p>
            </Form>
        </div>

    )
}

export default Register;