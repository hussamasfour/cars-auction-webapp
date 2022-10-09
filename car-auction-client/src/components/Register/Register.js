import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../../utils/authService";

const Register = () => {
  let navigate = useNavigate();
  const [userInfo, setUserInfo] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
  });
  const [registerError, setRegisterError] = useState({});

  const [isSubmitted, setIsSubmitted] = useState(false);

  useEffect(() => {
    if (Object.keys(registerError).length === 0 && isSubmitted) {
      AuthService.register(userInfo)
        .then(() => {
          navigate("/login");
        })
        .catch((error) => {
          setRegisterError(error.response.data);
          isSubmitted(false);
        });
    }
  }, [isSubmitted, navigate, registerError, userInfo]);

  const inputValid = (values) => {
    const errors = {};
    const regex =
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (!values.firstName) {
      errors.firstName = "FirstName is Required";
    }

    if (!values.lastName) {
      errors.lastName = "lastName is Required";
    }

    if (!values.email) {
      errors.email = "Email is Required";
    } else if (!regex.test(values.email)) {
      errors.email = "Enter a valid email";
    }
    if (!values.password) {
      errors.password = "Password is Required";
    } else if (values.password < 8) {
      errors.password = "Password must be at least 8 length";
    }
    return errors;
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    setRegisterError(inputValid(userInfo));
    setIsSubmitted(true);
  };
  return (
    <form id="form" onSubmit={handleSubmit}>
      <div className="title">
        <h1>Register</h1>
        <p>Please fill out this form with required informations</p>
      </div>
      <div>{registerError.message}</div>
      <div className="in">
        <div className="input-control">
          <input
            type="text"
            id="firstName"
            placeholder="First Name"
            name="firstName"
            onChange={(e) =>
              setUserInfo({ ...userInfo, firstName: e.target.value })
            }
          />

          <div className="text-danger">{registerError.firstName}</div>
        </div>
        <div className="input-control">
          <input
            type="text"
            id="lastName"
            placeholder="Last Name"
            name="lastName"
            onChange={(e) =>
              setUserInfo({ ...userInfo, lastName: e.target.value })
            }
          />
          <div className="text-danger">{registerError.lastName}</div>
        </div>
        <div className="input-control">
          <input
            type="text"
            id="email"
            placeholder="Email"
            name="email"
            onChange={(e) =>
              setUserInfo({ ...userInfo, email: e.target.value })
            }
          />

          <div className="text-danger">{registerError.email}</div>
        </div>
        <div className="input-control">
          <input
            type="password"
            id="password"
            placeholder="Password"
            name="password"
            onChange={(e) =>
              setUserInfo({ ...userInfo, password: e.target.value })
            }
          />
          <div className="text-danger">{registerError.password}</div>
        </div>
      </div>
      <button type="submit" className="rounded">
        Register
      </button>
    </form>
  );
};

export default Register;
