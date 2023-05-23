import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from 'react-router-dom';
import './detailedview.css';
import 'bootstrap/dist/css/bootstrap.css'
import Logout from "../logout/Logout";

function DetailedView() {
    let navigate = useLocation();
    const [cityData, setCityData] = useState([]);
    const [countryData, setCountryData] = useState([]);
    const [languageData, setLanguageData] = useState([]);

    const getTableData = () => {
        fetch(`http://localhost:8080/detailed-data/${sessionStorage.getItem("session")}?searchParam=${navigate.state.searchparam}`,
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                }
            })
            .then(myresponse => myresponse.json())
            .then(res => {
                setCityData(res.data.city.cityDetails);
                setCountryData(res.data.country.countryDetails);
                setLanguageData(res.data.language.languageDetails);
            }).catch(err => {
                console.log(err)
            })
    }

    useEffect(() => {
        getTableData();
    }, [])

    return (
        <>
            <Logout />
            <div style={{
                // marginBottom:"20px",
                marginTop: "20px"
            }}>
                <h4>City Details</h4>
                <hr></hr>
                <div style={{
                    height: "30vh",
                    overflowY: "scroll",
                    marginBottom: "30px"
                }}>
                    <table style={{ width: "100%", height: "20vh", overflow: "scroll" }}
                        className="table">
                        <thead className="thead-dark">
                            <tr style={{ position: "sticky", top: 0, background: "white" }}>
                                <th><span className="common_head">countryCode</span></th>
                                <th><span className="common_head">district</span></th>
                                <th><span className="common_head">name</span></th>
                                <th><span className="common_head">population</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                cityData.map((item, index) => (
                                    <tr>
                                        <td ><span className="common">{item.countryCode}</span></td>
                                        <td ><span className="common">{item.district}</span></td>
                                        <td ><span className="common">{item.name}</span></td>
                                        <td ><span className="common">{item.population}</span></td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>

                <hr />
                <h4 style={{ marginTop: "40px" }}>Country Details</h4>
                <hr></hr>
                <div style={{
                    height: "30vh",
                    overflowY: "scroll",
                    marginBottom: "30px",
                    // width:"180%",
                    // overflowX:"scroll"
                }}>
                    <table style={{ width: "270%", height: "20vh", overflow: "scroll" }}
                        className="table">
                        <thead className="thead-dark">
                            <tr style={{ position: "sticky", top: 0, background: "white" }}>
                                <th><span className="common_head">capital</span></th>
                                <th><span className="common_head">code</span></th>
                                <th><span className="common_head">code2</span></th>
                                <th><span className="common_head">continent</span></th>
                                <th><span className="common_head">gnp</span></th>
                                <th><span className="common_head">gnpold</span></th>
                                <th><span className="common_head">governmentForm</span></th>
                                <th><span className="common_head">headOfState</span></th>
                                <th><span className="common_head">indepYear</span></th>
                                <th><span className="common_head">lifeExpectancy</span></th>
                                <th><span className="common_head">localName</span></th>
                                <th><span className="common_head">name</span></th>
                                <th><span className="common_head">population</span></th>
                                <th><span className="common_head">region</span></th>
                                <th><span className="common_head">surfaceArea</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                countryData.map((item, index) => (
                                    <tr>
                                        <td><span className="common">{item.capital}</span></td>
                                        <td><span className="common">{item.code}</span></td>
                                        <td><span className="common">{item.code2}</span></td>
                                        <td><span className="common">{item.continent}</span></td>
                                        <td><span className="common">{item.gnp}</span></td>
                                        <td><span className="common">{item.gnpold}</span></td>
                                        <td><span className="common">{item.governmentForm}</span></td>
                                        <td><span className="common">{item.headOfState}</span></td>
                                        <td><span className="common">{item.indepYear}</span></td>
                                        <td><span className="common">{item.lifeExpectancy}</span></td>
                                        <td><span className="common">{item.localName}</span></td>
                                        <td><span className="common">{item.name}</span></td>
                                        <td><span className="common">{item.population}</span></td>
                                        <td><span className="common">{item.region}</span></td>
                                        <td><span className="common">{item.surfaceArea}</span></td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>

                <hr />
                <h4 style={{ marginTop: "40px" }}>Language Details</h4>
                <hr></hr>
                <div style={{
                    height: "30vh",
                    overflowY: "scroll"
                }}>
                    <table style={{ width: "100%", height: "20vh", overflow: "scroll" }} className="table">
                        <thead className="thead-dark">
                            <tr style={{ position: "sticky", top: 0, background: "white" }}>
                                <th><span className="common_head">countryCode</span></th>
                                <th><span className="common_head">isOfficial</span></th>
                                <th><span className="common_head">language</span></th>
                                <th><span className="common_head">percentage</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                languageData.map((item, index) => (
                                    <tr scope="row">
                                        <td><span className="common">{item.countryCode}</span></td>
                                        <td><span className="common">{item.isOfficial}</span></td>
                                        <td><span className="common">{item.language}</span></td>
                                        <td><span className="common">{item.percentage}</span></td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </>
    )
}

export default DetailedView;