import Car from "../Car/Car";
import "./CarList.css";
const CarList = ({ allCars }) => {
  return (
    <div className="">
      <div className="row car-list">
        <h2 className="text-white mb-4 ">Hot Deals:</h2>
        {allCars
          .filter((car, idx) => idx < 4)
          .map((car) => {
            return <Car key={car.id} car={car} />;
          })}
      </div>
    </div>
  );
};

export default CarList;
