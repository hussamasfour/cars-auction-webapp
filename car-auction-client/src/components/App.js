import React, { useEffect, useState } from "react";

import { Navigate, Route, Routes } from "react-router-dom";
import AuthService from "../utils/authService";
import "./App.css";
import Home from "./Home/Home";
import Login from "./Login/Login";
import Register from "./Register/Register";

function App() {
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user) {
      setCurrentUser(user);
    }
  }, []);

  return (
    <div className="container main-app">
      <Routes>
        <Route
          path="/"
          element={currentUser ? <Home /> : <Navigate replace to="/login" />}
        />
        />
        <Route
          path="/login"
          element={currentUser ? <Navigate replace to="/" /> : <Login />}
        ></Route>
        <Route
          path="/register"
          element={currentUser ? <Navigate replace to="/" /> : <Register />}
        />
      </Routes>
    </div>
  );
}

export default App;
