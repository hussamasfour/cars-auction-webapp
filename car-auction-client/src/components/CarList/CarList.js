import Car from "../Car/Car";
import "./CarList.css";

const CarList = ({ allCars }) => {
  return (
    <div className="row car-list">
      <h2 className="text-white mb-4 ">Hot Deals:</h2>
      {allCars
        .filter((car, idx) => idx < 4)
        .map((car) => {
          return (
            <div className="col-md-4 col-sm-6 col-lg-3 col-xl-3 mb-3">
              <Car key={car.id} car={car} />{" "}
            </div>
          );
        })}
    </div>
  );
};

export default CarList;
