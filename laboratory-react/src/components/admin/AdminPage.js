import React from "react";
import { Link } from "react-router-dom";
import "../../css/admin.css";

const AdminPage = () => (
  <>
    <h1>Welcome Admin</h1>
    <div className="row admin">
      <div className="col-md-4 offset-1">
        <Link to="/admin/appointment" className="btn adminlink">
          Appointments
        </Link>
      </div>
      <div className="col-md-4 offset-2">
        <Link to="/admin/report" className="btn adminlink">
          Reports
        </Link>
      </div>
    </div>
  </>
);

export default AdminPage;
