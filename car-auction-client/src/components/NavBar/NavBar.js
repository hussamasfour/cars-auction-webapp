import React, { useState } from "react";
import { Link } from "react-router-dom";

const NavBar = () => {
  const [isNavCollapsed, setIsNavCollapsed] = useState(true);
  // const isLoggedIn = useSelector((state) => state.user.isLoggedIn);

  const handleNavCollapse = () => setIsNavCollapsed(!isNavCollapsed);

  return (
    <nav className="navbar navbar-expand-lg border-bottom ">
      <div className="container-fluid">
        <div className="navbar-header">
          <Link className="navbar-brand text-white" to="/">
            CAR DEALS
          </Link>
        </div>
        <button
          className="navbar-toggler border"
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
            <li className="nav-item ">
              <Link className="nav-link text-white" to="/explore">
                Explore
              </Link>
            </li>

            <li className="nav-item ml-4">
              {/* {isLoggedIn ? (
                <span
                  className="nav-link text-white pointer"
                //   onClick={() =>)}
                >
                  Sign Out
                </span> */}
              {/* ) : (
                    <Link className="nav-link text-white" to="/login">
                    Login
                    </Link>
                )} */}
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default NavBar;
