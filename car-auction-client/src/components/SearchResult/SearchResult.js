import React from "react";
import { useLocation } from "react-router-dom";

const SearchResult = () => {
  const location = useLocation();
  console.log(location);
  return;
};

export default SearchResult;
