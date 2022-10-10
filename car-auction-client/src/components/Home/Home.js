import React, { useEffect, useState } from "react";
import carService from "../../utils/carService";
import CarList from "../CarList/CarList";
import NavBar from "../NavBar/NavBar";
import Search from "../Search/Search";
import "./Home.css";
const Home = () => {
  const [allCars, setAllCars] = useState([]);

  useEffect(() => {
    carService.getAllCars().then((response) => {
      setAllCars(response.data);
    });
  }, []);

  if (allCars.length === 0) {
    return <p>Loading</p>;
  } else {
    return (
      <div className="row back d-flex ">
        <NavBar />

        <h1 className="d-flex justify-content-center home-header">
          Welcome To Car Deals
        </h1>
        <Search allCars={allCars} />

        <CarList allCars={allCars} />
      </div>
    );
  }
};

export default Home;
