import {
    Routes,
    Route,
    BrowserRouter
} from "react-router-dom";
import Login from "./login/login.js";
import Dashboard from "./dashboard/Dashboard.js";
import Register from "./Register/Register.js";
import Otp from "./otp/Otp.js";
import DetailedView from "./detailedview/Detailedview.js";
import Table from "./countrydetails/CountryDetails.js";
import Logout from "./logout/Logout.js";
// import DetailedView from "./detailedview/Detailedview.js";

function App() {
    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Login />} />
                    <Route path="/otp" element={<Otp />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/dashboard" element={<Dashboard />} />
                    <Route path="/detailedview" element={<DetailedView />} />
                    <Route path="/countrydetails" element ={<Table />} />
                    {/* <Route path="/Logout" element ={<Logout />} /> */}
                </Routes>
            </BrowserRouter>

        </>
    );
}

export default App;
