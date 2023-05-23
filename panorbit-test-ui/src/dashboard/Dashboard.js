import React, { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import "./Dashboard.css"
import Logout from "../logout/Logout";
function Dashboard() {
    const navigate = useNavigate();
    const [dropDownData, setDropDownData] = useState([]);
    const [search, setSearch] = useState();
    const [temp, setTemp] = useState(false);
    const [singleSelections, setSingleSelections] = useState([]);

    const getDropDownData = (val) => {
        setSingleSelections(val)
        fetch(`http://localhost:8080/search/${sessionStorage.getItem("session")}?searchParam=${val}`,
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },

            })
            .then(myresponse => myresponse.json())
            .then(res => {
                console.log(res.data)
                setTemp(true);
                setDropDownData(res.data.reponse);
            }).catch(err => {
                console.log(err)
            })
    }

    const clickable = (val) => {
        navigate("/detailedview", {state: {searchparam: singleSelections}})
    }
    const dropDown = (val) => {
        
        navigate("/countrydetails", {state: {countrycode: val}})
    }
    return (
        <div>
            <Logout />
            <div class="container">
                <div class="row">
                    <form class="col-md-6 py-2">
                        <h5>Bootstrap 4 Typeahead</h5>
                        <div class="input-group">
                            <input type="text" class="form-control typeahead border-primary" placeholder="Start typing something to search..." autocomplete="off" onChange={e =>getDropDownData(e.target.value)}/>
                            <div class="input-group-append">
                                <button type="button" class="btn btn-outline-primary" onClick={() => clickable(singleSelections)}>
                                    Search
                                </button>
                            </div>
                        </div>
                        {dropDownData.map(e => <p className="view-btn" onClick={() => e.placeHolder === 'Country' ? dropDown(e.code) : ""}>{e.name}</p>)}
                    </form>
                </div>
            </div>
        </div>
    )
}

export default Dashboard;