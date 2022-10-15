import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Link } from "react-router-dom";
import carService from "../../utils/carService";
import NavBar from "../NavBar/NavBar";
import "./CarDetails.css";

const CarDetails = () => {
  const { id } = useParams();
  const [selectedCar, setSelectedCar] = useState({});
  const [bidAmount, setBidAmount] = useState(0.0);
  const [suc, setSuc] = useState(null);

  useEffect(() => {
    carService
      .getCarById(id)
      .then((response) => {
        setSelectedCar(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }, [id]);

  const handleBid = () => {
    carService
      .placeBid(selectedCar.id, bidAmount)
      .then((response) => {
        if (response.status === 201) {
          setSuc("Successfully placed a bid");
        }
      })
      .catch((e) => {
        console.log(e);
      });
  };
  return (
    <div>
      <NavBar />
      <div className="row  ">
        <div className="col-lg-10">
          <div className="mt-5 p-4 bg-white mb-3 rounded">
            {/* <Link to="/">Back </Link> */}
            <h4>
              {selectedCar.year} {selectedCar.make} {selectedCar.model}
            </h4>
          </div>
        </div>
      </div>
      <div className="row ">
        <div
          className="col-lg-5 col-sm-12
        "
        >
          <img
            src="/audi.jpg"
            alt="car img"
            width="100%"
            height="400px"
            className="rounded"
          />
        </div>
        <div className="col-lg-5 col-md-12 col-sm-12 ">
          <div className="bg-white rounded  p-3">
            <h3>Car Info:</h3>
            <div className="p-2">
              <div className="row justify-content-space-between car-info">
                <div className="d-flex justify-content-between pt-3 pb-2">
                  <span>Make:</span>
                  <span style={{ fontWeight: 700 }}>{selectedCar.make}</span>
                </div>
                <div className="d-flex justify-content-between pt-3 pb-2">
                  <span>Model:</span>
                  <span style={{ fontWeight: 700 }}>{selectedCar.model}</span>
                </div>{" "}
                <div className="d-flex justify-content-between pt-3 pb-2">
                  <span>Vin:</span>
                  <span style={{ fontWeight: 600 }}>
                    {selectedCar.vinNumber}
                  </span>
                </div>{" "}
                <div className="d-flex justify-content-between pt-3 pb-2">
                  <span>Seats:</span>
                  <span style={{ fontWeight: 700 }}>
                    {selectedCar.numberOfSeats}
                  </span>
                </div>{" "}
                <div className="d-flex justify-content-between pt-3 pb-2">
                  <span>Fuel Type:</span>
                  <span style={{ fontWeight: 700 }}>
                    {selectedCar.fuelType}
                  </span>
                </div>
                <div className="d-flex justify-content-between pt-3 pb-2">
                  <span>Fuel Cap:</span>
                  <span style={{ fontWeight: 700 }}>
                    {selectedCar.fuelCapacity} Gal
                  </span>
                </div>
                <div className="d-flex justify-content-between pt-3 pb-2">
                  <span>Fuel Cap:</span>
                  <span style={{ fontWeight: 700 }}>
                    {selectedCar.fuelCapacity} Gal
                  </span>
                </div>
                <div className="d-flex justify-content-between pt-3 pb-2">
                  <span>Fuel Cap:</span>
                  <span style={{ fontWeight: 700 }}>
                    {selectedCar.fuelCapacity} Gal
                  </span>
                </div>
                <div className="d-flex justify-content-between pt-3 pb-2">
                  <span>Fuel Cap:</span>
                  <span style={{ fontWeight: 700 }}>
                    {selectedCar.fuelCapacity} Gal
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div className="bidding-info bg-white mt-3 rounded p-3">
            <h3>Bidding Details:</h3>
            <div className=" car-info">
              <div className="d-flex justify-content-between pt-3 pb-2">
                <span>Bid End</span>
                <span style={{ fontWeight: 700, color: "red" }}>
                  {selectedCar.auctionEnd}
                </span>
              </div>
              <div className="d-flex justify-content-between pt-3 pb-2">
                <span>Current Bid</span>
                <span style={{ fontWeight: 700 }}>
                  {selectedCar.bids
                    ? Math.max(...selectedCar.bids.map((o) => o.amount))
                    : "No bid yet"}
                </span>
              </div>
              <div className="d-flex justify-content-between pt-3 pb-2 ">
                <span>Start Bidding:</span>
                <span className="d-flex flex-column ">
                  <input
                    type="number"
                    className="bid-input p-2 mb-3"
                    onChange={(e) => setBidAmount(e.target.value)}
                    placeholder="$ Bid..."
                  />
                  <div className="d-flex justify-content-center ">
                    <button className="btn btn-primary " onClick={handleBid}>
                      Place Bid
                    </button>
                  </div>
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CarDetails;
