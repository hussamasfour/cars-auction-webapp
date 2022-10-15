import React, { useEffect, useState } from "react";
import carService from "../../utils/carService";
import CarList from "../CarList/CarList";
import NavBar from "../NavBar/NavBar";
import Search from "../Search/Search";
import "./Home.css";
const Home = () => {
  const [allCars, setAllCars] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    carService
      .getAllCars()
      .then((response) => {
        setIsLoaded(true);
        setAllCars(response.data);
      })
      .catch((error) => {
        setIsLoaded(true);
        setError(error.response.data.message[0]);
      });
  }, []);

  if (error.length !== 0 && isLoaded) {
    return (
      <div>
        <NavBar />
        <h3 className=" text-white">{error}</h3>
      </div>
    );
  } else if (!isLoaded) {
    return <h3 className="text-white">Loading...</h3>;
  } else {
    return (
      <div className="row d-flex  ">
        <NavBar />

        <h1 className="row justify-content-center home-header">
          Welcome To Cars Deal
        </h1>
        <Search allCars={allCars} />

        <CarList allCars={allCars} />
      </div>
    );
  }
};

export default Home;
