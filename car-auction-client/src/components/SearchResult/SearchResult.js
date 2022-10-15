import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Car from "../Car/Car";
import NavBar from "../NavBar/NavBar";
import Search from "../Search/Search";

const SearchResult = () => {
  const [searchedCar, setSearchedCar] = useState([]);
  const location = useLocation();
  useEffect(() => {
    console.log(location.state);
    setSearchedCar(location.state.searchResult);
  }, []);

  return (
    <div>
      <NavBar />

      <Search searchVal={location.state.searchValue} />
      <div className="row car-list">
        <h2 className="text-white mb-4 ">
          <span className="text-success">{searchedCar.length}</span> Searched
          Results for: {location.state.searchValue}
        </h2>
        {searchedCar.map((car) => {
          return (
            <div className="col-md-4 col-sm-6 col-lg-4 col-xl-4 mb-3">
              <Car key={car.id} car={car} />
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default SearchResult;
