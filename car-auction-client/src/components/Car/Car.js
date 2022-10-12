import React from "react";
import { useNavigate } from "react-router";
import "./Car.css";

const Car = ({ car }) => {
  const navigate = useNavigate();

  const bids = car.bids;
  const maxBid = Math.max(...bids.map((o) => o.amount));
  return (
    <div className="col-md-4 col-sm-6 col-lg-3 col-xl-3 mb-3">
      <div className="card">
        <img
          src="https://images.unsplash.com/photo-1603584173870-7f23fdae1b7a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1169&q=80"
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
                Current Bid:{" "}
                <span className=" bid-value">
                  {" "}
                  {maxBid > 0 ? maxBid : "No Bid Yet"}
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
    </div>
  );
};

export default Car;
