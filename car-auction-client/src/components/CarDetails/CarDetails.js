import React, { useEffect } from "react";
import { useParams } from "react-router";
import carService from "../../utils/carService";
import NavBar from "../NavBar/NavBar";

const CarDetails = () => {
  const { id } = useParams();
  useEffect(() => {
    carService
      .getCarById(id)
      .then((response) => {
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e.response);
      });
  }, []);
  return <h1>Car Details</h1>;
};

export default CarDetails;
