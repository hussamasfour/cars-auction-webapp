import React from "react";
import { Route, Routes } from "react-router-dom";
import "./App.css";
import Home from "./Home/Home";
import Login from "./Login/Login";
import Register from "./Register/Register";

function App() {
  return (
    <div className="container">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </div>
  );
}

export default App;
