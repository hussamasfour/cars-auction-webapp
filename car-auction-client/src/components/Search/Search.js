import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import carService from "../../utils/carService";

const Search = ({ allCars, searchVal }) => {
  const navigate = useNavigate();
  const [searchValue, setSearchValue] = useState("");
  const [searchResult, setSearchResult] = useState({});
  const [isClicked, setIsClicked] = useState(false);
  const [isLoaded, setIsLoaded] = useState(false);

  useEffect(() => {
    if (searchValue.length !== 0 && isClicked) {
      carService.searchCar(searchValue).then((response) => {
        setSearchResult(response.data);
        setIsLoaded(true);
        setIsClicked(false);
        // navigate("/result", {
        //   state: { searchResult },
        // });
      });
    }
  }, [isClicked, navigate, searchResult, searchValue]);

  useEffect(() => {
    if (isLoaded) {
      navigate("/result", {
        state: { searchResult, searchValue },
      });
      setIsLoaded(false);
    }
  }, [isClicked, isLoaded, searchResult, searchValue]);

  const handleChange = (e) => {
    setSearchValue(e.target.value);
  };

  const handleSearch = (e) => {
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
              className="form-control p-3"
              placeholder="Search by Make, Year, Model or vin"
              onChange={handleChange}
              defaultValue={searchVal}
            />
            <button className="btn btn-primary " onClick={handleSearch}>
              Go
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Search;
