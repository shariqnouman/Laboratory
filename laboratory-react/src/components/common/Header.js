import React from "react";
import { NavLink } from "react-router-dom";

const Header = () => {
  const activeStyle = { backgroundColor: "blue" };
  return (
    <nav className="navbar bg-dark rounded mb-5 p-3">
      <div className="nav">
        <div
          className="navbar font-weight-bold text-white h5"
          style={{ fontFamily: "Georgia" }}
        >
          LABORATORY
        </div>
        <NavLink
          className="btn font-weight-bold text-white px-4 mx-4"
          exact
          to="/"
          activeStyle={activeStyle}
        >
          Home
        </NavLink>

        <NavLink
          className="btn font-weight-bold text-white px-4 mx-4"
          to="/about"
          activeStyle={activeStyle}
        >
          About
        </NavLink>
        <NavLink
          className="btn font-weight-bold text-white px-4 mx-4"
          to="/admin"
          activeStyle={activeStyle}
        >
          Admin
        </NavLink>
      </div>
    </nav>
  );
};

export default Header;
