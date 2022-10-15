import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AuthService from "../../utils/authService";
import wheel from "../../assets/wheel-2-32.png";

const NavBar = () => {
  const [isNavCollapsed, setIsNavCollapsed] = useState(true);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [currentUser, setCurrentUser] = useState({});
  const handleNavCollapse = () => setIsNavCollapsed(!isNavCollapsed);

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user) {
      setCurrentUser(user);
      setIsLoggedIn(true);
    } else {
      setIsLoggedIn(false);
    }
  }, []);

  return (
    <nav className="navbar navbar-expand-lg ">
      <div className="container-fluid">
        <div className="navbar-header">
          <Link className="navbar-brand text-white" to="/">
            <img
              src={wheel}
              alt="icon"
              width="35"
              height="25"
              className="d-inline-block m-2"
            />
            CARS DEAL
          </Link>
        </div>
        <button
          className="navbar-toggler border bg-white"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded={!isNavCollapsed ? true : false}
          aria-label="Toggle navigation"
          onClick={handleNavCollapse}
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div
          className={`${
            isNavCollapsed ? "collapse" : ""
          } navbar-collapse justify-content-end `}
        >
          <ul className="navbar-nav navbar-right mr-auto">
            <li className="nav-item">
              <Link className="nav-link text-white" to="/">
                Home
              </Link>
            </li>

            <li className="nav-item dropdown">
              <Link
                className="nav-link text-white dropdown-toggle"
                id="navbarDropdownMenuLink"
                role="button"
                data-bs-toggle="dropdown"
              >
                Welcome, {currentUser.firstName}
              </Link>
              <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                <Link className="dropdown-item" to="/">
                  Profile
                </Link>
                <Link className="dropdown-item" href="#">
                  Sign out
                </Link>
              </ul>
            </li>
            <li className="nav-item ml-4">
              {isLoggedIn ? (
                <span
                  className="nav-link text-white pointer"
                  //   onClick={() =>)}
                >
                  Sign Out
                </span>
              ) : (
                <Link className="nav-link text-white" to="/login">
                  Login
                </Link>
              )}
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default NavBar;
