import React, { useEffect, useState } from "react";
import carService from "../../utils/carService";
import NavBar from "../NavBar/NavBar";

const AddCar = () => {
  const [carDetails, setCarDetails] = useState({
    make: "",
    model: "",
    year: 0,
    numberOfSeats: 0,
    vinNumber: "",
    fuelType: "",
    fuelCapacity: 0.0,
    imagesLink: "",
    auctionEnd: "",
  });
  const [responseMessage, setResponseMessage] = useState(null);
  const [isSubmitted, setIsSubmitted] = useState(false);

  useEffect(() => {
    if (isSubmitted) {
      carService.addCar(carDetails).then((response) => {
        setResponseMessage(response.data);
      });
      setIsSubmitted(false);
    }
  }, [isSubmitted]);
  const handleSubmit = (e) => {
    e.preventDefault();
    if (responseMessage !== null) {
      setResponseMessage(null);
    }
    setIsSubmitted(true);
    console.log(carDetails);
  };

  function handleUpload(e) {
    setCarDetails({ ...carDetails, imagesLink: e.target.files[0].name });
  }
  return (
    <React.Fragment>
      <NavBar />
      <div className="row justify-content-center">
        <div className="col-lg-7">
          <div className="text-white p-3 rounded mt-5 bg-dark shadow-lg ">
            <h2 className="mb-4">Add Vehicle</h2>
            <form>
              <div className="form-group mb-3">
                <label htmlFor="Make">Make:</label>
                <input
                  type="make"
                  className="form-control"
                  id="Make"
                  aria-describedby="emailHelp"
                  placeholder="Enter Make"
                  onChange={(e) =>
                    setCarDetails({ ...carDetails, make: e.target.value })
                  }
                />

                {/* <small id="make" className="form-text text-danger">
                  We'll never share your email with anyone else.
                </small> */}
              </div>
              <div className="form-group mb-3">
                <label htmlFor="model">Model</label>
                <input
                  type="text"
                  className="form-control"
                  id="model"
                  placeholder="Model"
                  onChange={(e) =>
                    setCarDetails({ ...carDetails, model: e.target.value })
                  }
                />
              </div>
              <div className="form-group mb-3">
                <label htmlFor="Year">Year</label>
                <input
                  type="number"
                  className="form-control"
                  id="Year"
                  placeholder="Year"
                  onChange={(e) =>
                    setCarDetails({ ...carDetails, year: e.target.value })
                  }
                />
              </div>
              <div className="form-group mb-3">
                <label htmlFor="numberOfSeats">numberOfSeats</label>
                <input
                  type="number"
                  className="form-control"
                  id="numberOfSeats"
                  placeholder="numberOfSeats"
                  onChange={(e) =>
                    setCarDetails({
                      ...carDetails,
                      numberOfSeats: e.target.value,
                    })
                  }
                />
              </div>

              <div className="form-group mb-3">
                <label htmlFor="vinNumber">Vin Number</label>
                <input
                  type="text"
                  className="form-control"
                  id="vinNumber"
                  placeholder="vinNumber"
                  onChange={(e) =>
                    setCarDetails({ ...carDetails, vinNumber: e.target.value })
                  }
                />
              </div>
              <div className="form-group mb-3">
                <label htmlFor="vinNumber">fuelCapacity</label>
                <input
                  type="number"
                  className="form-control"
                  id="fuelCapacity"
                  placeholder="fuelCapacity"
                  onChange={(e) =>
                    setCarDetails({
                      ...carDetails,
                      fuelCapacity: e.target.value,
                    })
                  }
                />
              </div>
              <div className="input-group mb-3 d-flex flex-column">
                <label htmlFor="fuelType">Fuel Type</label>
                <select
                  className="custom-select p-2 rounded"
                  id="inputGroupSelect02"
                  onChange={(e) =>
                    setCarDetails({
                      ...carDetails,
                      fuelType: e.target.value,
                    })
                  }
                >
                  <option defaultValue="gas">Choose...</option>
                  <option value="Gas">Gas</option>
                  <option value="Desial">Desial</option>
                  <option value="Electric">Electric</option>
                </select>
              </div>
              <div className="form-group mb-3">
                <label htmlFor="auctionEnd">Auction End date</label>
                <input
                  type="date"
                  className="form-control"
                  id="auctionEnd"
                  onChange={(e) =>
                    setCarDetails({
                      ...carDetails,
                      auctionEnd: e.target.value,
                    })
                  }
                />
              </div>
              <div className="form-group mb-4 mt-4">
                <input type="file" onChange={handleUpload} />
              </div>

              <button
                type="submit"
                className="btn btn-primary"
                onClick={handleSubmit}
              >
                Submit
              </button>
              {responseMessage ? (
                <div className="text-white bg-success mt-3 p-2 rounded">
                  Successfully!! a {responseMessage}
                </div>
              ) : null}
            </form>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
};

export default AddCar;
