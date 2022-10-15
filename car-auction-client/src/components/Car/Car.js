import React from "react";
import { useNavigate } from "react-router";
import "./Car.css";

const Car = ({ car }) => {
  const navigate = useNavigate();

  const bids = car.bids;
  const maxBid = Math.max(...bids.map((o) => o.amount));

  console.log(car);
  return (
    <div className="card card-custom">
      <img
        src={"./images/" + car.imagesLink}
        className="card-img-top"
        alt="..."
      />
      <div className="card-body ">
        <h5 className="card-title">
          {car.year} {car.make} {car.model}
        </h5>
        <h6 className="card-subtitle mb-2 text-muted">
          Sale End : <span className="text-danger">{car.auctionEnd}</span>
        </h6>
        <p className="card-text"></p>
        <div className="row  align-items-center justify-content-center">
          <div className="col-xl-12 col-lg-12">
            <p>
              Current Bid:
              <span className=" bid-value">
                {maxBid > 0 ? maxBid : " No Bid Yet"}
              </span>
            </p>
            <button
              className="btn btn-success px-3 car-btn"
              onClick={() => {
                navigate("/car/" + car.id);
              }}
            >
              Details
            </button>
          </div>
          <div className="col-xl-6 col-lg-3 align-items-center"></div>
        </div>
      </div>
    </div>
  );
};

export default Car;
