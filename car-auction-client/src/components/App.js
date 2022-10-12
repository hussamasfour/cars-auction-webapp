import React, { useEffect, useState } from "react";

import { Navigate, Route, Routes } from "react-router-dom";
import AuthService from "../utils/authService";
import "./App.css";
import CarDetails from "./CarDetails/CarDetails";
import Home from "./Home/Home";
import Login from "./Login/Login";
import NavBar from "./NavBar/NavBar";
import Register from "./Register/Register";
import SearchResult from "./SearchResult/SearchResult";

function App() {
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user) {
      setCurrentUser(user);
    }
  }, []);

  return (
    <div className="container">
      <Routes>
        <Route
          path="/"
          element={currentUser ? <Home /> : <Navigate replace to="/login" />}
        />

        <Route
          path="/login"
          element={currentUser ? <Navigate replace to="/" /> : <Login />}
        ></Route>
        <Route
          path="/register"
          element={currentUser ? <Navigate replace to="/" /> : <Register />}
        />
        <Route path="/result" element={<SearchResult />} />
        <Route path="/car/:id" element={<CarDetails />} />
      </Routes>
    </div>
  );
}

export default App;
