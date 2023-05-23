import React from "react"
import { useNavigate } from "react-router-dom"
const Logout = () => {
    const navigate = useNavigate()
    const clearSession = () => {
        sessionStorage.clear()
        navigate("/")
    }

    return (
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="navbar-nav">
                <button type="button" onClick={(e) => clearSession()}>Logout
                </button>
            </div>

        </nav>
    )

}

export default Logout