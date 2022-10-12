import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import carService from "../../utils/carService";

import CarList from "../CarList/CarList";

const Search = ({ allCars }) => {
  const navigate = useNavigate();
  const [searchValue, setSearchValue] = useState("");
  const [searchResult, setSearchResult] = useState({});
  const [isClicked, setIsClicked] = useState(false);

  const getSearch = async () => {
    const data = await axios.get("http://localhost:8080/api/search-car", {
      params: {
        query: searchValue,
      },
    });
    setSearchResult(data);
    setIsClicked(false);
  };
  useEffect(() => {
    if (searchValue.length !== 0 && isClicked) {
      // carService.searchCar(searchValue).then((response) => {
      //   setSearchResult(response.data);
      //   setIsClicked(false);
      //   console.log(searchResult);

      // });

      getSearch();
      console.log(searchResult);

      if (searchResult.status === 200) {
        navigate("/result", {
          state: { searchResult },
        });
      }
    }
  }, [isClicked, searchValue]);

  const handleChange = (e) => {
    setSearchValue(e.target.value);
  };

  const handleSearch = (e) => {
    // carService.searchCar(searchValue).then((response)=>{
    //   setSearchResult(response.data);
    setIsClicked(true);
  };

  return (
    <div>
      <div className="row  d-flex justify-content-center align-items-center mt-5">
        <div className="col-md-7">
          <div className="form-outline d-flex flex-direction-row">
            <i className="fa fa-search"></i>
            <input
              type="text"
              className="form-control p-4"
              placeholder="Search by Make, Year, Model or vin"
              onChange={handleChange}
            />
            <button className="btn btn-primary " onClick={handleSearch}>
              Go
            </button>
          </div>
        </div>
      </div>
      {/* <div className="row">
        {allCars.length !== 0 ? <CarList allCars={allCars} /> : "Loeading"}
      </div> */}
    </div>
  );
};

export default Search;
