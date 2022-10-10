import React from "react";

import CarList from "../CarList/CarList";

const Search = ({ allCars }) => {
  // const [searchValue, setSearchValue] = useState("");
  // const [searchResult, setSearchResult] = useState({});
  // const [searchShow,setSearchShow] = useState(false);

  // const handleChange = (e) => {
  //   setSearchValue(e.target.value);
  // };

  // const handleSearch = (e) => {
  //   carService.searchCar(searchValue).then((response)=>{
  //     setSearchResult(response.data);
  //   });
  //   setSearchShow(true);
  // };
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
              // onChange={handleChange}
            />
            <button className="btn btn-primary ">Go</button>
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
