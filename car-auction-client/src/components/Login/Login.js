import React, { useEffect } from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../../utils/authService";

import "./login.css";

const Login = () => {
  let navigate = useNavigate();
  const [values, setValues] = useState({
    email: "",
    password: "",
  });

  const [isSubmitted, setIsSubmitted] = useState(false);

  const [loginError, setLoginError] = useState({});

  const handleSubmit = (e) => {
    e.preventDefault();
    setLoginError(validateInput(values));
    setIsSubmitted(true);
  };
  useEffect(() => {
    if (Object.keys(loginError).length === 0 && isSubmitted) {
      AuthService.login(values)
        .then(() => {
          navigate("/");
        })
        .catch((error) => {
          setLoginError(error.response.data);
          setIsSubmitted(false);
        });
    }
  }, [isSubmitted, navigate, loginError, values]);

  const validateInput = (data) => {
    const errors = {};
    const regex =
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (!data.email) {
      errors.email = "Email is Required";
    } else if (!regex.test(data.email)) {
      errors.email = "Please enter a valid email";
    }
    if (!data.password) {
      errors.password = "Password is Required";
    }
    return errors;
  };

  return (
    <form onSubmit={handleSubmit} id="form">
      <div className="title">
        <h1>LOGIN</h1>
        <p>Please provide username and password</p>
        <p>
          No Account? <a href="/register">Register </a>
        </p>
      </div>
      {loginError.message}
      <div className="message"></div>
      <div className="in">
        <div className="input-control">
          <input
            type="text"
            id="email"
            name="email"
            placeholder="Email"
            // value={values.email}
            onChange={(e) => setValues({ ...values, email: e.target.value })}
          />
          <span className="text-danger">{loginError.email}</span>
        </div>
        <div className="input-control">
          <input
            type="password"
            id="password"
            name="password"
            placeholder="Password"
            onChange={(e) => setValues({ ...values, password: e.target.value })}
          />
          <span className="text-danger">{loginError.password}</span>
        </div>
      </div>
      <button type="submit" className="rounded customButton">
        LOGIN
      </button>
    </form>
  );
};

export default Login;
