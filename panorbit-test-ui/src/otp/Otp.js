import { useState } from "react"
import { useNavigate } from "react-router-dom";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import 'bootstrap/dist/css/bootstrap.css';

const Otp = () => {
    const navigate = useNavigate()
    const [getOtp, setOtp] = useState();
    const loginApi = (e) => {
        e.preventDefault()
        fetch("http://localhost:8080/verify-otp",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    "otp": getOtp,
                    "email": sessionStorage.getItem("email")
                })

            })
            .then(res => res.json())
            .then(res => {
                sessionStorage.setItem("session", res.data)
                navigate("/dashboard")
            })
            .catch(err => {
                alert(err)
            })
    }
    return (
        <div className="container">
            <h1>Otp Page</h1>
            <Form>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    <Form.Control type="text" placeholder="Enter otp" onChange={(e) => setOtp(e.target.value)} />
                </Form.Group>
                <Button variant="primary" type="submit" onClick={(e) => loginApi(e)}>
                    Verify
                </Button>
            </Form>
        </div>
    )
}

export default Otp;